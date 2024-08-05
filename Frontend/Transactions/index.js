import { fetchWithAuth, parseJwtAccountId } from "./api.js";
import { storage } from "./firebase.js";
import {
  ref,
  uploadBytes,
  getDownloadURL,
} from "https://www.gstatic.com/firebasejs/9.0.0/firebase-storage.js";

const currentAmt = document.getElementById("current-balance");
const tranSum = document.getElementById("tran-sum");
fetchWithAuth(`/api/v1/account/${await parseJwtAccountId()}`, {
  method: "get",
  headers: {
    "Content-Type": "application/json",
  },
}).then(async (it) => {
  if (it.ok) {
    currentAmt.innerHTML = `Rs. ${await it.json().then((a) => a.balance)}`;
  }
});

const fetchAllTransaction = async () => {
  const url = `/api/v1/transactions/account/${parseJwtAccountId()}`;
  const data = await fetchWithAuth(url, {
    headers: {
      "Content-Type": "application/json",
    },
  }).then((it) => it.json());
  return data;
};

function groupTransactions(transactions) {
  return transactions.reduce((acc, transaction) => {
    const type = transaction.tranType;
    if (!acc[type]) {
      acc[type] = { count: 0, totalAmount: 0 };
    }
    acc[type].count += 1;
    acc[type].totalAmount += transaction.amount;
    return acc;
  }, {});
}

fetchAllTransaction().then((it) => {
  let result = groupTransactions(it);
  tranSum.innerHTML = `INCOME (<span style="color:#2ECC71">${
    result.income?.count || 0
  }</span>): Rs. ${result.income?.totalAmount || 0}
  <br/>
  EXPENSE (<span style="color:#E74C3C">${
    result.expense?.count || 0
  }</span>): Rs. ${result.expense?.totalAmount || 0}
  `;
});

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("transaction-form");

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const tranType = document.getElementById("transaction-type").value;
    const amount = parseFloat(document.getElementById("amount").value);
    const tag = document.getElementById("tag").value;
    const file = document.getElementById("file").files[0];
    const recurring = document.getElementById("recurring").checked;
    const createdAt = document.getElementById("date").value;
    var data = { tranType, tag, amount };
    if (file) {
      const storageRef = ref(storage, "files/" + file.name);
      await uploadBytes(storageRef, file);

      const fileUrl = await getDownloadURL(storageRef);
      data = { ...data, imageUrl: fileUrl, createdAt };
      // console.log('File uploaded at:', fileUrl);
    }

    const accountId = parseJwtAccountId();
    const url = `/api/v1/transactions/transaction/${accountId}`;

    try {
      const response = await fetchWithAuth(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        if (recurring) {
          const transaction = await response.json();
          console.log(transaction);
          await fetchWithAuth(`/api/v1/crons/add/${transaction.tranId}`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              date: new Date().getDate() < 29 ? new Date().getDate() : 28,
            }),
          });
        }

        await handleTransactionRow();
        setTimeout(() => {
          window.location.href = "index.html";
        }, 100);
      } else {
        console.error("Transaction failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error during transaction:", error);
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const tagSelect = document.getElementById("tag");
  const typeSelect = document.getElementById("transaction-type");

  const tags = {
    income: ["Salary", "Investment", "Gift"],
    expense: ["Groceries", "Rent", "Utilities", "Entertainment"],
  };

  function populateTags(type) {
    tagSelect.innerHTML = ""; // Clear existing options
    const options = tags[type] || [];
    options.forEach((tag) => {
      const option = document.createElement("option");
      option.value = tag.toLowerCase();
      option.textContent = tag;
      tagSelect.appendChild(option);
    });
  }

  typeSelect.addEventListener("change", function () {
    populateTags(this.value);
  });
  populateTags(typeSelect.value);
});

const createTableRow = (transaction) => {
  const rowColor =
    transaction.tranType.toUpperCase() === "EXPENSE" ? "#E74C3C" : "#2ECC71";
  const readableCreatedAt = new Date(transaction.createdAt).toLocaleString();
  const imageUrl = transaction.imageUrl;
  const finalColm = imageUrl
    ? `<td><img src="${imageUrl}" alt="Thumbnail" style="width: 50px; cursor: pointer;" data-bs-toggle="modal" data-bs-target="#imageModal" onclick="openImageModal('${imageUrl}')" /></td>`
    : "<td> </td>";
  return `
        <tr style="background: ${rowColor}" class=${transaction.tranType.toLowerCase()}>
            <td>${readableCreatedAt}</td>
            <td>${transaction.tag}</td>
            <td>${transaction.amount}</td>
            <td>${transaction.tranType}</td>
            ${finalColm}
        </tr>
    `;
};

const handleTransactionRow = () => {
  fetchAllTransaction().then((it) => {
    const rows = it
      .sort(
        (a, b) =>
          new Date(a.createdAt).getTime() < new Date(b.createdAt).getTime()
      )
      .map((v, i) => {
        return createTableRow(v);
      })
      .join(" ");
    if (document.getElementById("transactionBody"))
      document.getElementById("transactionBody").innerHTML = rows;
    document.getElementById("loading-logo").style.display = "none";
    document.getElementById("bodyOfTransactionTable").style.display = "block";
  });
};

handleTransactionRow();

document
  .getElementById("budgetForm")
  .addEventListener("submit", async (event) => {
    event.preventDefault();

    const category = document.getElementById("category").value;
    const amount = document.getElementById("amount").value;
    const frequency = document.getElementById("frequency").value;
    const data = {
      budgetCategory: category,
      budgetAmt: amount,
      frequency: frequency,
    };

    fetchWithAuth(`/api/v1/budget/${parseJwtAccountId()}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then(async (it) => {
        if (it.ok) {
          await generateExpenseChart();
          showAlert("Budget set successfully.", "alert-primary");
          document.getElementById("budgetForm").reset();
          // window.location.reload()
        }
      })
      .catch((error) => {
        showAlert("Hmmm!! Something went wrong.", "alert-danger");
        console.log(error);
      });
  });

const transactionChartData = async (tranType) => {
  const data = await fetchAllTransaction();
  const groupedData = data
    .filter((it) => it.tranType == tranType)
    .reduce((acc, transaction) => {
      const { tag, amount } = transaction;
      if (!acc[tag.toUpperCase()]) {
        acc[tag.toUpperCase()] = 0;
      }
      acc[tag.toUpperCase()] += amount;
      return acc;
    }, {});
  return groupedData;
};

const prepareChartData = (groupedData) => {
  const labels = Object.keys(groupedData);
  const values = Object.values(groupedData);

  return {
    labels: labels,
    datasets: [
      {
        data: values,
        backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0"],
        hoverBackgroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0"],
      },
    ],
  };
};

const renderChart = async (tranType) => {
  const data = await transactionChartData(tranType);
  const chartData = prepareChartData(data);

  const ctx = document
    .getElementById(`transactionsChart-${tranType}`)
    .getContext("2d");
  new Chart(ctx, {
    type: "doughnut",
    data: chartData,
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: "top",
        },
        tooltip: {
          callbacks: {
            label: function (tooltipItem) {
              const label = tooltipItem.label || "";
              const value = tooltipItem.raw || 0;
              return `${label}: Rs.${value.toFixed(2)}`;
            },
          },
        },
      },
    },
  });
};

renderChart("income");
renderChart("expense");

fetchWithAuth(`/api/v1/account/${parseJwtAccountId()}`, {
  method: "get",
  headers: {
    "Content-Type": "application/json",
  },
}).then(async (it) => {
  if (it.ok) {
    const goals = document.getElementById("goalsId");
    const data = await it.json();
    goals.innerHTML = data.goalScore ? data.goalScore : 0;
  }
});

const fetchAllBudget = async () => {
  const response = fetchWithAuth(`/api/v1/budget/${parseJwtAccountId()}`, {
    method: "get",
    headers: {
      "Content-Type": "application/json",
    },
  });
  return (await response).json();
};

let expenseChart = null;

const generateExpenseChart = async () => {
  const expenseData = await fetchAllBudget();
  const currentExpenseData = await fetchAllTransaction();
  const ctx = document.getElementById("expensesChart").getContext("2d");

  const categories = ["Groceries", "Rent", "Utilities", "Entertainment"];

  const budgetedAmounts = categories.map((category) => {
    const expense = expenseData.find(
      (exp) => exp.budgetCategory?.toLowerCase() === category?.toLowerCase()
    );
    return expense ? expense.budgetAmt : 0;
  });

  const actualExpenses = categories.map((category) => {
    const transactions = currentExpenseData
      .filter((it) => it.tranType === "expense")
      .filter(
        (transaction) =>
          transaction?.tag?.toLowerCase() === category?.toLowerCase()
      );
    return transactions.reduce(
      (sum, transaction) => sum + transaction.amount,
      0
    );
  });

  const chartData = {
    labels: categories,
    datasets: [
      {
        label: "Budgeted Amount",
        data: budgetedAmounts,
        backgroundColor: "#36A2EB",
        borderColor: "#36A2EB",
        borderWidth: 1,
      },
      {
        label: "Actual Expenses",
        data: actualExpenses,
        backgroundColor: "#FF6384",
        borderColor: "#FF6384",
        borderWidth: 1,
      },
    ],
  };

  if (expenseChart) {
    expenseChart.destroy();
  }

  expenseChart = new Chart(ctx, {
    type: "bar",
    data: chartData,
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  });
};
generateExpenseChart();

document
  .getElementById("debtForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();
    const response = await fetchWithAuth(
      `/api/v1/account/${parseJwtAccountId()}`,
      {
        method: "get",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    const account = await response.json();
    const debtAmount = parseFloat(document.getElementById("debt-amount").value);
    const annualInterestRate =
      parseFloat(document.getElementById("interest-rate").value) / 100;
    const monthlyInterestRate = annualInterestRate / 12;
    const monthlySalary = parseFloat(account.userDetails.salary);
    const repaymentPercentage =
      parseFloat(document.getElementById("repayment-percentage").value) / 100;
    const monthlyPayment = monthlySalary * repaymentPercentage;

    let remainingAmount = debtAmount;
    let totalPaid = 0;
    let months = 0;
    const dataPoints = [];

    while (remainingAmount > 0) {
      // Monthly interest
      const monthlyInterest = remainingAmount * monthlyInterestRate;
      remainingAmount += monthlyInterest;

      if (remainingAmount > monthlyPayment) {
        remainingAmount -= monthlyPayment;
        totalPaid += monthlyPayment;
      } else {
        totalPaid += remainingAmount;
        remainingAmount = 0;
      }

      months++;
      dataPoints.push({ x: months, y: totalPaid });
    }
    document.getElementById("debt-year").innerText = (months / 12).toFixed(1);
    document.getElementById("total-debt-amt").innerText = totalPaid.toFixed(3);

    plotDebtProgress(dataPoints, months);
    document.getElementById("debt-graph").style.display = "block";
  });

function plotDebtProgress(dataPoints, totalMonths) {
  const ctx = document.getElementById("debtProgressChart").getContext("2d");
  new Chart(ctx, {
    type: "line",
    data: {
      datasets: [
        {
          label: "Total Amount Paid Over Time",
          data: dataPoints,
          fill: false,
          borderColor: "blue",
          tension: 0.1,
        },
      ],
    },
    options: {
      scales: {
        x: {
          type: "linear",
          title: {
            display: true,
            text: "Months",
          },
          ticks: {
            stepSize: Math.max(1, Math.floor(totalMonths / 12)), // Adjust the step size for better readability
          },
        },
        y: {
          title: {
            display: true,
            text: "Total Amount Paid (Principal + Interest)",
          },
        },
      },
    },
  });
}

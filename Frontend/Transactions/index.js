import { fetchWithAuth } from "./api.js";
document.addEventListener("DOMContentLoaded", () => {
  // Check if the redirection has already occurred
});

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("transaction-form");

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const tranType = document.getElementById("transaction-type").value;
    const amount = parseFloat(document.getElementById("amount").value);
    const tag = document.getElementById("tag").value;

    const data = { tranType, tag, amount };
    const accountId = "ce975592-b00f-4302-b577-04d1d2d33fdd"; // need to change in future
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

const fetchAllTransaction = async () => {
  const url = "/api/v1/transactions/all";
  const data = await fetchWithAuth(url, {
    headers: {
      "Content-Type": "application/json",
    },
  }).then((it) => it.json());
  console.log(data);
  return data;
};

const createTableRow = (transaction) => {
  const rowColor =
    transaction.tranType.toUpperCase() === "EXPENSE" ? "#FF4C4C" : "#399918";
  const readableCreatedAt = new Date(transaction.createdAt).toLocaleString();
  return `
        <tr style="background: ${rowColor}" class=${transaction.tranType.toLowerCase()}>
            <td>${readableCreatedAt}</td>
            <td>${transaction.tag}</td>
            <td>${transaction.amount}</td>
            <td>${transaction.tranType}</td>
        </tr>
    `;
};

const handleTransactionRow = () => {
  fetchAllTransaction().then((it) => {
    const rows = it
      .map((v, i) => {
        return createTableRow(v);
      })
      .join(" ");
    if (document.getElementById("transactionBody"))
      document.getElementById("transactionBody").innerHTML = rows;
      document.getElementById("bodyOfTransactionTable").style.display = "block"
      document.getElementById("loading-spinner").style.display = "none !important"
  });
};

handleTransactionRow();

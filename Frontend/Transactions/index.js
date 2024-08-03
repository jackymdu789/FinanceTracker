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
    var data = { tranType, tag, amount };
    if (file) {
      const storageRef = ref(storage, "files/" + file.name);
      await uploadBytes(storageRef, file);

      const fileUrl = await getDownloadURL(storageRef);
      data = { ...data, imageUrl: fileUrl };
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

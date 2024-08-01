// document.addEventListener("DOMContentLoaded", function () {
//   // Generate a unique transaction ID
//   function generateTransactionId() {
//     return "TX" + Math.random().toString(36).substr(2, 9).toUpperCase();
//   }

// // Set the initial transaction ID
// document.getElementById("transaction-id").value = generateTransactionId();

// // Handle form submission
// document
//   .getElementById("transaction-form")
//   .addEventListener("submit", function (event) {
//     event.preventDefault();

//     // Collect form data
//     const transactionId = document.getElementById("transaction-id").value;
//     const transactionType = document.getElementById("transaction-type").value;
//     const amount = parseFloat(document.getElementById("amount").value);
//     const date = document.getElementById("date").value;
//     const accountId = document.getElementById("account-id").value;

//     // Validate and process data (e.g., send to server or display in console)
//     if (transactionType && amount >= 0 && date && accountId) {
//       console.log({
//         transactionId,
//         transactionType,
//         amount,
//         date,
//         accountId,
//       });
//       // Here you can add code to submit the data to a server or handle it as needed
//     } else {
//       alert("Please fill out all required fields.");
//     }
//   });
// });

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('transaction-form');
  
  form.addEventListener('submit', async (event) => {
      event.preventDefault(); 
      
      const tranType = document.getElementById("transaction-type").value;
      const amount = parseFloat(document.getElementById("amount").value);
      const tag = document.getElementById("tag").value;

      const data = { tranType, tag, amount };
      const accountId = "ce975592-b00f-4302-b577-04d1d2d33fdd"; // need to change in future
      const url = `http://127.0.0.1:7000/api/v1/transactions/transaction/${accountId}`;

      try {
          const response = await fetch(url, {
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
              console.error('Transaction failed:', response.statusText);
          }
      } catch (error) {
          console.error('Error during transaction:', error);
      }
  });
});

const fetchAllTransaction = async () => {
  const url = "http://127.0.0.1:7000/api/v1/transactions/all";
  const data = await fetch(url).then((it) => it.json());
  return data;
};

const createTableRow = (transaction) => {
  const rowColor =
    transaction.tranType.toUpperCase() === "EXPENSE" ? "#FF4C4C" : "#399918";
  const readableCreatedAt = new Date(transaction.createdAt).toLocaleString();
  return `
        <tr style="background: ${rowColor}">
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
    document.getElementById("transactionBody").innerHTML = rows;
  });
};

handleTransactionRow();

document.addEventListener('DOMContentLoaded', function() {
    // Generate a unique transaction ID
    function generateTransactionId() {
        return 'TX' + Math.random().toString(36).substr(2, 9).toUpperCase();
    }

    // Set the initial transaction ID
    document.getElementById('transaction-id').value = generateTransactionId();

    // Handle form submission
    document.getElementById('transaction-form').addEventListener('submit', function(event) {
        event.preventDefault();

        // Collect form data
        const transactionId = document.getElementById('transaction-id').value;
        const transactionType = document.getElementById('transaction-type').value;
        const amount = parseFloat(document.getElementById('amount').value);
        const date = document.getElementById('date').value;
        const accountId = document.getElementById('account-id').value;

        // Validate and process data (e.g., send to server or display in console)
        if (transactionType && amount >= 0 && date && accountId) {
            console.log({
                transactionId,
                transactionType,
                amount,
                date,
                accountId
            });
            // Here you can add code to submit the data to a server or handle it as needed
        } else {
            alert('Please fill out all required fields.');
        }
    });
});

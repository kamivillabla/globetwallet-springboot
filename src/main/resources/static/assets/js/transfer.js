document.addEventListener('DOMContentLoaded', () => {
    const recipientUserSelect = document.getElementById('recipientUserId');
    const recipientAccountSelect = document.getElementById('recipientAccountId');

    recipientUserSelect.addEventListener('change', fetchRecipientAccounts);

    async function fetchRecipientAccounts() {
        const userId = recipientUserSelect.value;
        try {
            const response = await fetch(`/transfer/api/recipients/${userId}/accounts`);
            const accounts = await response.json();

            recipientAccountSelect.innerHTML = '<option value="" disabled selected>Selecciona la cuenta del destinatario</option>';

            accounts.forEach(account => {
                const option = document.createElement('option');
                option.value = account.accountID;
                option.text = `${account.bankName} - ${account.accountNumber}`;
                recipientAccountSelect.appendChild(option);
            });
        } catch (error) {
            console.error('Error al obtener las cuentas del destinatario:', error);
        }
    }
});

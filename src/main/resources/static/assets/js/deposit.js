document.addEventListener('DOMContentLoaded', function() {
	const accountSelect = document.getElementById('accountId');
	const balanceElement = document.getElementById('saldo-actual');
	const accountNumberElement = document.querySelector('.credit-card__number');
	const bankNameElement = document.querySelector('.credit-card__logo');

	accountSelect.addEventListener('change', function() {
		const selectedOption = accountSelect.options[accountSelect.selectedIndex];
		const bankName = selectedOption.getAttribute('data-bankName');
		const balance = selectedOption.getAttribute('data-balance');
		const accountNumber = selectedOption.getAttribute('data-accountNumber');

		balanceElement.textContent = balance ? balance : '0';
		accountNumberElement.textContent = accountNumber ? accountNumber : 'XXXXXXXXXXXXX';
		bankNameElement.textContent = bankName ? bankName : 'VISA';
	});

	const selectedOption = accountSelect.options[accountSelect.selectedIndex];
	if (selectedOption) {
		const event = new Event('change');
		accountSelect.dispatchEvent(event);
	}

});



$(document).ready(function() {
    setupSidebarToggle();
    formatSaldo();
    showLoginError();
    showAlertFromServer();
});

function setupSidebarToggle() {
    const navToggle = $('.nav-toggle-label');
    const sidebar = $('.sidebar');
    navToggle.click(function() {
        sidebar.toggleClass('active');
        navToggle.toggleClass('toggled');
    });
}

function formatSaldo() {
    const saldoElement = document.getElementById('saldo-actual');
    if (saldoElement) {
        const saldo = saldoElement.textContent.replace('$', '').trim();
        saldoElement.textContent = '$' + new Intl.NumberFormat('es-CL', { style: 'decimal' }).format(saldo);
    }
}

function showAlert(message, type) {
    const alertHtml = $(
        `<div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>`
    );

    const alertBox = $('#alert-container');
    alertBox.empty().append(alertHtml);
    setTimeout(() => {
        alertHtml.fadeOut();
    }, 3000);
}

function showLoginError() {
    const errorMessage = $('#alert-container').data('error');
    if (errorMessage) {
        showAlert(errorMessage, 'danger');
    }
}

function showAlertFromServer() {
    const successMessage = $('#alert-container').data('success');
    const errorMessage = $('#alert-container').data('error');

    if (successMessage) {
        showAlert(successMessage, 'success');
    } else if (errorMessage) {
        showAlert(errorMessage, 'danger');
    }
}

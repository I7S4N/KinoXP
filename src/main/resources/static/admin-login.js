// Hardcoded admin password
const ADMIN_PASSWORD = "Showy";

document.getElementById("adminLoginForm").addEventListener("submit", function(e){
    e.preventDefault();

    const passwordInput = document.getElementById("password").value;
    const message = document.getElementById("loginMessage");

    if(passwordInput === ADMIN_PASSWORD){
        localStorage.setItem("adminToken", "loggedIn");
        window.location.href = "admin-showings.html";
    } else {
        message.textContent = "Forkert adgangskode! Prøv igen.";
    }
});
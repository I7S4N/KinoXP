// Tjek om admin er logget ind
if(localStorage.getItem("adminToken") !== "loggedIn"){
    window.location.href = "admin-login.html";
}

// Logout
const logoutBtn = document.getElementById("logoutBtn");
if(logoutBtn){
    logoutBtn.addEventListener("click", () => {
        localStorage.removeItem("adminToken");
        window.location.href = "admin-login.html";
    });
}
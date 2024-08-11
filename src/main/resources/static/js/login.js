document.getElementById('loginForm').addEventListener('submit', function(event){
    event.preventDefault();

    let email = document.getElementById('email').value;
    if (!validateEmail(email)) {
        alert("Please enter a valid email address.");
        return;
    }
     else {
        this.submit();
    }

});

function validateEmail(email) {
    let re = /\S+@\S+\.\S+/;
    return re.test(email);
}

function togglePasswordVisibility() {
    let passwordInput = document.getElementById('password');
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}

let form = document.getElementById('register_form');
let password = document.getElementById('password');
let confirmPassword = document.getElementById("password_confirm");
password.onchange = validatePassword;
confirmPassword.onkeyup = validatePassword;
form.onsubmit = registerUser;

/**
 * The validatePassword function is used to validate the password and confirmPassword fields.
 * It checks if the passwords match, and sets a custom error message on the confirmPassword field if they do not.
 */
function validatePassword() {
    if (password.value != confirmPassword.value) {
        confirmPassword.setCustomValidity("Passwords do not match");
    } else {
        confirmPassword.setCustomValidity('');
    }
}

/**
 * The registerUser function is used to register a new user.
 *
 * @return A promise.
 */
function registerUser(e) {
    e.preventDefault();
    const userInfos = {
        firstName: document.getElementById('first_name').value,
        lastName: document.getElementById('last_name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
    }
    fetch("http://localhost:8080/user", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userInfos)
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.success) {
            setUserLocalStorage(res);
            Swal.fire({
                title: 'Register successfull!',
                html: `We are going to redirect you promptly.`,
                icon: 'success',
                timer: 3000,
                timerProgressBar: true,
            }).then((result) => {
                window.location.href = "http://localhost:80/index.html";
            });
        }
        else {
            M.toast({ html: `<b>${res.message}</b>`, classes: 'red darken-4', displayLength: 8000 });
        }
    });
}
document.getElementById('login_form').onsubmit = login;

/**
 * The login function is used to log in a user.
 *
 * @param e Used to Prevent the default action of submitting a form.
 */
function login(e) {
    e.preventDefault();
    let loginInfos = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    }
    fetch("http://localhost:8080/user/login", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginInfos)
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.success) {
            setUserLocalStorage(res);
            Swal.fire({
                title: "You're in!",
                html: `Please wait a tiny little bit!`,
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
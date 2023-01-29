
/**
 * The checkLogin function checks if the user is logged in.
 * If they are, it redirects them to the jobboard page.
 * If not, it does nothing and continues on with execution of the rest of the script.
 */
 function checkIfLogged() {
    if (localStorage.getItem('token'))
        window.location.href = document.URL.substring(0, document.URL.lastIndexOf('/')) + '/jobboard.html';
}

/**
 * The setUserLocalStorage function sets the local storage of the user's token, id, name, phone and email.
 *
 * @param user Used to Get the user information from the database.
 */
 function setUserLocalStorage(user) {
    localStorage.setItem('token', user.token);
}

/**
 * The logout function clears the local storage and refreshes the page.
 */
 function logout() {
    localStorage.clear();
    window.location.href = "http://localhost:80/index.html";
}
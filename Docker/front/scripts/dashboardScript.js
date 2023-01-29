document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, {});
});

document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems, { dismissible: true });
});

document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.dropdown-trigger');
    var instances = M.Dropdown.init(elems, {});
});

let row = document.getElementsByClassName("row")[0];
//changer la ligne suivante pour être appelée après l'autocomplétion dans la fenetre modale.
//widget.querySelector("button").addEventListener('click', getCurrentWeather);


window.onload = (e) => {
    if (localStorage.getItem("token")) {
        loadWidgets();
        document.getElementById("center-login").style.display = "none";
        document.getElementById("login").style.display = "none";
        document.getElementById("nav-main-actions").style.display = ""
    }
}

async function loadWidgets() {
    let jwt = localStorage.getItem("token");
    let widgets;
    if (jwt == null)
        return;
    await fetch("http://localhost:8080/user/getWidgets", {
        method: "GET",
        headers: {
            "Authorization": jwt
        }
    }).then((res) => {
        if (res.status < 200 || res.status > 300)
            throw "Couldn't fetch widgets"
        return res.json();
    }).then((res) => {
        widgets = res;
    });
    if (widgets == null)
        return;

    widgets.forEach(widget => {
        switch (widget.type) {
            case 'weather':
                console.log("insee: ", widget.insee, " id: ", widget.id);
                initCurrentWeather(widget.insee, widget.id);
                break;
            default:
                return;
        }
    });
}
initForms();

async function initForms() {
    cityPicker.addEventListener("keyup", async function () {
        let listCities = document.getElementById("cityList");
        let cityPicker = document.getElementById("cityPicker");
        let insee = document.getElementById("insee");

        listCities.innerHTML = "";
        if (cityPicker.value.length < 3) {
            return null;
        } else {
            await fetch("https://geo.api.gouv.fr/communes?nom=" + cityPicker.value + "&fields=departement&boost=population&limit=5")
                .then((res) => {
                    return res.json();
                }).then((res) => {
                    res.forEach(element => {
                        let li = document.createElement("li");
                        li.innerText = element["nom"];
                        li.id = element["code"];
                        li.onclick = (e) => { cityPicker.value = li.innerText; insee.value = li.id/* getCurrentWeather(li.id, widget)*/ }
                        listCities.appendChild(li);
                    });
                    insee.value = res[0].code;
                })
        }
    })

    form1.addEventListener("submit", async (e) => {
        e.preventDefault();
        currentWeatherFormOnSubmit();
    });
}

async function currentWeatherFormOnSubmit() {
    let widgetId = form1.querySelector("#widgetId").value;
    let inseeCode = form1.querySelector("#insee").value;
    if (widgetId == 0) {
        await fetch("http://localhost:8080/user/weather/addreport/" + inseeCode, {
            method: "POST",
            headers: {
                "Authorization": localStorage.getItem("token")
            }
        }).then((res) => {
            return res.json();
        }).then((res) => {
            initCurrentWeather(res.insee, res.id);
        });
    } else {
        await fetch("http://localhost:8080/user/weather/edit/" + widgetId + "/" + inseeCode, {
            method: "PATCH",
            headers: {
                "Authorization": localStorage.getItem("token")
            }
        });
        getCurrentWeather(inseeCode, document.getElementById(widgetId));
    }
    widgetId.value = 0;
}

async function initCurrentWeather(insee, id) {
    let widget = document.getElementById("wCurrentWeather").content.firstElementChild.cloneNode(true);
    widget.id = id;
    widget.querySelector(".wCurrentWeatherEditBtn").addEventListener("click", (e) => {
        editWeatherWidget(id);
    });
    getCurrentWeather(insee, widget);
    row.appendChild(widget);
}

async function addWeatherWidget() {
    let modal = M.Modal.getInstance(document.getElementById("modal1"));
    let widgetId = document.getElementById("widgetId");
    widgetId.value = 0;
    modal.open();
}

async function editWeatherWidget(id) {
    let modal = M.Modal.getInstance(document.getElementById("modal1"));
    let widgetId = document.getElementById("widgetId");
    widgetId.value = id;
    modal.open();
}

async function getCurrentWeather(insee, target) {
    await fetch("http://localhost:8080/weather/report/" + insee, {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        let objResponse = res;
        target.querySelector(".wCurrentWeatherCityName").innerText = objResponse.city;
        target.querySelector(".wCurrentWeatherDegrees").innerText = objResponse.temp + "°C";
        target.querySelector(".wCurrentWeatherIcon").src = mapWeatherVerbose.get(objResponse.weather);
        target.querySelector(".wCurrentWeatherWindSpeed").innerText = objResponse.windSpeed + "km/h";
        let rotationArrow = windQuadrant.get(objResponse.windDirection);
        target.querySelector(".wCurrentWeatherWindArrow").style.transform = 'rotate(' + rotationArrow + 'deg)';
    });


}

const windQuadrant = new Map();
windQuadrant.set("W", 0);
windQuadrant.set("NW", 45);
windQuadrant.set("N", 90);
windQuadrant.set("NE", 135);
windQuadrant.set("E", 180);
windQuadrant.set("SE", 225);
windQuadrant.set("S", 270);
windQuadrant.set("SW", 315);

const mapWeatherVerbose = new Map();
mapWeatherVerbose.set(0, "./animated/day.svg");
mapWeatherVerbose.set(1, "./animated/cloudy-day-1.svg");
mapWeatherVerbose.set(2, "./animated/cloudy-day-2.svg");
mapWeatherVerbose.set(3, "./animated/cloudy.svg");
mapWeatherVerbose.set(4, "./animated/cloudy.svg");
mapWeatherVerbose.set(5, "./animated/cloudy.svg");
mapWeatherVerbose.set(6, "./animated/cloudy.svg");
mapWeatherVerbose.set(7, "./animated/snowy-4.svg");
mapWeatherVerbose.set(10, "./animated/rainy-4.svg");
mapWeatherVerbose.set(11, "./animated/rainy-6.svg");
mapWeatherVerbose.set(12, "./animated/rainy-7.svg");
mapWeatherVerbose.set(13, "./animated/rainy-4.svg");
mapWeatherVerbose.set(14, "./animated/rainy-6.svg");
mapWeatherVerbose.set(15, "./animated/rainy-7.svg");
mapWeatherVerbose.set(16, "./animated/rainy-4.svg");
mapWeatherVerbose.set(20, "./animated/snowy-4.svg");
mapWeatherVerbose.set(21, "./animated/snowy-5.svg");
mapWeatherVerbose.set(22, "./animated/snowy-6.svg");
mapWeatherVerbose.set(30, "./animated/rainy-7.svg");
mapWeatherVerbose.set(31, "./animated/rainy-7.svg");
mapWeatherVerbose.set(32, "./animated/rainy-7.svg");
mapWeatherVerbose.set(40, "./animated/rainy-7.svg");
mapWeatherVerbose.set(41, "./animated/rainy-7.svg");
mapWeatherVerbose.set(42, "./animated/rainy-7.svg");
mapWeatherVerbose.set(43, "./animated/rainy-7.svg");
mapWeatherVerbose.set(44, "./animated/rainy-7.svg");
mapWeatherVerbose.set(45, "./animated/rainy-7.svg");
mapWeatherVerbose.set(46, "./animated/rainy-7.svg");
mapWeatherVerbose.set(47, "./animated/rainy-7.svg");
mapWeatherVerbose.set(48, "./animated/rainy-7.svg");
mapWeatherVerbose.set(60, "./animated/snowy-6.svg");
mapWeatherVerbose.set(61, "./animated/snowy-6.svg");
mapWeatherVerbose.set(62, "./animated/snowy-6.svg");
mapWeatherVerbose.set(63, "./animated/snowy-6.svg");
mapWeatherVerbose.set(64, "./animated/snowy-6.svg");
mapWeatherVerbose.set(65, "./animated/snowy-6.svg");
mapWeatherVerbose.set(66, "./animated/snowy-6.svg");
mapWeatherVerbose.set(67, "./animated/snowy-6.svg");
mapWeatherVerbose.set(68, "./animated/snowy-6.svg");
mapWeatherVerbose.set(70, "./animated/snowy-6.svg");
mapWeatherVerbose.set(71, "./animated/snowy-6.svg");
mapWeatherVerbose.set(72, "./animated/snowy-6.svg");
mapWeatherVerbose.set(73, "./animated/snowy-6.svg");
mapWeatherVerbose.set(74, "./animated/snowy-6.svg");
mapWeatherVerbose.set(75, "./animated/snowy-6.svg");
mapWeatherVerbose.set(76, "./animated/snowy-6.svg");
mapWeatherVerbose.set(77, "./animated/snowy-6.svg");
mapWeatherVerbose.set(78, "./animated/snowy-6.svg");
mapWeatherVerbose.set(100, "./animated/thunder.svg");
mapWeatherVerbose.set(101, "./animated/thunder.svg");
mapWeatherVerbose.set(102, "./animated/thunder.svg");
mapWeatherVerbose.set(103, "./animated/thunder.svg");
mapWeatherVerbose.set(104, "./animated/thunder.svg");
mapWeatherVerbose.set(105, "./animated/thunder.svg");
mapWeatherVerbose.set(106, "./animated/thunder.svg");
mapWeatherVerbose.set(107, "./animated/thunder.svg");
mapWeatherVerbose.set(108, "./animated/thunder.svg");
mapWeatherVerbose.set(120, "./animated/thunder.svg");
mapWeatherVerbose.set(121, "./animated/thunder.svg");
mapWeatherVerbose.set(122, "./animated/thunder.svg");
mapWeatherVerbose.set(123, "./animated/thunder.svg");
mapWeatherVerbose.set(124, "./animated/thunder.svg");
mapWeatherVerbose.set(125, "./animated/thunder.svg");
mapWeatherVerbose.set(126, "./animated/thunder.svg");
mapWeatherVerbose.set(127, "./animated/thunder.svg");
mapWeatherVerbose.set(128, "./animated/thunder.svg");
mapWeatherVerbose.set(130, "./animated/thunder.svg");
mapWeatherVerbose.set(131, "./animated/thunder.svg");
mapWeatherVerbose.set(132, "./animated/thunder.svg");
mapWeatherVerbose.set(133, "./animated/thunder.svg");
mapWeatherVerbose.set(134, "./animated/thunder.svg");
mapWeatherVerbose.set(135, "./animated/thunder.svg");
mapWeatherVerbose.set(136, "./animated/thunder.svg");
mapWeatherVerbose.set(137, "./animated/thunder.svg");
mapWeatherVerbose.set(138, "./animated/thunder.svg");
mapWeatherVerbose.set(140, "./animated/thunder.svg");
mapWeatherVerbose.set(141, "./animated/thunder.svg");
mapWeatherVerbose.set(142, "./animated/thunder.svg");
mapWeatherVerbose.set(210, "./animated/rainy-4.svg");
mapWeatherVerbose.set(211, "./animated/rainy-5.svg");
mapWeatherVerbose.set(212, "./animated/rainy-6.svg");
mapWeatherVerbose.set(220, "./animated/snowy-4.svg");
mapWeatherVerbose.set(221, "./animated/snowy-5.svg");
mapWeatherVerbose.set(222, "./animated/snowy-6.svg");
mapWeatherVerbose.set(230, "./animated/rainy-7.svg");
mapWeatherVerbose.set(231, "./animated/rainy-7.svg");
mapWeatherVerbose.set(232, "./animated/rainy-7.svg");
mapWeatherVerbose.set(235, "./animated/rainy-7.svg");

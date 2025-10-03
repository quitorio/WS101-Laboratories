let temperature = document.querySelector(".temp");
let summary = document.querySelector(".summary");
let loc = document.querySelector(".location");
let dateDiv = document.querySelector(".date");
const kelvin = 273;

const api = "6d055e39ee237af35ca066f35474e9df";

const cityInput = document.querySelector("#cityInput");
const searchBtn = document.querySelector("#searchBtn");

function getWeather(city) {
    if (!city) {
        temperature.textContent = "";
        summary.textContent = "Please enter a city name.";
        loc.textContent = "";
        dateDiv.textContent = "";
        return;
    }
    const base = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${api}`;
    fetch(base)
        .then((response) => response.json())
        .then((data) => {
            if (data.cod !== 200) {
                temperature.textContent = "";
                summary.textContent = "City not found.";
                loc.textContent = "";
                dateDiv.textContent = "";
                return;
            }
            temperature.textContent = Math.floor(data.main.temp - kelvin) + "°C";
            summary.textContent = data.weather[0].description;
            loc.textContent = data.name + ", " + data.sys.country;
            dateDiv.textContent = new Date().toLocaleDateString('en-US', {
                year: 'numeric',
                month: 'long',
                day: 'numeric'
            });
        })
        .catch(() => {
            temperature.textContent = "";
            summary.textContent = "Error fetching weather data.";
            loc.textContent = "";
            dateDiv.textContent = "";
        });
}

window.addEventListener("load", () => {
});

searchBtn.addEventListener("click", () => {
    const city = cityInput.value.trim();
    if (city) {
        getWeather(city);
    } else {
        getWeather("");
    }
});
const divContainer = document.getElementById("divContainer");

//Buttons
const btnUserA = document.getElementById("allUsers");
const btnUserID = document.getElementById("userByID");
const btnUserP = document.getElementById("userByPhone");
const btnCities = document.getElementById("cities");

//URLs
const urlUsersA = "http://localhost:8080/devops_starter_war_exploded/api/users/";
const urlUserID = "http://localhost:8080/devops_starter_war_exploded/api/users/byID/";
const urlUserP = "http://localhost:8080/devops_starter_war_exploded/api/users/byPhone/";
const urlCitiesA = "http://localhost:8080/devops_starter_war_exploded/api/cities/";

//Clicks
btnUserA.onclick = () => showUsers();
btnUserID.onsubmit = (evt) => { evt.preventDefault(); showUserByID(document.getElementById("userID").value)};
btnUserP.onsubmit = (evt) => { evt.preventDefault(); showUserByPhone(document.getElementById("phoneNumber").value)} 
btnCities.onclick = () => showCities();

//Functions
function showUsers(){
    fetch(urlUsersA)
    .then(res => res.json())
    .then(data => {
        if(data.length == 0){
            divContainer.innerHTML = "There were no persons to display"
            
            return;
        }

        let htmlString = `<table frame ="void", rules = "rows"><th>ID</th><th>Email</th><th>First Name</th><th>Last Name</th>`;

        data.forEach(user => {
            htmlString += `<tr><td>${user["id"]}</td><td>${user["email"]}</td><td>${user["firstName"]}</td><td>${user["lastName"]}</td></tr>`;
        })

        htmlString += "</table>";

        divContainer.innerHTML = htmlString;
    });
}

function showUserByID(id){
    fetch(urlUserID + id)
    .then(res => res.json())
    .then(user => {
        let htmlString = `<table frame ="void", rules = "rows"><th>ID</th><th>Email</th><th>First Name</th><th>Last Name</th>`;
            
        htmlString += `<tr><td>${user["id"]}</td><td>${user["email"]}</td><td>${user["firstName"]}</td><td>${user["lastName"]}</td></tr>`;
            
        htmlString += "</table>";

        divContainer.innerHTML = htmlString;
    });
}

function showUserByPhone(phoneNumber){
    fetch(urlUserP + phoneNumber)
    .then(res => res.json())
    .then(user => {
        let htmlString = `<table frame ="void", rules = "rows"><th>ID</th><th>Email</th><th>First Name</th><th>Last Name</th>`;
            
        htmlString += `<tr><td>${user["id"]}</td><td>${user["email"]}</td><td>${user["firstName"]}</td><td>${user["lastName"]}</td></tr>`;
            
        htmlString += "</table>";

        divContainer.innerHTML = htmlString;
    });
}

function showCities(){
    fetch(urlCitiesA)
    .then(res => res.json())
    .then(data => {

        if(data.length == 0){
            divContainer.innerHTML = "There were no cities to display"
            
            return;
        }

        let htmlString = `<table frame ="void", rules = "rows"><th>ZIP Code</th><th>Name</th>`;

        data.forEach(city => {
            htmlString += `<tr><td>${city["zipCode"]}</td><td>${city["city"]}</td></tr>`;
        })

        htmlString += "</table>";

        divContainer.innerHTML = htmlString;
    });
}
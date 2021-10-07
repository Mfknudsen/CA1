const btnContainer = document.getElementById("btnContainer")
const divContainer = document.getElementById("divContainer");

getPage();

//URLs
const urlUsers = "http://localhost:8080/devops_starter_war_exploded/api/users/";
const urlUserID = "http://localhost:8080/devops_starter_war_exploded/api/users/byID/";
const urlUserP = "http://localhost:8080/devops_starter_war_exploded/api/users/byPhone/";
const urlUsersH = "http://localhost:8080/devops_starter_war_exploded/api/users/byHobby/";
const urlCitiesA = "http://localhost:8080/devops_starter_war_exploded/api/cities/";
const urlUserEdit = "http://localhost:8080/devops_starter_war_exploded/api/users/edit/";

//Functions
function getPage() {
    btnContainer.innerHTML = `        
    <button id = "allUsers">Get All User</button>
    <button id = "btnEdit">Edit User</button>
    <button id = "btnAdd">Add User</button>
    <form action="" method="get" id="userByID">
        <input type="number" name="" id="userID" value="0">
        <input type="submit" value="Get User By ID">
    </form>
    <form action="" method="get" id="userByPhone">
        <input type="number" name="" id="phoneNumber" value="0">
        <input type="submit" value="Get User By Phone Number">
    </form>
    <form action="" method="get" id="userByHobby">
        <input type="text" name="" id="hobbyName" value="">
        <input type="submit" value="Get User Count By Hobby">
    </form>
    <button id = "cities">Get Cities</button>
    `;
    divContainer.innerHTML = "";

    //Buttons
    btnUserA = document.getElementById("allUsers");
    btnUserID = document.getElementById("userByID");
    btnUserP = document.getElementById("userByPhone");
    btnUsersH = document.getElementById("userByHobby");
    btnCities = document.getElementById("cities");
    btnEdit = document.getElementById("btnEdit");
    btnAdd = document.getElementById("btnAdd");

    //Clicks
    btnUserA.onclick = () => showUsers();
    btnUserID.onsubmit = (evt) => { evt.preventDefault(); showUserByID(document.getElementById("userID").value)};
    btnUserP.onsubmit = (evt) => { evt.preventDefault(); showUserByPhone(document.getElementById("phoneNumber").value)} 
    btnUsersH.onsubmit = (evt) => { evt.preventDefault(); showUsersByHobby(document.getElementById("hobbyName").value)} 
    btnCities.onclick = () => showCities();
    btnEdit.onclick = () => editPage();
    btnAdd.onclick = () => addPage();
}

function editPage() {
    btnContainer.innerHTML = `
    <button id = "cancel">Cancel</button>
    <form id = "editForm">
        <p>ID</p>
        <input type="number" id="id">
        <p>Email</p>
        <input type="text" id="email">
        <p>First Name</p>
        <input type="text" id="firstName">
        <p>Last name</p>            
        <input type="text" id="lastName">
        <input type="submit" value="Edit By ID">
    </form>
    `;
    divContainer.innerHTML = "";

    //Buttons
    btnCancel = document.getElementById("cancel");
    btnSubmit = document.getElementById("editForm");

    //Clicks
    btnCancel.onclick = () => getPage();
    btnSubmit.onsubmit = (evt) => {evt.preventDefault(); 
        editUser(
            document.getElementById("id").value,
            document.getElementById("email").value,
            document.getElementById("firstName").value,
            document.getElementById("lastName").value
        )};
}

function addPage() {
    btnContainer.innerHTML = `
    <button id = "cancel">Cancel</button>
    <form id = "addForm">
        <p>Email</p>
        <input type="text" id="email">
        <p>First Name</p>
        <input type="text" id="firstName">
        <p>Last Name</p>
        <input type="text" id="lastName">
        <input type="submit" value="Add User">
    </form>
    `;
    divContainer.innerHTML = ``;

    //Buttons
    btnCancel = document.getElementById("cancel");
    btnSubmit = document.getElementById("addForm")

    //Clicks
    btnCancel.onclick = () => getPage();
    btnSubmit.onsubmit = (evt) => {evt.preventDefault(); addUsers(
        document.getElementById("email").value,
        document.getElementById("firstName").value,
        document.getElementById("lastName").value
    )};
}

function showUsers(){
    fetch(urlUsers)
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
        if(user["id"] == undefined){
            divContainer.innerHTML = "There were no persons with the giving id to display"
            
            return;
        }

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
        if(user["id"] == undefined){
            divContainer.innerHTML = "There were no persons with the giving phone number to display"
            
            return;
        }

        let htmlString = `<table frame ="void", rules = "rows"><th>ID</th><th>Email</th><th>First Name</th><th>Last Name</th>`;
            
        htmlString += `<tr><td>${user["id"]}</td><td>${user["email"]}</td><td>${user["firstName"]}</td><td>${user["lastName"]}</td></tr>`;
            
        htmlString += "</table>";

        divContainer.innerHTML = htmlString;
    });
}

function showUsersByHobby(hobbyName){
    fetch(urlUsersH + hobbyName)
    .then(res => res.json())
    .then(data => {
        htmlString = "There is a total of " + data + " persons who has this hobby";
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

function addUsers(email, firstName, lastName){
    let object = {email: email, firstName: firstName, lastName}
console.log(JSON.stringify(object));
    h = new XMLHttpRequest();
    h.open('POST', urlUsers, true)
    h.setRequestHeader('Content-Type', 'application/json');
    h
    h.onreadystatechange = function () { //Call a function when the state changes.
        if (h.readyState == 4 && h.status == 200) {
            alert(h.responseText);
        }
    }

    h.send(JSON.stringify(object))
}

function editUser(id, email, firstName, lastName){
    let object = {id: id, email: email, firstName: firstName, lastName}

    h = new XMLHttpRequest();
    h.open('PUT', urlUserEdit, true)
    h.setRequestHeader('Content-Type', 'application/json');
    h
    h.onreadystatechange = function () { //Call a function when the state changes.
        if (h.readyState == 4 && h.status == 200) {
            alert(h.responseText);
        }
    }

    h.send(JSON.stringify(object))
}

document.addEventListener("DOMContentLoaded", () => {
        getAllUsers();
});

const editModal = document.getElementById('editModal')
const submit = document.getElementById("sendUserForm");


editModal.addEventListener('show.bs.modal', function (event) {

    var button = event.relatedTarget
    var id = button.getAttribute('data-bs-id')
    var del = button.getAttribute('data-bs-delete')

    if (del=="true"){
        document.getElementById('ModalLabel').innerHTML = 'Delete user';
        for (let elements of document.getElementsByClassName('form-control')) {
            elements.setAttribute('readonly', 'true');
        }
        document.getElementById('deleteButton').style.display = 'block';
        document.getElementById('editButton').style.display = 'none';

    }else{
        document.getElementById('ModalLabel').innerHTML = 'Edit user';
        for (let elements of document.getElementsByClassName('form-control')) {
            elements.removeAttribute('readonly');
        }
        document.getElementById('deleteButton').style.display = 'none';
        document.getElementById('editButton').style.display = 'block';
    }

    getUser(id);

})

function closeEditModal() {
    bootstrap.Modal.getInstance(editModal).hide()
}


users.onclick = function() {
    boxesDisplayNone();
    foldersActiveOff();
    boxAndFolderSetActive('users');
};


newUser.onclick = function() {
    boxesDisplayNone();
    foldersActiveOff();
    boxAndFolderSetActive('newUser');

    for (let elements of document.getElementsByClassName('form-control')) {
        elements.removeAttribute('readonly');
    }

    var divError = document.getElementById('errorPOST');
    divError.classList.remove('alert-success');
    divError.classList.remove('alert-danger');
    divError.innerHTML = '';
};


function foldersActiveOff(){
    let elements = document.getElementsByName('folder')
    for( let i = 0; i < elements.length; i++){
        elements[i].classList.remove("active");
    }
}


function boxesDisplayNone(){
    let elements = document.getElementsByName('box')
    for( let i = 0; i < elements.length; i++){
        elements[i].style.display = 'none';
    }
}


function boxAndFolderSetActive(name){
    document.getElementById(name + 'Box').style.display = 'block';
    document.getElementById(name).classList.add("active");
}


function getAllUsers() {

    fetch('http://localhost:8080/api/users')
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Status Code: ' + response.status);
                    return;
                }

                response.json().then(function(data) {

                    var result = '';

                    for(var i in data){
                        result += '<tr>' +
                            '<td>' + data[i].id + '</td>' +
                            '<td>' + data[i].name + '</td>' +
                            '<td>' + data[i].age + '</td>' +
                            '<td>' + data[i].mail + '</td>' +
                            '<td>' + rolesToString(data[i].roles) + '</td>' +
                            '<td><input class="btn btn-info" data-bs-id="' + data[i].id + '"' +
                                        'data-bs-toggle="modal" data-bs-target="#editModal"' +
                                        'data-bs-delete="false"' +
                                        'type="button" value="Edit"></td>' +
                            '<td><input class="btn btn-danger" data-bs-id="' + data[i].id + '"' +
                                        'data-bs-delete="true"' +
                                        'data-bs-toggle="modal" data-bs-target="#editModal"' +
                                        'type="submit" value="Delete"></td>'+
                            '</tr>';
                    }

                    document.getElementById("userList").innerHTML = result;

                });
            }
        )
}


function rolesToString(roles) {

    var result = ''

    for(var i in roles){
        result += roles[i].name + ' '
    }
    return result
}


const submitNewUserJson = function() {
    const formData = new FormData(document.getElementById("newUserForm"));
    sendJson(createJson(formData),'POST');
}


function createJson(formData){
    let object = {};
    var cnt = 0;

    for (var pair of formData.entries()) {
        if(pair[0]=='roles'){
            if(!object['roles']){
                object['roles'] = [];
            }
            object['roles'][cnt] = {};
            object['roles'][cnt++]['id'] = pair[1];
        }
        else{
            object[pair[0]] = pair[1];
        }
    }
    return JSON.stringify(object);
}


async function sendJson(data, sendMethod) {

    const response = await fetch('http://localhost:8080/api/user', {
        method: sendMethod,
        body: data,
        headers: {'Content-Type': 'application/json'}
    });
    const json = await response.json();

    var divError = document.getElementById('error' + sendMethod);

    if(divError != null) {

        if (response.status != 200) {
            divError.classList.remove('alert-success')
            divError.classList.add('alert-danger')
            divError.innerHTML = json.error;
        } else {
            divError.classList.remove('alert-danger')
            divError.classList.add('alert-success')
            divError.innerHTML = 'User successfully added';
            document.getElementById('newUserForm').reset();
        }
    }

    getAllUsers();
}


submit.addEventListener("click", submitNewUserJson);


function getUser(id){

    fetch('http://localhost:8080/api/user/' + id)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Status Code: ' + response.status);
                    return;
                }

                response.json().then(function(data) {

                    document.getElementById("editUserForm").reset();
                    resetSelectElement(document.getElementById('selectRole'));

                    document.getElementById("id").value = data.id;
                    document.getElementById("name").value = data.name;
                    document.getElementById("age").value = data.age;
                    document.getElementById("mail").value = data.mail;

                    data.roles.forEach((value, index) => document.getElementById(value.name)
                                                                        .setAttribute('selected', ''))
                });
            }
        )
}


function resetSelectElement(selectElement) {
    var options = selectElement.options;
    for (var i = 0; i < options.length; i++) {
        options[i].removeAttribute('selected');
    }
}

editButton.onclick = function () {
    const formData = new FormData(document.getElementById("editUserForm"));
    sendJson(createJson(formData),'PATCH');
}

deleteButton.onclick = function () {
    const formData = new FormData(document.getElementById("editUserForm"));
    sendJson(createJson(formData),'DELETE');
    closeEditModal()
}

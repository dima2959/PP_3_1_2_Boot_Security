
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
    }else{
        document.getElementById('ModalLabel').innerHTML = 'Edit user';
    }

    document.getElementById('myIframe').src = 'http://localhost:8080/admin/' + id;

})

editModal.addEventListener('hidden.bs.modal', function (event) {
    getAllUsers();
})

function closeEditModal() {
    bootstrap.Modal.getInstance(editModal).hide()
}

// ------ Navigation --------

users.onclick = function() {
    boxesDisplayNone();
    foldersActiveOff();
    boxAndFolderSetActive('users');
};

newUser.onclick = function() {
    boxesDisplayNone();
    foldersActiveOff();
    boxAndFolderSetActive('newUser');
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

//----- REST -----

function getAllUsers() {

    fetch('http://localhost:8080/api/users')
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
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
                            '<td><input class="btn btn-danger" data-bs-id="' + data[i].id + '/delete"' +
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

const toJson = function() {

    const formData = new FormData(document.getElementById("newUserForm"));

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

    let json = JSON.stringify(object);

    send(json);

};

async function send(data) {

    const response = await fetch('http://localhost:8080/api/user', {
        method: 'POST',
        body: data,
        headers: {'Content-Type': 'application/json'}
    });
    const json = await response.json();

    var divError = document.getElementById('error');

    if(response.status!=200){
        divError.classList.remove('alert-success')
        divError.classList.add('alert-danger')
        divError.innerHTML = json.error;
    }else{
        divError.classList.remove('alert-danger')
        divError.classList.add('alert-success')
        divError.innerHTML = 'User successfully added';
        document.getElementById('newUserForm').reset();
    }

    if(response.status==200) {
        getAllUsers();
    }
}

submit.addEventListener("click", toJson);
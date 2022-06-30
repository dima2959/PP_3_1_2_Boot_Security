const editModal = document.getElementById('editModal')

editModal.addEventListener('show.bs.modal', function (event) {

    var button = event.relatedTarget
    var id = button.getAttribute('data-bs-id')
    var del = button.getAttribute('data-bs-delete')

    if (del){
        document.getElementById('ModalLabel').innerHTML = 'Delete user';
    }

    document.getElementById('myIframe').src = 'http://localhost:8080/admin/' + id;

})

editModal.addEventListener('hidden.bs.modal', function (event) {
    location.reload();
})

function closeEditModal() {
    bootstrap.Modal.getInstance(editModal).hide()
}

// ------ Navigation --------

users.onclick = function() {
    boxesDisplayNone()
    foldersActiveOff()
    BoxAndFolderSetActive('users')
};

newUser.onclick = function() {
    boxesDisplayNone()
    foldersActiveOff()
    BoxAndFolderSetActive('newUser')
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
function BoxAndFolderSetActive(name){
    document.getElementById(name + 'Box').style.display = 'block';
    document.getElementById(name).classList.add("active");
}
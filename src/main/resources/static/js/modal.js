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


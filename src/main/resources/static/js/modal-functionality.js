document.addEventListener('DOMContentLoaded', function() {
    const editButtons = document.querySelectorAll('.edit-btn');

    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const userId = this.getAttribute('value');
            const row = button.closest('tr');

            document.getElementById('editUserId').value = userId;

            console.log('Assigned UserId:', document.getElementById('editUserId').value);

            document.getElementById('editName').value = row.cells[1].textContent;
            document.getElementById('editLastName').value = row.cells[2].textContent;
            document.getElementById('editCity').value = row.cells[5 ].textContent;
            document.getElementById('editUsername').value = row.cells[4].textContent;
            document.getElementById('editState').value = row.cells[3].textContent;
            document.getElementById('editZip').value = row.cells[6].textContent;

            $('#editModal').modal('show');
        });
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const deleteButtons = document.querySelectorAll('.delete-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const userId = this.getAttribute('value');
            fetch(`/users/delete/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                if (response.ok) {
                    console.log('Usuario eliminado correctamente');
                    location.reload();
                } else {
                    throw new Error('Failed to delete: ' + response.statusText);
                }
            }).catch(error => {
                console.error('Error:', error);
            });
        });
    });
});


$('.close').on('click', function () {
    $('#editModal').modal('hide');
})

$('.add-btn').on('click', function () {
    window.location.href = 'http://localhost:8080/users/registration';

})

$('#saveChanges').on('click', function(e) {
    e.preventDefault();
    const formData = new FormData(document.getElementById('editUserForm'));

    fetch('/users/update', {
        method: 'POST',
        body: formData
    }).then(response => {
        if (response.ok) {
            return response.json();
            console.log(response.json())
        } else {
            throw new Error('Failed to update: ' + response.statusText);
        }
    }).then(data => {
        $('#editModal').modal('hide');
        console.log('Todo chido')
        location.reload();
    }).catch(error => {
        console.error('Error:', error);
    });
});


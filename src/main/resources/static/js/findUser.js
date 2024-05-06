fetch(`/users/search?userCode=${users_Id}`)
    .then(response => response.text())
    .then(html => {
        const parser = new DOMParser();
        const doc = parser.parseFromString(html, 'text/html');
        const newTableBody = doc.querySelector("table tbody");
        document.querySelector("table tbody").innerHTML = newTableBody.innerHTML;
    })
    .catch(error => console.error('Error:', error))
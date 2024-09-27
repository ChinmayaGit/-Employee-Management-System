const deptBackendUrl = "http://localhost:8090/deloitte-jax-rs-demo/departments";

let currentDeptEditId = null;

window.onload = function() {
    viewAllDepartments();
};

function viewAllDepartments() {
    fetch(deptBackendUrl)
        .then(response => response.json())
        .then(data => {
            let html = "<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Edit</th><th>Delete</th></tr>";
            data.forEach(department => {
                html += `
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td><button class="btn btn-warning" onclick="editDepartment(${department.id}, '${department.name}')">Edit</button></td>
                        <td><button class="btn btn-danger" onclick="deleteDepartment(${department.id})">Delete</button></td>
                    </tr>`;
            });
            html += "</table>";
            document.getElementById("departmentList").innerHTML = html;
        })
        .catch(() => {
            document.getElementById("departmentList").innerHTML = "<p>Error loading departments</p>";
        });
}

function addDepartment() {
    const name = document.getElementById("addDeptName").value;

    if (!name) {
        document.getElementById("addDeptResult").innerText = "Please enter a department name!";
        return;
    }

    const department = { name };
    const method = currentDeptEditId ? 'PUT' : 'POST';
    const url = currentDeptEditId ? `${deptBackendUrl}/${currentDeptEditId}` : deptBackendUrl;

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(department)
    })
        .then(response => response.json())
        .then(() => {
            document.getElementById("addDeptResult").innerText = currentDeptEditId ? "Department updated!" : "Department added!";
            resetDeptForm();
            viewAllDepartments();
        })
        .catch(() => {
            document.getElementById("addDeptResult").innerText = currentDeptEditId ? "Failed to update!" : "Failed to add!";
        });
}

function deleteDepartment(id) {
    fetch(`${deptBackendUrl}/${id}`, { method: 'DELETE' })
        .then(() => viewAllDepartments())
        .catch(() => console.error("Failed to delete department."));
}

function editDepartment(id, name) {
    currentDeptEditId = id;
    document.getElementById("addDeptName").value = name;
}

function resetDeptForm() {
    currentDeptEditId = null;
    document.getElementById("addDeptName").value = '';
}

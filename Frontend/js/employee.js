const employeeBackendUrl = "http://localhost:8090/deloitte-jax-rs-demo/employees";

let currentEmployeeEditId = null;

window.onload = function() {
    viewAllEmployees();
};

function viewAllEmployees() {
    fetch(employeeBackendUrl)
        .then(response => response.json())
        .then(data => {
            let html = "<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Salary</th><th>Dept ID</th><th>Edit</th><th>Delete</th></tr>";
            data.forEach(employee => {
                html += `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.deptId}</td>
                        <td><button class="btn btn-warning" onclick="editEmployee(${employee.id}, '${employee.firstName}', ${employee.salary}, ${employee.deptId})">Edit</button></td>
                        <td><button class="btn btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button></td>
                    </tr>`;
            });
            html += "</table>";
            document.getElementById("employeeList").innerHTML = html;
        })
        .catch(error => {
            document.getElementById("employeeList").innerHTML = "<p>Error loading employees</p>";
        });
}

function addEmployee() {
    const firstName = document.getElementById("addFirstName").value;
    const salary = document.getElementById("addSalary").value;
    const deptId = document.getElementById("addDeptId").value;

    if (!firstName || !salary || !deptId) {
        document.getElementById("addResult").innerText = "Please fill all fields!";
        return;
    }

    const employee = { firstName, salary: parseFloat(salary), deptId: parseInt(deptId) };
    const method = currentEmployeeEditId ? 'PUT' : 'POST';
    const url = currentEmployeeEditId ? `${employeeBackendUrl}/${currentEmployeeEditId}` : employeeBackendUrl;

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(employee)
    })
        .then(response => response.json())
        .then(() => {
            document.getElementById("addResult").innerText = currentEmployeeEditId ? "Employee updated!" : "Employee added!";
            resetEmployeeForm();
            viewAllEmployees();
        })
        .catch(() => {
            document.getElementById("addResult").innerText = currentEmployeeEditId ? "Failed to update!" : "Failed to add!";
        });
}

function deleteEmployee(id) {
    fetch(`${employeeBackendUrl}/${id}`, { method: 'DELETE' })
        .then(() => viewAllEmployees())
        .catch(() => console.error("Failed to delete employee."));
}

function editEmployee(id, firstName, salary, deptId) {
    currentEmployeeEditId = id;
    document.getElementById("addFirstName").value = firstName;
    document.getElementById("addSalary").value = salary;
    document.getElementById("addDeptId").value = deptId;
}

function resetEmployeeForm() {
    currentEmployeeEditId = null;
    document.getElementById("addFirstName").value = '';
    document.getElementById("addSalary").value = '';
    document.getElementById("addDeptId").value = '';
}

const backendUrlEmployees = "http://localhost:8090/deloitte-jax-rs-demo/employees";
const backendUrlDepartments = "http://localhost:8090/deloitte-jax-rs-demo/dept";

// Check which page is loaded to call the relevant function
document.addEventListener("DOMContentLoaded", function() {
    if (window.location.pathname.includes("employees.html")) {
        viewAllEmployees();
    } else if (window.location.pathname.includes("departments.html")) {
        viewAllDepartments();
    }
});

// Fetch and display all employees
function viewAllEmployees() {
    fetch(backendUrlEmployees)
        .then(response => response.json())
        .then(data => {
            let html = `<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Salary</th><th>Department ID</th><th>Actions</th></tr>`;
            data.forEach(employee => {
                html += `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.deptId}</td>
                        <td>
                            <button class="btn btn-warning" onclick="editEmployee(${employee.id})">Edit</button>
                            <button class="btn btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button>
                        </td>
                    </tr>`;
            });
            html += "</table>";
            document.getElementById("employeeList").innerHTML = html;
        })
        .catch(error => console.error('Error:', error));
}

// Fetch and display employees by department ID
function viewEmployeesByDept() {
    const deptId = document.getElementById("deptIdInput").value;
    fetch(`${backendUrlEmployees}/dept/${deptId}/employees`)
        .then(response => response.json())
        .then(data => {
            let html = `<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Salary</th><th>Department ID</th><th>Actions</th></tr>`;
            data.forEach(employee => {
                html += `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.deptId}</td>
                        <td>
                            <button class="btn btn-warning" onclick="editEmployee(${employee.id})">Edit</button>
                            <button class="btn btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button>
                        </td>
                    </tr>`;
            });
            html += "</table>";
            document.getElementById("employeesByDeptList").innerHTML = html;
        })
        .catch(error => console.error('Error:', error));
}

// Add Employee
function addEmployee() {
    const name = document.getElementById("addName").value;
    const salary = document.getElementById("addSalary").value;
    const deptId = document.getElementById("addDeptId").value;

    const employee = {
        name: name,
        salary: salary,
        deptId: deptId
    };

    fetch(backendUrlEmployees, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(employee),
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById("addResult").innerText = "Employee added successfully!";
            viewAllEmployees();  // Refresh the employee list
        })
        .catch(error => console.error('Error:', error));
}

// Fetch and display all departments
function viewAllDepartments() {
    fetch(backendUrlDepartments)
        .then(response => response.json())
        .then(data => {
            let html = `<table class='table table-bordered'><tr><th>ID</th><th>Name</th><th>Location</th><th>Actions</th></tr>`;
            data.forEach(department => {
                html += `
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.location}</td>
                        <td>
                            <button class="btn btn-warning" onclick="editDepartment(${department.id})">Edit</button>
                            <button class="btn btn-danger" onclick="deleteDepartment(${department.id})">Delete</button>
                        </td>
                    </tr>`;
            });
            html += "</table>";
            document.getElementById("departmentList").innerHTML = html;
        })
        .catch(error => console.error('Error:', error));
}

// Add Department
function addDepartment() {
    const name = document.getElementById("addDeptName").value;
    const location = document.getElementById("addDeptLocation").value;

    const department = {
        name: name,
        location: location
    };

    fetch(backendUrlDepartments, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(department),
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById("addDeptResult").innerText = "Department added successfully!";
            viewAllDepartments();  // Refresh the department list
        })
        .catch(error => console.error('Error:', error));
}

// Edit Employee (Functionality to be implemented)
function editEmployee(id) {
    alert(`Edit functionality for Employee ID: ${id} is not implemented yet.`);
}

// Delete Employee
function deleteEmployee(id) {
    fetch(`${backendUrlEmployees}/${id}`, {
        method: 'DELETE',
    })
        .then(() => {
            viewAllEmployees();  // Refresh the employee list
        })
        .catch(error => console.error('Error:', error));
}

// Edit Department (Functionality to be implemented)
function editDepartment(id) {
    alert(`Edit functionality for Department ID: ${id} is not implemented yet.`);
}

// Delete Department
function deleteDepartment(id) {
    fetch(`${backendUrlDepartments}/${id}`, {
        method: 'DELETE',
    })
        .then(() => {
            viewAllDepartments();  // Refresh the department list
        })
        .catch(error => console.error('Error:', error));
}

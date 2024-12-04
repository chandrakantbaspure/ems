const API_BASE_URL = 'http://localhost:8082/api/employees';

function fetchData(url, onSuccess, onError) {
    fetch(url)
        .then(response => response.json())
        .then(onSuccess)
        .catch(onError);
}

function fetchAllEmployees() {
    fetchData(
        API_BASE_URL,
        data => {
            if (data.length > 0) {
                showData(data);
            } else {
                console.error('No employees found.');
            }
        },
        error => {
            console.error('Error fetching all employees:', error);
        }
    );
}

function fetchEmployeeById(id) {
    fetchData(
        `${API_BASE_URL}/fetchEmployee/${id}`,
        data => showData([data]),
        error => console.error(`Error fetching employee with ID ${id}:`, error)
    );
}

function fetchEmployeesByAgeRange(minAge, maxAge) {
    fetchData(
        `${API_BASE_URL}/findbetweenage/${minAge}/${maxAge}`,
        data => showData(data),
        error => console.error(`Error fetching employees between ages ${minAge} and ${maxAge}:`, error)
    );
}

function fetchEmployeesByDepartment(department) {
    fetchData(
        `${API_BASE_URL}/department/${department}`,
        data => showData(data),
        error => console.error(`Error fetching employees in Department ${department}:`, error)
    );
}

function fetchEmployeesByJoiningDateRange(startDate, endDate) {
    fetchData(
        `${API_BASE_URL}/filter/${startDate}/${endDate}`,
        data => showData(data),
        error => console.error(`Error fetching employees joined between ${startDate} and ${endDate}:`, error)
    );
}

function deleteEmployeeById(id) {
    fetch(`${API_BASE_URL}/deleteEmployee/${id}`, {method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                console.log(`Employee with ID ${id} successfully deleted.`);
                fetchAllEmployees();
            } else {
                console.error(`Error deleting employee with ID ${id}:`, response.statusText);
            }
        })
        .catch(error => console.error(`Error deleting employee with ID ${id}:`, error));
}


function showData(data) {
    const tableBody = document.getElementById('employeeData');
    tableBody.innerHTML = '';
    data.forEach(employee => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${employee.employeeId}</td>
            <td>${employee.name}</td>
            <td>${employee.age}</td>
            <td>${employee.department}</td>
            <td>${employee.designation}</td>
            <td>$${employee.salary}</td>
            <td>${employee.joiningDate}</td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="deleteEmployeeById(${employee.employeeId})">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function clearInputs() {
    document.querySelectorAll('input').forEach(input => input.value = '');
}

function clearTable() {
    const tableBody = document.getElementById('employeeData');
    tableBody.innerHTML = '';
}
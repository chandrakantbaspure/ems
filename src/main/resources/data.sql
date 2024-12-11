DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
    employee_id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    address VARCHAR(255),
    department VARCHAR(255),
    designation VARCHAR(255),
    salary DOUBLE,
    joining_date DATE
    );
INSERT INTO employee (employee_id,
                       name,
                       age,
                       address,
                       department,
                       designation,
                       salary,
                       joining_date)
VALUES (1, 'Rahul Sharma', 28, 'Delhi', 'IT', 'Software Engineer', 80000.00, '2018-06-15'),
       (2, 'Priya Singh', 25, 'Mumbai', 'HR', 'HR Manager', 75000.00, '2019-03-20'),
       (3, 'Karan Thakur', 32, 'Bangalore', 'Finance', 'Accountant', 70000.00, '2017-12-10'),
       (4, 'Neha Jain', 29, 'Hyderabad', 'Marketing', 'Marketing Executive', 65000.00, '2019-09-05'),
       (5, 'Vikram Kumar', 35, 'Chennai', 'Operations', 'Team Lead', 90000.00, '2016-07-25'),
       (6, 'Shweta Verma', 27, 'Kolkata', 'HR', 'Recruiter', 60000.00, '2020-01-15'),
       (7, 'Ajay Singh', 31, 'Ahmedabad', 'IT', 'Senior Developer', 85000.00, '2018-11-01'),
       (8, 'Sakshi Mehra', 26, 'Pune', 'Finance', 'Financial Analyst', 70000.00, '2019-08-10'),
       (9, 'Rohan Joshi', 34, 'Jaipur', 'Marketing', 'Brand Manager', 75000.00, '2017-04-20'),
       (10, 'Anushka Roy', 28, 'Lucknow', 'Operations', 'Project Manager', 80000.00, '2019-06-15'),
       (11, 'Tanvi Gupta', 25, 'Surat', 'HR', 'HR Executive', 55000.00, '2020-03-01'),
       (12, 'Devanshu Mittal', 32, 'Indore', 'IT', 'Tech Lead', 95000.00, '2016-10-15'),
       (13, 'Priya Dubey', 27, 'Bhopal', 'Finance', 'Accountant', 65000.00, '2019-09-05'),
       (14, 'Kabir Khan', 30, 'Patna', 'Marketing', 'Digital Marketing Specialist', 70000.00, '2018-07-10'),
       (15, 'Siddharth Tiwari', 29, 'Raipur', 'Operations', 'Quality Assurance Engineer', 75000.00, '2020-01-15');

# Student Management System 🎓

A Java-based application for managing student records with MySQL database integration.

## Features ✨

- **CRUD Operations**
  - ➕ Add new students
  - 📃 Display all students
  - ❌ Delete students by PRN
  - ✏️ Update student details
- **Search Functionality**
  - 🔍 Search by PRN (exact match)
  - 🔎 Search by name (partial match)
- **Database Integration**
  - MySQL backend storage
  - Prepared statements for security
- **Error Handling**
  - SQL exception handling
  - Input validation

## Requirements 📋

- Java JDK 11+
- MySQL Server 8+
- MySQL Connector/J 8.0.23+

## 🛡️ Error Handling

### Error Types and Handling

| Error Type                | Handling Mechanism                          | Example Message                          |
|---------------------------|---------------------------------------------|------------------------------------------|
| **SQL Exceptions**         | Try-catch blocks with SQLException handling | `Database error: Connection refused`     |
| **Invalid Input**          | InputMismatchException handling             | `Invalid input! Enter numbers only`      |
| **Database Connection**    | Connection validation in try-with-resources | `Connection failed: Access denied`       |
| **Resource Leaks**         | finally block for connection closure        | `Always closes database connections`     |
| **Null Results**           | Empty result set checks                     | `No student found with PRN: 12345`       |

## 🛠️ Operations Table

| Operation Name       | Description                                  | SQL Command                            | Input Parameters                  | Output                                  |
|----------------------|----------------------------------------------|----------------------------------------|-----------------------------------|-----------------------------------------|
| **Add Student**      | Inserts new student record                   | `INSERT INTO student VALUES (?,?,?,?,?)` | PRN, Name, Branch, Batch, CGPA    | Success message or error                |
| **Display All**      | Shows all student records                    | `SELECT * FROM student`                | None                              | Formatted list of students              |
| **Delete Student**   | Removes student by PRN                       | `DELETE FROM student WHERE PRN=?`      | PRN                               | Deletion confirmation/error             |
| **Update Student**   | Modifies existing student details            | `UPDATE student SET ... WHERE PRN=?`   | PRN + new details                 | Update confirmation/error               |
| **Search by PRN**    | Finds student using exact PRN match          | `SELECT * FROM student WHERE PRN=?`    | PRN                               | Student details or "Not found" message  |
| **Search by Name**   | Finds students using name substring          | `SELECT * FROM student WHERE name LIKE ?` | Name (partial)                   | Matching records or "No results" message |

### Key Features:
- **Parameterized Queries**: All SQL operations use `PreparedStatement` to prevent SQL injection
- **Input Validation**:
  - PRN must be positive integer
  - CGPA range: 0.0-10.0
  - Non-empty name/branch/batch
- **Search Flexibility**:
  - Exact match for PRN (unique identifier)
  - Partial match for names using `%` wildcards
- **Transaction Safety**: 
  - Auto-commit enabled
  - Explicit connection closure in `finally` blocks

### Example SQL Flow
```sql
/* Add Student */
INSERT INTO student VALUES (101, 'John Doe', 'CS', '2023', 9.2);

/* Search Operation */
SELECT name, branch FROM student WHERE PRN = 101;

/* Update Operation */ 
UPDATE student SET cgpa = 9.5 WHERE PRN = 101;
```

## Result
![image](https://github.com/user-attachments/assets/1097b94c-113f-42e5-91e3-54932a58f82f)
![image](https://github.com/user-attachments/assets/62cfdd41-8724-4cb2-9694-f29962c40262)
![image](https://github.com/user-attachments/assets/2d8e27f4-c4bf-478d-9e49-de306c651527)
![image](https://github.com/user-attachments/assets/bc085bca-0a0e-4bf4-bc21-513439cc2f9d)
![image](https://github.com/user-attachments/assets/60cc0085-95a0-44bf-b2ff-af65a76dc3e9)
![image](https://github.com/user-attachments/assets/ec617de5-a7ed-4dd2-a18d-fe61ef49c4f7)
![image](https://github.com/user-attachments/assets/f9d70761-99c8-453a-99a6-d72a6493b6ea)
![image](https://github.com/user-attachments/assets/c5e5fd6b-8ea8-4937-9bc6-7d0c1b25c58c)








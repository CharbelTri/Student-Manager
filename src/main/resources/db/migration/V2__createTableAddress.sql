CREATE TABLE IF NOT EXISTS address
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(250) NOT NULL,
    student_id int,
    FOREIGN KEY (student_id) REFERENCES student(id)
    );
-- Drop tables if they exist
DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS users;

-- Create tables
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE module (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE registration (
    id SERIAL PRIMARY KEY,
    student_id INT,
    module_code VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (module_code) REFERENCES module(code)
);

CREATE TABLE grade (
    id SERIAL PRIMARY KEY,
    student_id INT,
    module_code VARCHAR(10),
    score DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (module_code) REFERENCES module(code)
);

-- Insert data
INSERT INTO users (username, email, password) VALUES ('user2', 'user2@example.com', 'password2');
INSERT INTO users (username, email, password) VALUES ('user3', 'user3@example.com', 'password3');

INSERT INTO student (first_name, last_name, username, email) VALUES 
('John', 'Doe', 'jdoe', 'jdoe@example.com');

INSERT INTO module (code, name) VALUES 
('CS101', 'Computer Science 101');
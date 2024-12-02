CREATE DATABASE imfinals;
USE imfinals;

CREATE TABLE Product (
    p_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    brand_id INT(11) NOT NULL,  -- Foreign key referencing the Brands table
    model VARCHAR(100) NOT NULL,
    quantity INT(11) NOT NULL,
    price DECIMAL(10,3) NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES Brands(b_id)  -- Foreign key constraint to Brands table
);

CREATE TABLE Brands(
	b_id INT(11) PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(100) NOT NULL
);

CREATE TABLE Staff(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(255) NOT NULL,
	role_ID INT(11) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    FOREIGN KEY (role_ID) REFERENCES roleTbl(role_id)
);

CREATE TABLE roleTbl (
	role_id INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    role_set VARCHAR(255) NOT NULL
);

INSERT INTO Product(category, brand_id, model, quantity, price) VALUES 
("Gaming",1,"LOQ 15IRX9",10,82000),
("Gaming",1,"Legion",6,82000);
INSERT INTO Brands(brand) VALUES ("Lenovo");
DROP TABLE Product;
DROP TABLE roleTbl;
DROP TABLE Staff;

INSERT INTO roleTbl(role_set) VALUES ("Admin"),("Staff");

INSERT INTO Staff(fullname, role_id, `password`, `username`) VALUES ("Jeanne Soriano", 1, "111", "Jea"),("Arvey Pelayo", 2, "111", "Arbee");
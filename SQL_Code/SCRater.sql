DROP DATABASE IF EXISTS SCRater;
CREATE DATABASE SCRater;
USE SCRater;
CREATE TABLE Users (
	userID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    accessLevel INT NOT NULL,
    username VARCHAR(50) NULL,
    pass VARCHAR(50) NULL
);
CREATE TABLE Rating (
	title VARCHAR(50) NOT NULL,
    rdescription VARCHAR(50),
    dateCreated VARCHAR(50),
    numLikes INT,
    numRating DOUBLE NOT NULL,
    privacy BOOLEAN,
    ratingID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userID INT NOT NULL,
    FOREIGN KEY fk1(userID) REFERENCES Users(userID)
);
CREATE TABLE RatedObject (
	title VARCHAR(50) NOT NULL,
    sumRatings DOUBLE,
    averageRating DOUBLE,
    totalRatings INT
);
CREATE TABLE Category (
	title VARCHAR(50) NOT NULL
); 
INSERT INTO Category (title)
	VALUES ('Restaurants'),
    ('Professors'),
    ('Classes'),
    ('Clubs/Student Organizations'),
    ('Events'),
    ('Dorms');

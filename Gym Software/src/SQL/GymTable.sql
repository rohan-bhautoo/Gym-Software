DROP DATABASE Gym;
CREATE DATABASE Gym;
USE Gym;

CREATE TABLE Trainer(
	TrainerID VARCHAR(4),
	Name VARCHAR(40) NOT NULL,
	Sex ENUM('M', 'F') NOT NULL,
	Phone INT(7) NOT NULL,
	PRIMARY KEY (TrainerID)
);

CREATE TABLE Client(
	ClientID VARCHAR(4),
	ClientName VARCHAR(40) NOT NULL,
	Weight INT(3) NOT NULL,
	PRIMARY KEY (ClientID)
);

CREATE TABLE Specialism(
	SpecialismID VARCHAR(4),
	Focus VARCHAR(20) NOT NULL,
	Duration TIME NOT NULL,
	Cost INT(5) NOT NULL,
	PRIMARY KEY (SpecialismID)
);

CREATE TABLE Booking(
	BookingID VARCHAR(4),
	BookingDate DATE NOT NULL,
	StartTime TIME NOT NULL,
	ClientID VARCHAR(4),
	SpecialismID VARCHAR(4),
	TrainerID VARCHAR(4),
	PRIMARY KEY (BookingID),
	FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
	FOREIGN KEY (SpecialismID) REFERENCES Specialism(SpecialismID),
	FOREIGN KEY (TrainerID) REFERENCES Trainer(TrainerID)
);

CREATE TABLE TrainerSpecialism(
	SpecialismID VARCHAR(4),
	TrainerID VARCHAR(4),
	FOREIGN KEY (SpecialismID) REFERENCES Specialism(SpecialismID),
	FOREIGN KEY (TrainerID) REFERENCES Trainer(TrainerID)
);

INSERT INTO Trainer
VALUES ('T001', 'Ralph Holder', 'M', 5123456),
('T002', 'Sami Hulme', 'M', 5348712),
('T003', 'Fiona Miller', 'F', 5699087),
('T004', 'Savana Kelley', 'F', 5490531);

INSERT INTO Client
VALUES ('C001', 'Olivier Dixon', 58),
('C002', 'Jo Rhandall', 85),
('C003', 'Ema Lister', 51),
('C004', 'Jessica Aimes', 47);

INSERT INTO Specialism
VALUES ('S001', 'Muscle Gain', '02:00:00', 1000),
('S002', 'Weight Loss', '01:00:00', 600),
('S003', 'Flexibility', '00:30:00', 350),
('S004', 'Muscle Gain', '01:30:00', 900);

INSERT INTO Booking
VALUES ('B001', '2020-01-25', '09:30:00', 'C001', 'S001', 'T001'),
('B002', '2020-01-25', '11:30:00', 'C002', 'S002', 'T001'),
('B003', '2020-02-01', '18:00:00', 'C001', 'S002', 'T002'),
('B004', '2020-02-01', '19:00:00', 'C003', 'S003', 'T003'),
('B005', '2020-02-02', '08:30:00', 'C004', 'S004', 'T003'),
('B006', '2020-02-02', '17:00:00', 'C004', 'S003', 'T004');

INSERT INTO TrainerSpecialism
VALUES ('S001', 'T001'),
('S002', 'T001'),
('S002', 'T002'),
('S003', 'T003'),
('S003', 'T004'),
('S004', 'T003');

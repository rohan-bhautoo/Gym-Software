<p align="center">
  <img width="200" height="200" src="https://github.com/rohan-bhautoo/Gym-Software/blob/main/gym_software_logo.png">
</p>
<h1 align="center">Gym Software</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-brightgreen.svg" />
  <img alt="Java" src="https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white" />
  <img alt="MariaDB" src="https://img.shields.io/badge/MariaDB-964B00?logo=mariadb&logoColor=white" />
  <img alt="VS_Code" src="https://img.shields.io/badge/Visual_Studio_Code-0078d7?logo=visualstudiocode&logoColor=white" />
  <img alt="JDK" src="https://img.shields.io/badge/JDK->=11.0.14-blue.svg" />
</p>

## Description
> The Gym Software is a network application, based in Java, which utilises multi-threaded programming and is connected to a database. The sofware implementation will enable staff members to add, update, delete and view the bookings of each client.
> 
> With the use of multi-threading, the Gym Software will be accessible to mutiple clients to connect at the same time and interact with the database.

## Prerequisite

### Java Development Kit (JDK) 
> JDK version 11 is used for this project as it includes the JavaFX library. Download it [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
> 
> For Windows:
```sh
set JAVA_HOME="C:\[Path to folder]\Java\jdk-11.0.14
```
> Enter the Environment Vairables in System Properties.
> 
> Add **%JAVA_HOME%\bin** into Path.
```sh
%JAVA_HOME%\bin
```
<p align="center">
  <img height="400" src="https://github.com/rohan-bhautoo/Point-Of-Sales-System/blob/master/Screenshots/Env%20Variable.png">
</p>

## MariaDB
> MariaDB Server is one of the most popular open source relational databases. It’s made by the original developers of MySQL and guaranteed to stay open source. It is part of most cloud offerings and the default in most Linux distributions. Download it [here](https://mariadb.org/download/).
> 
> The five tables below will be used to store information in the database.

<table align="center">
  <tr>
    <th>Trainer</th>
    <th>Client</th>
    <th>Specialism</th>
    <th>Booking</th>
    <th>TrainerSpecialism</th>
  </tr>
  <tr><td>
         
| TrainerID (PK) | 
| -------------- |
| Name           |   
| Sex            |   
| Phone          |
      
</td><td>
      
| ClientID (PK) |
| ------------- |
| ClientName    |
| Weight        |
      
</td><td>
    
| SpecialismID (PK) |
| ----------------- |
| Focus             |
| Duration          |
| Cost              |
    
</td><td>
      
| BookingID (PK)    |
| ----------------- |
| BookingDate       |
| StartTime         |
| ClientID (FK)     |
| SpecialismID (FK) |
| TrainerID (FK)    |
    
</td><td>
    
| SpecialismID (FK) |
| ----------------- |
| TrainerID (FK)    |
    
</td></tr>
</table>

## Software Design

### Server

### Client

### Testing
> Testing shows that the software has meet its requirements and it is printing the expected output.

#### Validation Testing
> Before sending data to the server, the client side must validate the user input. In the gym software, regex expressions are used to validate all user inputs from console.

##### IDs
```java
^[B][0-9]{3}+$
```

> The ^ symbol means the start of the string which is the user input and $ sign is end of string. In the regex above, the string must always start with a capital B and continue with only 3 numeric values.
> 
> Examples:
> 
> Good format: B001, B123, B999 
> 
> Bad format: C001, B0001, JC21, BB01
> 
> The same format is used to validate the trainerID, SpecialismID and ClientID.

##### Date
```java
\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[[01])*
```
> The “\\d{4}” ensures that the user input for year is YYYY. The “(0?[1-9]|1[012])” part checks if user months input is between 01 to 12. And the last part allows only numbers between 01 to 31 to be accepted.

##### Time
```java
([01]?[0-9]|2[0-3]):[0-5][0-9]
```
> It ensures that the time is between 00:00 to 23:59. Even 9:0 is accepted but it will be reconverted to 09:00 in the table booking.

## Usage
> After installing MariaDB, import the table from [SQL](/Gym%20Software/src/SQL/GymTable.sql) folder, to the database, using the following command.
```sh
mysql -u root -p
```

```sh
source C:\[Path to folder]\SQL\GymTable.sql
```

## Author

👤 **Rohan Bhautoo**

* Github: [@rohan-bhautoo](https://github.com/rohan-bhautoo)
* LinkedIn: [@rohan-bhautoo](https://linkedin.com/in/rohan-bhautoo)

## Show your support

Give a ⭐️ if this project helped you!

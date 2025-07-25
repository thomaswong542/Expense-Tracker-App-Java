# Expense-Tracker-App-Java
A Java Desktop Expense Tracker App using JavaFX and spring boot

## Liscense Notice
This Project is for mainly for job application, personal and non-commercial use only, any distribution and modification are not allowed.

## Features
1. Data Filtering
2. Quick access to previous data and fill it in the form by double left clicking the row from the table
3. Same features as no.2 when right clicking the row and click update button for quick form input

## How to use
1. Install PostgreSQL
2. Create a database
3. Create a table using the following SQL
    ```
    CREATE TABLE expense
    (
        id serial NOT NULL,
        date date,
        category character varying(20),
        location character varying(20),
        shop character varying(20),
        item character varying(50),
        card character varying(20),
        quantity integer,
        price numeric(6, 2),
        deleted boolean DEFAULT false,
        PRIMARY KEY (id)
    );
4. Import the project to Intellij
5. Add the following properties in expenseTrackerAPI/src/main/resources/application.properties (The use of environment variables is highly suggested)
    ```
    spring.datasource.url=jdbc:postgresql://localhost:{port_number}/{database_name}
    spring.datasource.username={database_owner}
    spring.datasource.password={PostgreSQL_password}
6. Run expenseTrackerAPI/src/main/java/com/project/expenseTrackerAPI/ExpenseTrackerApiApplication.java first
7. Then run expenseTrackerUI/src/main/java/com/project/expenseTrackerUI/App.java (You can use the preset run configuration)

## Screenshot
<img width="1280" height="720" alt="App Screenshot" src="https://github.com/user-attachments/assets/706fff93-c7ed-4d38-99e6-815b365dc79a" />


ATM Simulator
Project Description
This project implements a simple ATM system simulator. It features a REST API backend developed with Spring Boot and an interactive web frontend built with HTML, CSS (Bootstrap), and JavaScript.

The application allows you to simulate key banking operations:

- Viewing account balances.

- Deposits.

- Withdrawals.

- Bank transfers between accounts.

- Creating new customers.

- Opening new current accounts linked to customers.

The frontend is designed to be intuitive and leverages Bootstrap 5 for a modern, responsive user interface, handling operations through modal popups for an improved user experience.

Technologies Used
Backend:

- Java 17+

- Spring Boot: Framework for building standalone, production-ready Java applications.

- Spring Web: For creating RESTful APIs.

- Spring Data JPA: For data access (you can specify the database if you've configured one, e.g., H2 Database, PostgreSQL, MySQL).

- Maven (or Gradle): Build automation tool for dependency management.

Frontend:

- HTML5: Web page structure.

- CSS3: Custom styling.

- JavaScript (Vanilla JS): Interactive logic and API calls.

- Bootstrap 5: CSS framework for responsive design and UI components (modals, buttons, forms).

System Requirements
To run the project locally, you'll need:

- JDK 17 (or newer)

- Apache Maven (or Gradle, depending on your Spring Boot project setup)

- A modern web browser (Chrome, Firefox, Edge, Safari)

How to Get Started (Local Setup)
Follow these steps to get the ATM simulator running on your machine:

1. Start the Backend (Spring Boot)
Clone the repository (if you haven't already):

Bash

git clone https://github.com/YourGitHubUsername/YourRepositoryName.git
cd YourRepositoryName
Navigate to the backend directory:
Make sure you're in the root of your Spring Boot project (where pom.xml or build.gradle is located).

Compile and run the Spring Boot application:
If you're using Maven:

Bash

mvn spring-boot:run
If you're using Gradle:

Bash

./gradlew bootRun
The backend should start on port 8080 by default.

2. Launch the Frontend
Ensure the backend is running.

In your preferred web browser, directly open your index.html file (or whatever you named it) located in your project's frontend folder (usually src/main/resources/static/).

Example for Windows: file:///C:/Users/YourUser/Documents/YourRepositoryName/src/main/resources/static/index.html

Example for Linux: file:///home/your_user/YourRepositoryName/src/main/resources/static/index.html

Example for macOS: file:///Users/YourUser/YourRepositoryName/src/main/resources/static/index.html

The frontend application will automatically connect to the running backend.

API Endpoints (Backend)
Here are some of the main endpoints exposed by the backend:

GET /api/bancomat/conti/{accountNumber}: Retrieves account details.

PUT /api/bancomat/conti/{accountNumber}/deposito: Performs a deposit.

PUT /api/bancomat/conti/{accountNumber}/prelievo: Performs a withdrawal.

PUT /api/bancomat/bonifico: Executes a bank transfer.

POST /api/bancomat/clienti: Creates a new customer.

POST /api/bancomat/conti: Creates a new bank account.

(Note: Make sure these endpoints exactly match what's implemented in your Spring Boot code.)

How to Contribute
If you'd like to contribute to this project, feel free to:

Fork the repository.

Create a new branch (git checkout -b feature/new-feature).

Implement your changes.

Commit your changes (git commit -m 'Add new feature X').

Push to the branch (git push origin feature/new-feature).

Open a Pull Request.

License
This project is released under the License Name, e.g., MIT License.

# Mobile Market Application
This is a Spring Boot application for managing mobile phones in a virtual mobile market.
# Features
- Create, read, update, and delete (CRUD) operations for mobile phones.
- Validation of mobile phone attributes.
- In-memory storage for simplicity.
# Technologies Used
- Java
- Spring Boot
- Lombok
- Jakarta Bean Validation
# Usage
  - To create a new mobile phone, send a POST request to /mobilephones with a JSON payload containing the mobile phone details.
  - To retrieve a mobile phone by ID, send a GET request to /mobilephones/{id}.
  - To retrieve all mobile phones, send a GET request to /mobilephones.
  - To update an existing mobile phone, send a PUT request to /mobilephones with a JSON payload containing the updated mobile phone details.
  - To delete a mobile phone by ID, send a DELETE request to /mobilephones/{id}

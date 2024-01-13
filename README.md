# Task1_Resr_API
Here's a description of the code provided:

TaskManagerApplication:
This class contains the main method and serves as the entry point for the Spring Boot application.
It's annotated with @SpringBootApplication, indicating that it is a Spring Boot application.

Task:
An entity class representing a task with properties such as id, name, assignee, project, startTime, and rajeshSinghProperty.
The generateRajeshSinghProperty method generates a random 5-character string from the source string "RajeshSingh."

TaskRepository:
An interface that extends MongoRepository for CRUD operations on Task entities.
Includes custom query methods for finding tasks by name and assignee.

TaskService:
A service class that contains business logic for managing tasks.
Implements methods for getting all tasks, getting a task by ID, creating a task, deleting a task, and finding tasks by name or assignee.

TaskController:
A REST controller that handles HTTP requests related to tasks.
Defines endpoints for getting all tasks, getting a task by ID, creating a task, deleting a task, and finding tasks by name or assignee.

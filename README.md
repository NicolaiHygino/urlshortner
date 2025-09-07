# URL Shortener

This is a URL shortener application built with a Java + Spring Boot backend and a React + TypeScript frontend.

## Tech Stack

### Backend

- Java 17
- Spring Boot
- Gradle
- PostgreSQL
- Redis
- RabbitMQ
- Flyway
- Lombok

### Frontend

- React
- TypeScript
- Vite
- ESLint

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Installation & Running

1.  Clone the repository:
    ```sh
    git clone https://github.com/your-username/url-shortener.git
    cd url-shortener
    ```

2.  Run the application using Docker Compose:
    ```sh
    docker-compose up -d
    ```

The application will be available at the following URLs:

-   **Frontend:** [http://localhost:3000](http://localhost:3000)
-   **Backend:** [http://localhost:8080](http://localhost:8080)

## API Documentation

The API documentation is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) once the backend is running.

## Running Tests

### Backend

To run the backend tests, navigate to the `backend` directory and run:

```sh
./gradlew test
```

### Frontend

To run the frontend tests, navigate to the `frontend` directory and run:

```sh
npm test
```

## Deployment

This application is configured for deployment using Docker. You can build the Docker images for the backend and frontend and deploy them to your preferred cloud provider.

## Contributing

Contributions are welcome! Please feel free to submit a pull request.

## License

This project is licensed under the MIT License.

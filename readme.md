# The Dashboard Project
## Backend Setup
### Gradle and Spring setup
#### To build project
`gradle build`
#### To run the project with Spring
`gradle bootRun` or `./gradlew bootRun`   
The gradlew script allow to run the project without having to install Gradle on your machine.
#### Build .war
`gradle bootWar` or `./gradlew bootWar`

### MongoDB setup
#### 1. Install mongoDB
`sudo pacman -Sy mongodb jq`
#### 2. Setup user and create database
Inside dashboard-backend/
- `sudo systemctl start mongodb.service`
- `sudo systemctl enable mongodb.service`
- `mkdir -p data/db`
- In one terminal (Inside Docker): `mongod --port 21087 --dbpath data/db/`
- In another terminal (Outside Docker): `mongosh --host localhost --port 21087 < data/documents/db.setup`
- CTRL+C on mongod terminal.

To change with Docker
--host: Address to Mongod
--port: Forwarded Docker port 
#### 3. Starting MongoDB
Inside Docker:
- `mongod --auth --port 21087 --dbpath data/db`

## Routes
### /user
- GET /users - Returns all users in database
- GET /user/email/{email} - Returns the specific user with the given email- 
- GET /user/id/{id} - Returns the specific user with the given id.
- POST /user - Create a user in the database.
- PATCH /user/email/{email} - Updates the user with the given email.
- PATCH /user/email/{id} - Updates the user with the given id.
- DELETE /user/email/{email} - Deletes the user with the given email.
- DELETE /user/ud/{id} - Deletes the user with the given id.

# YOUR NAME – MATH WHIZ

A beginner-friendly Java Servlet/JSP mathematical game using Apache Tomcat, JDBC and MySQL.

## Features
- Four randomly generated questions: prime, modulus and two trigonometry questions.
- Server-side Java calculations.
- Score out of 10 (2 + 2 + 3 + 3).
- Saves player, score, difficulty and timestamp in MySQL.
- Top 10 leaderboard.

## Technologies
Java 21+, Jakarta Servlet 6, JSP, Apache Tomcat 10.1, Maven, MySQL, JDBC.

## Requirements
- JDK 21 or later
- IntelliJ IDEA Ultimate (or another Maven IDE)
- Apache Tomcat 10.1
- MySQL Server

## Database setup
1. Open MySQL Workbench or IntelliJ Database.
2. Run `database/math_whiz_db.sql`.
3. Open `src/main/java/com/mathwhiz/dao/DBConnection.java`.
4. Change `PASSWORD` to your MySQL root password.

## Run in IntelliJ
1. Open this folder as a Maven project.
2. Set Project SDK and Maven importer JDK to JDK 21 or later.
3. Wait for Maven dependencies to download.
4. Add a Tomcat 10.1 local server configuration.
5. Set deployment artifact to `MathWhiz:war exploded`.
6. Set application context to `/MathWhiz`.
7. Run Tomcat and open `http://localhost:8080/MathWhiz/`.

## Scoring
- Prime answer: 2 marks
- Modulus answer: 2 marks
- Trigonometry answer 1: 3 marks
- Trigonometry answer 2: 3 marks
- Trigonometry answers are accepted within a tolerance of 0.01.
- Difficulty is currently NORMAL.

## Project structure
- `controller`: servlet request handling
- `service`: mathematical methods
- `dao`: JDBC and database queries
- `model`: question and score classes
- `src/main/webapp`: JSP pages and CSS
- `database`: SQL setup script

## Screenshots
Create a `screenshots` folder and add your own screenshots:
- `home.png`
- `game.png`
- `result.png`
- `leaderboard.png`

## GitHub
After creating your repository, update this section with your project subdirectory URL.

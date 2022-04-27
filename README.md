# Restaurant Voting Application

---
### _A voting system REST API_
- Stack: [JDK 17](http://jdk.java.net/17/), Spring Boot 2.5, Lombok, HSQLDB, Swagger/OpenAPI 3.0 <br>

Credentials:
|Username|Password|
|---|---|
| `user1@yandex.ru` | `password` |
| `admin@gmail.com` | `admin` |

---
### _Terms of reference_
Voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed.

Each restaurant provides new menu each day.

-------------------------------------------------------------
### _Steps to launch app_
- Run: `mvn spring-boot:run` in root directory.
- Open in browser: [localhost:8080](http://localhost:8080)<br>
-----------------------------------------------------

### _REST Api Documentation:_<br>
[_Api docs_](http://localhost:8080/v3/api-docs) <br>
[_Swagger-ui_](http://localhost:8080/swagger-ui.html) <br>

---
[Список моих пет-проектов на Github page](https://kvostok.github.io/my-pet-projects/)

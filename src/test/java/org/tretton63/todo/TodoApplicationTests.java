package org.tretton63.todo;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.tretton63.todo.interfaces.requests.NewTodoItemRequest;

import java.net.URI;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests {
    @LocalServerPort
    int serverPort;

    @Test
    void contextLoads() {
    }


    @Test
    void getRequestHappyPath() {
        given()
                .port(serverPort)
                .auth()
                .basic("user1", "test")
                .when()
                .get("/todo/")
                .thenReturn();

    }


    @Test
    void postHappyPath() {
        NewTodoItemRequest requestBody = new NewTodoItemRequest("Hello, World");

        given()
                .port(serverPort)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .auth()
                .basic("user2", "test")
                .when()
                .post(URI.create("/todo/"))
                .then().statusCode(201);
    }
}

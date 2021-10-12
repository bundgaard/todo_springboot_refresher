package org.tretton63.todo;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Test;
import org.tretton63.todo.interfaces.requests.NewTodoItemRequest;

import java.net.URI;

import static io.restassured.RestAssured.given;


class TodoApplicationTests extends ComponentTest{
    @Test
    void contextLoads() {
    }

    @Test
    void getRequestHappyPath() {
        given(requestForUser1())
                .when()
                .get("/todo/")
                .thenReturn();

    }


    @Test
    void postHappyPathAndRetrieveValueBack() {
        /*
        * POST a request with body to server
        * Validate response to be equal to 201
        *
        * Then test if the location we received is GETable
        * and validate the status code to be 200
        * */
        NewTodoItemRequest requestBody = new NewTodoItemRequest("Hello, World");

        ExtractableResponse response = given(requestForUser1())
                .body(requestBody)
                .when()
                .post(URI.create("/todo/"))
                .then()
                .statusCode(201)
                .extract();

        String location = response.header("Location");
        System.out.printf("Trying to receive the value from the server %s\n", location);

        given(requestForUser1())
                .when()
                .get(location)
                .then()
                .statusCode(200);
    }
}

package org.tretton63.todo;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.tretton63.todo.domain.TodoItem;
import org.tretton63.todo.interfaces.requests.NewTodoItemRequest;

import java.net.URI;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

class TodoApplicationTests extends ComponentTest {
    @Test
    void contextLoads() {
    }

    @Test
    void getRequestHappyPath() {
        given(requestForUser1())
                .when()
                .get("/todo/")
                .then().statusCode(HttpStatus.OK.value());

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

        ExtractableResponse<Response> response = given(requestForUser1())
                .body(requestBody)
                .when()
                .post(URI.create("/todo/"))
                .then()
                .statusCode(201)
                .extract();

        String location = response.header("Location");
        System.out.printf("Trying to receive the value from the server %s\n", location);

        TodoItem item = given(requestForUser1())
                .when()
                .get(URI.create(location))
                .then()
                .statusCode(200).extract().as(TodoItem.class);

        assertThat(item.getValue()).isEqualTo("Hello, World");
    }
}

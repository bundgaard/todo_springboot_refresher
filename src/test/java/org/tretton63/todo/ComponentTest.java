package org.tretton63.todo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.basic;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComponentTest {

    @LocalServerPort
    int serverPort;

    protected RequestSpecBuilder  baseSpecBuilder() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setPort(serverPort);
    }
    protected RequestSpecification authenticationSpec(String username, String password) {
        return baseSpecBuilder()
                .setAuth(basic(username, password))
                .build();
    }

    protected RequestSpecification requestForUser1() {
        return authenticationSpec("user1", "test");
    }


    protected RequestSpecification requestForUser2() {
        return authenticationSpec("user2", "test");
    }

    protected RequestSpecification noAuthentication() {
        return baseSpecBuilder().build();
    }

}

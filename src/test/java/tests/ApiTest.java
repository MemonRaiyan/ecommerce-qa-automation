
package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void getPostReturnsExpectedData() {

        Response response =
            given()
            .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
                .extract()
                .response();

        // Check the HTTP status code.
        Assert.assertEquals(response.statusCode(), 200);

        // Check important fields in the JSON response.
        Assert.assertEquals(
            response.jsonPath().getInt("id"), 1
        );

        Assert.assertNotNull(
            response.jsonPath().getString("title")
        );

        Assert.assertNotNull(
            response.jsonPath().getString("body")
        );

        System.out.println("API status: " + response.statusCode());
        System.out.println("Post title: "
            + response.jsonPath().getString("title"));
    }
     @Test
    public void nonexistentPostReturnsNotFound() {

        Response response =
            given()
            .when()
                .get("https://jsonplaceholder.typicode.com/posts/99999")
            .then()
                .extract()
                .response();

        Assert.assertEquals(
            response.statusCode(),
            404,
            "A nonexistent post should return HTTP 404"
        );

        System.out.println(
            "PASS: Nonexistent post returns HTTP 404."
        );
    }
}

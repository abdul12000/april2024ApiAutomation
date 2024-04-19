package restAssuredTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class SocialNetworkingPost {

    @Test
    public void getAllPost(){
       given().log().all().contentType(ContentType.JSON).when().get("https://jsonplaceholder.typicode.com/posts").then().log().all().statusCode(200);
    }

    @Test
    public void getASpecificPost(){
        given().log().all().contentType(ContentType.JSON).when().get("https://jsonplaceholder.typicode.com/posts/2").
                then().log().all().statusCode(200).body("title", equalTo("qui est esse"));
    }
@Test
    public void createANewPost() {
        HashMap<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("userId", "100404");
        bodyForPost.put("title", "My trip to Johannesburg");
        bodyForPost.put("body", "I enjoyed my stay in Japan . it was very beautiful bla bla bl bla bla a");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).
                when().post("https://jsonplaceholder.typicode.com/posts").
                then().log().all().statusCode(201).body("title", equalTo("My trip to Johannesburg"));
    }
    @Test
    public void UpdateAPostUsingPut() {
        HashMap<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("userId", "100404");
        bodyForPost.put("title", "My trip to Johannesburg");
        bodyForPost.put("body", "I enjoyed my stay in Japan .It was a waste og our time");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).
                when().put("https://jsonplaceholder.typicode.com/posts/2").
                then().log().all().statusCode(200).body("title", equalTo("My trip to Johannesburg"));
    }

    @Test
    public void UpdateAPostUsingPatch() {
        HashMap<String, String> bodyForPost = new HashMap<>();
        bodyForPost.put("title", "My trip to Johannesburg");
        bodyForPost.put("body", "I enjoyed my stay in Japan .It was a waste og our time");

        given().log().all().contentType(ContentType.JSON).body(bodyForPost).
                when().patch("https://jsonplaceholder.typicode.com/posts/3").
                then().log().all().statusCode(200).body("title", equalTo("My trip to Johannesburg"));
    }
    @Test
    public void deleteASpecificPost(){
        given().log().all().contentType(ContentType.JSON).when().delete("https://jsonplaceholder.typicode.com/posts/2").
                then().log().all().statusCode(200);
    }


}

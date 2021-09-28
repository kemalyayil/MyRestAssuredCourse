import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestUserTest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "https://gorest.co.in/public/v1/users";

    }


    @Test
    public void goRestUserTests(){

        RequestSpecification requestSpecification = given()
                .log().uri()
                .header("Authorization", "Bearer 4a0eee6d56180a8378fa56c1acace9b6c9422edab892bf281ecd37dc9876c019")
                .contentType(ContentType.JSON);

        ResponseSpecification defaultResponseSpecifications = given().then().statusCode(200).contentType(ContentType.JSON);

        Map<String, String> body = new HashMap<>();
        body.put("name", "TestUser TechnoStudy");
        body.put("email", "technoo242TestUser@mersys.com");
        body.put("gender", "male");
        body.put("status", "active");

        // Create User Test
        Object id =
                given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post()
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("data.id");


        // Create Duplicate User Test
        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post()
                .then()
                .log().body()
                .statusCode(422);



        // Edit User Test
        String newName = "New TechnoUserName";

        Map<String, String> editUserBody = new HashMap<>();
        editUserBody.put("name", newName);

        given()
                .spec(requestSpecification)
                .body(editUserBody)
                .when()
                .put("/"+ id)
                .then()
                .spec(defaultResponseSpecifications)
                .log().body()
                .body("data.name", equalTo(newName));

    }

}

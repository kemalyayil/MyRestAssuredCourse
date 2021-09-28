
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://api.zippopotam.us";       // bunu son derste yaptik. baseUrl. asagidaki linkleri kisaltti
                                                            // ayrica , herhangi bi degisiklikte, sadece burayi degistirdigimiz
                                                            // zaman kisayoldan hallelmis oluyor
    }

    @Test
    public void test(){
        given().when().then();          // main structure
    }

    @Test
    public void testingStatusCode() {
        given().                        // request body
                log().uri().
                when().
                get("us/90210").
                then().                 // after then , we will do assertion by statusCode
                statusCode(200);
    }

    @Test
    public void loggingRequest() {
        given()
                .log().all()        // all details about the request will be shown via "log().all()". It should be after "given"
                .when()
                .get("/us/90210")
                .then();

    }



    @Test
    public void loggingResponse() {
        given()

                .when()
                .get("/us/90210")
                .then()
                .log().all() ;          // if wanna get all details about response, we are to put logAll here.

    }

    @Test
    public void loggingResponse2() {
        given()

                .when()
                .get("/us/90210")
                .then()
                .log().body() ;          // if wanna get the detail about body, we are to put logBody here.

    }

    @Test
    public void testingContentType() {
        given()

                .when()
                .get("/us/90210")
                .then()
                .contentType(ContentType.JSON) ;    // to be sure and check if the content type is json
    }

    @Test
    public void checkCountryTest() {
        given()

                .when()
                .get("/us/90210")
                .then()
                .body("country",equalTo("United States"))    // to be sure and check if the country is same
                .log().body();
    }

    @Test
    public void checkCountryTest2() {
        given()

                .when()
                .get("/us/90210")
                .then()
                //.body("country abbreviation",equalTo("US"))               // iki kelime arasinda space olursa hata verir.
                .body("'country abbreviation'",equalTo("US"))   // bunun ustesinden gelmek icin ' ' kullanimali
                .log().body();
    }

    @Test
    public void checkPlacesTest() {
        given()

                .when()
                .get("/us/90210")
                .then()
                .body("places",hasSize(1))
                .body("places.'place name'",hasItem("Beverly Hills"))
                .body("places[0].state",equalTo("California"))
                .log().body();
    }
    @Test
    public void pathParametersTest(){
        String country = "us";
        String zipCode = "90210";

        given()
                .pathParam("country",country)
                .pathParam("zipCode",zipCode)
                .when()
                .get("/{country}/{zipCode}")
                .then()
                .log().body();
    }

    @Test
    public void queryParametersTest(){
        String gender = "male";
        String status = "inactive";
        given()
                .param("gender", gender)                // bu ve bi altta ki filtrelemeye yariyor.
                .param("status", status)
                .when()
                .get("https://gorest.co.in/public/v1/users") // baseUrl imiz vardi , ama burda basta "https" ile basladigimiz icin , onun yeni URL oldugunu anlar ve BaseUrl i kullanmaz
                .then()
                .log().body();
    }

    @Test
    public void extractingValueTestCountry(){       // extract ing kullanilirken, o bilgiyi saklayacagimiz bi variable olmasi gerekir.
       Object countryName =  given()    // buradaki o data'yi saklayan variable.

                .when()
                .get("/us/71343")
                .then()
                .extract().path("country");
        System.out.println(countryName);
    }

    @Test
    public void extractingValueTestState(){
        Object stateName =  given()

                .when()
                .get("/us/71343")
                .then()
                .extract().path("places[0].state");
        System.out.println(stateName);
    }

}

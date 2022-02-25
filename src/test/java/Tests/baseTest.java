package Tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class baseTest {
    public static RequestSpecification requestSpec;
    @BeforeClass
    public static void setup() {
        // Setting BaseURI once
        RestAssured.baseURI = "https://apichallenges.herokuapp.com";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("X-Challenger", "cf8ca5d8-8155-458f-82ba-194cd60426df");
        requestSpec = builder.build();

    }
}

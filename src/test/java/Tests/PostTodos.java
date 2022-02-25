package Tests;

import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class PostTodos extends baseTest{
    @BeforeClass
    public static void test(){
        baseTest base = new baseTest();
        base.setup();

    }
    @DisplayName("Post Todo in XML")
    @Test
    public void postTodosXML(){
        boolean doneStatus = true;
        JSONObject object = new JSONObject();
        object.put("title","hello motor");
        object.put("doneStatus",doneStatus);

        given().spec(requestSpec).header("Accept","application/xml").body(object.toJSONString()).
                contentType("application/xml").
                when().post("/todos").then().log().all().
                assertThat().contentType("application/xml");
    }
    @DisplayName("Post Todo in JSON")
    @Test
    public void postTodosJSON(){
        boolean doneStatus = true;
        JSONObject object = new JSONObject();
        object.put("title","hello motor");
        object.put("doneStatus",doneStatus);

        given().spec(requestSpec).header("Accept","application/json").body(object.toJSONString()).
                contentType("application/xml").
                when().post("/todos").then().log().all().
                assertThat().contentType("application/json");
    }
    @DisplayName("Post Todo in JSON")
    @Test
    public void deleteHeartBeat(){


        given().spec(requestSpec).

                get("/heartbeat").then().log().all().
                assertThat().statusCode(204);
    }
    @DisplayName("post With Json File")
    @Test
    public void postWithJsonFile() throws IOException {

        JSONObject obj = new JSONObject();
        obj.put("title","yes");
        obj.put("doneStatus",true);

        File jsonData = new File("src/test/java/Resources/test.json");

        byte[] b = Files.readAllBytes(Paths.get("src/test/java/Resources/test.json"));

        //convert byte array to string
        String bdy = new String(b);
        Response respose =  given().spec(requestSpec).
                body(jsonData).when().post("/todos").then().log().all()
                .extract().response();
        String title = respose.getBody().asString();

        Assert.assertEquals(bdy.contains(title),false);


    }
}

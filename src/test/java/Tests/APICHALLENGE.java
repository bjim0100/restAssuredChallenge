/*
 * Author: Obeng Agyenim Boateng Boateng
 * https://apichallenges.herokuapp.com/gui/challenges/cf8ca5d8-8155-458f-82ba-194cd60426df
 *
 * */

package Tests;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.equalTo;


//cf8ca5d8-8155-458f-82ba-194cd60426df
public class APICHALLENGE {
    @BeforeClass
    public static void setup() {
        // Setting BaseURI once
        RestAssured.baseURI = "https://apichallenges.herokuapp.com";

    }

    @DisplayName("Post challenge")
    @Test
    public void postChallenger(){
        given().post("/challenger").
                then().statusCode(201).log().all();

    }

    @DisplayName("Get challenge")
    @Test
    public void getChallenger(){
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df")
        .get("/challenges").
                then().statusCode(200).contentType("application/json").
//                header("X Challenger",equalTo("cf8ca5d8-8155-458f-82ba-194cd60426df")).
                body("challenges[0].id",equalTo("01")).log().all();

    }

    @DisplayName("Get Todos 200")
    @Test
    public void getTodos(){
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df")
                .get("/todos").
                then().statusCode(200).contentType("application/json");

    }

    @DisplayName("Get Todos 404")
    @Test
    public void getTodo(){
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df")
                .get("/todo").
                then().statusCode(404).contentType("text/html;charset=utf-8");

    }

    @DisplayName("Get Todos with id")
    @Test
    public void getTodoID(){
        RequestSpecification httpRequest = RestAssured.given();
        Response respose = httpRequest.
                get("/todos").
                then().extract().response();
        int id = respose.jsonPath().getInt("todos.id[0]");
        String assertTitle = respose.jsonPath().getString("todos.title[0]");
        System.out.println(assertTitle);
        System.out.println(id);
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df")
                .get("/todos/"+id).
                then().statusCode(200).contentType("application/json").log().all().
                body("todos[0].title",equalTo(assertTitle));

    }

    @DisplayName("Get head requests")
    @Test
    public void headRequest(){
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df")
                .head("/todos").
                then().log().all();

    }

    @DisplayName("Post todo")
    @Test
    public void postTodo(){
        boolean doneStatus = true;
        JSONObject map = new JSONObject();
        map.put("title","Create test for juny");
        map.put("doneStatus",doneStatus);
        map.put("description","this is to learn how to test apis");

        File file = new File("src/test/java/Resources/test.json");

        Response response = given().header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df").
                body(file).when().
                post("/todos").
                then().log().all().
                extract().response();
        String title = response.jsonPath().getString("title");
        Assert.assertEquals("Create test for juny",title);

    }

    @DisplayName("get filter todo")
    @Test
    public void filterTodo(){
        boolean doneStatus = true;

         given()
                .header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df").
                param("doneStatus",doneStatus)
                 .when().
                get("/todos").
                then().log().all();


    }

    @DisplayName("Post update todoid")
    @Test
    public void postUpdate(){
        RequestSpecification httpRequest = RestAssured.given();
        Response respose = httpRequest.
                get("/todos").
                then().extract().response();
        int id = respose.jsonPath().getInt("todos.id[0]");
//        String assertTitle = respose.jsonPath().getString("todos.title[0]");
//        System.out.println(assertTitle);
        System.out.println(id);

        JSONObject object = new JSONObject();
        object.put("title","I am me");
        given().
                header("X-Challenger","cf8ca5d8-8155-458f-82ba-194cd60426df").
                body(object.toJSONString()).
                when()
                .post("/todos/"+id).
                then().statusCode(200).contentType("application/json").log().all();
//                        .
//                body("todos[0].title",equalTo("I am me"));

    }


}

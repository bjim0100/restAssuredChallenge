/*
* Author: Obeng Agyenim Boateng Boateng
* https://apichallenges.herokuapp.com/gui/challenges/cf8ca5d8-8155-458f-82ba-194cd60426df
*
* */

package Tests;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;

public class AcceptAndMixAccept extends baseTest{

    @BeforeClass
    public static void test() {
       baseTest base = new baseTest();
       base.setup();

    }

    @DisplayName("delete todo")
    @Test
    public void delete(){
        Response respose =
                get("/todos").
                then().extract().response();
        int id = respose.jsonPath().getInt("todos.id[0]");
        given().spec(requestSpec)
                .when().
                delete("/todos/"+id).then().statusCode(200).log().all();
        ;
    }

    @DisplayName("options todo")
    @Test
    public void options(){
//        RequestSpecification httpRequest = RestAssured.given();
//        Response respose =
                     given().spec(requestSpec)
                .when().
                options("/todos").then().statusCode(200).log().all();
        ;
    }

    @DisplayName("accept Header todo")
    @Test
    public void acceptHeaderXML(){

        given().spec(requestSpec).header("Accept","application/xml")
                .when().
                get("/todos").then().statusCode(200).log().all();
        ;
    }

    @DisplayName("accept Header XML and JSON todo")
    @Test
    public void acceptHeaderXMLAndJson(){

        given().spec(requestSpec).header("Accept","application/xml,application/json")
                .when().
                get("/todos").then().statusCode(200).log().all().contentType("application/xml");
        ;
    }

    @DisplayName("accept Header todo")
    @Test
    public void acceptHeaderJSON(){

        given().spec(requestSpec).header("Accept","application/json")
                .when().
                get("/todos").then().statusCode(200).log().all();
        ;
    }

    @DisplayName("accept Header todo")
    @Test
    public void acceptHeaderEmpty(){

        given().spec(requestSpec).header("Accept","")
                .when().
                get("/todos").then().statusCode(200).log().all().contentType("application/json");
        ;
    }

    @DisplayName("accept Header todo")
    @Test
    public void acceptHeaderGzip(){

        given().spec(requestSpec).header("Accept","application/gzip")
                .when().
                get("/todos").then().statusCode(406).log().all();
        ;
    }

}

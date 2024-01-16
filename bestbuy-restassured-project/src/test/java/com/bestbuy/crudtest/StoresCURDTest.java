package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {
    static String name = TestUtils.getRandomValue() +"PrimUser";
    static String type = TestUtils.getRandomValue() +"textBox";
    static String address = TestUtils.getRandomValue() +"12";
    static String address2 = TestUtils.getRandomValue() +"Elmore Drive";
    static String City = TestUtils.getRandomValue() +"Harrow";
    static String State = TestUtils.getRandomValue() +"London";
    static String Zip = TestUtils.getRandomValue() +"546734";
    static Double lat = 12.875;
    static Double lag = 34.56748;
    static String hours = TestUtils.getRandomValue() +"Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeID;

    //==================Create a store and find the product ID========================//

    @Test
    public void test001() {

        // Set store attributes
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(State);
        storePojo.setZip(Zip);
        storePojo.setLat(lat);
        storePojo.setLng(lag);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");//Send a POST request to create a store
        //Extract store ID from the response
        storeID = response.then().contentType(ContentType.JSON).extract().path("id");
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //=============================Find the new store by ID==========================//

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/stores" + "/" + storeID);//Send a GET request to retrieve a specific store by ID
        storeID = response.then().contentType(ContentType.JSON).extract().path("id");//Extract store ID from the response
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //======================Update the new store===============================//

    @Test
    public void test003() {

        //Update store attributes
        StorePojo storePojo = new StorePojo();
        storePojo.setHours(hours);
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress("26");
        storePojo.setAddress2("Sky Lander");
        storePojo.setCity("Ahmedabad");
        storePojo.setState(State);
        storePojo.setZip(Zip);
        storePojo.setLat(lat);
        storePojo.setLng(lag);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/stores" + "/" + storeID);//Send a PATCH request to update the store
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //======================Delete the new store===============================//

    @Test
    public void test004() {
        Response response = given()
                .when()
                .delete("/stores" + "/" + storeID);//Send a DELETE request to delete a store
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }
}

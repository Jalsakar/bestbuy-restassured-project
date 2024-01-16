package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {

    static String name = TestUtils.getRandomValue() +"Duracell - AAA Batteries (4-Pack)";
    static String type = TestUtils.getRandomValue() +"HardGood";
    static Double price = 5.75;
    static String upc = TestUtils.getRandomValue() +"041333424019";
    static Double shipping = 0.0;
    static String description = TestUtils.getRandomValue() +"Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = TestUtils.getRandomValue() +"Duracell";
    static String model = TestUtils.getRandomValue() +"MN2400B4Z";
    static String url = TestUtils.getRandomValue() +"http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = TestUtils.getRandomValue() +"http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productID;

    //=============Create a product and find the product ID================================//
    @Test
    public void test001() {

        // Set product attributes
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setImage(image);
        productPojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post("/products");//Send a POST request to create a product
        //Extract product ID from the response
        productID = response.then().contentType(ContentType.JSON).extract().path("id");
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //=============================Find the new product by ID==========================//
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/products" + "/" + productID);//Send a GET request to retrieve a product by ID
        productID = response.then().contentType(ContentType.JSON).extract().path("id");//Extract product ID from the response
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //======================Update the new product===============================//

    @Test
    public void test003() {

        //Update product attributes
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(06.50);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .patch("/products" + "/" + productID);//Send a PATCH request to update the product
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //======================Delete the new product===============================//

    @Test
    public void test004() {
        Response response = given()
                .when()
                .delete("/products" + "/" + productID);//Send a DELETE request to delete a product
        //Verify the response status code and print the product ID in the console
        response.then().statusCode(200);
        response.prettyPrint();
    }
}

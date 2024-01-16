package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * 1. Extract the limit
 * 2. Extract the total
 * 3. Extract the name of 5th product
 * 4. Extract the names of all the products
 * 5. Extract the productId of all the products
 * 6. Print the size of the data list
 * 7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
 * 8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
 * 9. Get all the categories of 8th products
 * 10. Get categories of the store where product id = 150115
 * 11. Get all the descriptions of all the products
 * 12. Get id of all the all categories of all the products
 * 13. Find the product names Where type = HardGood
 * 14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
 * 15. Find the createdAt for all products whose price < 5.49
 * 16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
 * 17. Find the manufacturer of all the products
 * 18. Find the image of products whose manufacturer is = Energizer
 * 19. Find the createdAt for all categories products whose price > 5.99
 * 20. Find the uri of all the products
 */

public class ProductsExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // * 1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 3. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 4. Extract the names of all the products
    @Test
    public void test004() {
        List<String> allProductsName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the names of all the products is : " + allProductsName);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 5. Extract the productId of all the products
    @Test
    public void test005() {
        List<String> allProductsId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the productId of all the products is : " + allProductsId);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 6. Print the size of the data list
    @Test
    public void test006() {
        List<String> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the size of the data list is : " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // * 7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all product where product name = Energizer - MAX Batteries AA (4-Pack)t is : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) is : " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 9. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> category = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the categories of 8th products is : " + category);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 10. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of categories of the store where product id = 150115 is : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 11. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the descriptions of all the products is : " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 12. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<String> ids = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id of all the all categories of all the products is : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 13. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> name = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the product names Where type = HardGood is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<HashMap<String, ?>> noOfCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the Total number of categories for product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) is : " + noOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the createdAt for all products whose price < 5.49 is : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String> categoriesNames = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)” is : " + categoriesNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 17. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<String> manufacture = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the manufacturer of all the products is : " + manufacture);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 18. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the image of products whose manufacturer = Energizer is : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the createdAt for all categories products whose price > 5.99 is : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // * 20. Find the url of all the products
    @Test
    public void test020() {
        List<String> url =  response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the uri of all the products is : " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}

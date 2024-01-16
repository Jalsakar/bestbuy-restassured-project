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
 * 3. Extract the name of 5th store
 * 4. Extract the names of all the store
 * 5. Extract the storeId of all the store
 * 6. Print the size of the data list
 * 7. Get all the value of the store where store name = St Cloud
 * 8. Get the address of the store where store name = Rochester
 * 9. Get all the services of 8th store
 * 10. Get storeservices of the store where service name = Windows Store
 * 11. Get all the storeId of all the store
 * 12. Get id of all the store
 * 13. Find the store names Where state = ND
 * 14. Find the Total number of services for the store where store name = Rochester
 * 15. Find the createdAt for all services whose name = “Windows Store”
 * 16. Find the name of all services Where store name = “Fargo”
 * 17. Find the zip of all the store
 * 18. Find the zip of store name = Roseville
 * 19. Find the storeservices details of the service name = Magnolia Home Theater
 * 20. Find the lat of all the stores
 */

public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String nameOfFifthStore = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + nameOfFifthStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the store is : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> allDataSize = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + allDataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store name = St Cloud is : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        String address = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the address of the store name = Rochester is : " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the services of 8th store is : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of storeservices of the store where service name = Windows Store is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the storeId of all the store is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<String> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id of all the store is : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        String storeName = response.extract().path("data[7].state");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store names Where state = ND is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<String> noOfServices = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the Total number of services for the store name = Rochester is : " + noOfServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<HashMap<String, ?>> createdAt = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the createdAt for all services whose name = “Windows Store” is : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<HashMap<String, ?>> servicesNames = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the name of all services Where store name = “Fargo” is : " + servicesNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the zip of all the store is : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the zip of store name = Roseville is : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> storeservices = response.extract().path("data.findAll{it.services.find { it.name == 'Magnolia Home Theater' } != null }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the storeservices details of the service name = Magnolia Home Theater is : " + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the lat of all the stores is : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}

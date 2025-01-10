package ge.tbc.testautomation.services;

import ge.tbc.testautomation.enums.SwoopEndpoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SwoopRequestService {
    public static Response getRestaurantOffers(int categoryId, int langId) {
        return RestAssured
                .given()
//                .baseUri(SwoopEndpoint.BASE_URL.toString())
                .baseUri("http://localhost:8080")   // Use WireMock server
                .basePath(SwoopEndpoint.SEARCH_OFFERS.toString())
                .queryParam("filter[cat_id]", categoryId)
                .queryParam("LangID", langId)
//                .log().all() // log request details if needed
                .when()
                .get(); // GET request
    }
}
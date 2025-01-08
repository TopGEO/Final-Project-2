package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pojos.SearchOffersResponse;
import ge.tbc.testautomation.services.SwoopRequestService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
public class RASwoopSteps {

    @Step("Calls SwoopRequestService to get restaurant offers and ensures we receive a 200 (OK)")
    public SearchOffersResponse getRestaurantOffers(int categoryId, int langId) {
        Response response = SwoopRequestService.getRestaurantOffers(categoryId, langId);

        // validate status code or any other quick checks:
        response.then()
                .log().ifError()
                .statusCode(200);

        // Deserialize JSON -> SearchOffersResponse
        return response.as(SearchOffersResponse.class);
    }
}
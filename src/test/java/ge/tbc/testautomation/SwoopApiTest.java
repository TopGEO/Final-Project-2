package ge.tbc.testautomation;

import ge.tbc.testautomation.data.dataproviders.OfferDataProviders;
import ge.tbc.testautomation.pojos.Offer;
import ge.tbc.testautomation.pojos.SearchOffersResponse;
import ge.tbc.testautomation.steps.RASwoopSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static ge.tbc.testautomation.data.SwoopConstants.*;


// Dear mentor, just a heads up, i have made these RestAssured tests using Alina's example from her GitHub page, i hope its okay 😄 Btw, the repo is public, so all is in plain sight!

@Epic("Swoop Offers API")
@Feature("Restaurant Offers")
public class SwoopApiTest extends BaseWireMockTest {
    RASwoopSteps swoopSteps = new RASwoopSteps();
    SoftAssert sfa = new SoftAssert();


    @Severity(SeverityLevel.NORMAL)
    @Story("We want to see a list of offers for a given category.")
    @Test(dataProvider = "validCategoryData", dataProviderClass = OfferDataProviders.class)
    @Description("Some basic checkers for swoop api testing, making it ready for enhacing later")
    public void getRestaurantOffersTest(int categoryId, int langId) {
        SearchOffersResponse response = swoopSteps.getRestaurantOffers(categoryId, langId);
        List<Offer> offers = response.getOffers();

        // some basic checks
        sfa.assertNotNull(response, RESPONSE_NULL_ERROR);
        sfa.assertNotNull(response.getOffers(), OFFERS_LIST_NULL_ERROR);
        sfa.assertFalse(response.getOffers().isEmpty(), OFFERS_LIST_EMPTY_ERROR);
        sfa.assertEquals(response.getPagination().getCurrentPage(), 1,
                PAGINATION_ERROR); // default must be 1, since we did not change anything about it

        // ensure each offer has an ID and name
        for (Offer offer : response.getOffers()) {
            sfa.assertNotNull(offer.getId(), OFFER_ID_NULL_ERROR);
            sfa.assertNotNull(offer.getName(), OFFERS_NAME_EMPTY_ERROR);
            System.out.println(offer.getName());
        }

        sfa.assertTrue(response.getPagination().getCurrentPage() == 1 && offers.size() == 24);

        sfa.assertAll();
    }

    // for now, its enought, but if we want we can add other tests too, such as test retrieving the second page, test invalid category or invalid LangID, test price ranges, etc.
    // P.S i hope its enough to show that i have finished Bonus 3 and im ready to dive deeply in RA : ))
}
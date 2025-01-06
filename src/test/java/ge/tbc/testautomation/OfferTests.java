package ge.tbc.testautomation;

import ge.tbc.testautomation.dataprovider.DProvider;
import ge.tbc.testautomation.steps.SwoopSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Website Functionality Testing")
@Feature("Offer Management Tests")
public class OfferTests extends BaseTest {
    SwoopSteps swoopSteps = new SwoopSteps(sfa);

    @Test(priority = 1, dataProviderClass = DProvider.class, dataProvider = "searchKeywords", description = "Tests both valid and invalid search keywords.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify Search Functionality")
    @Description("Perform searches with both valid and invalid keywords. Validate that results match the query for valid keywords and a 'No Results Found' message appears for invalid ones.")
    public void searchTest(String keyword, boolean isValid) {
        swoopSteps.openSwoopPage()
                .clickAgreeButton()
                .validateSearch(keyword, isValid);
    }

    @Test(priority = 2, description = "Checks pagination controls within categories.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Validate Pagination Controls")
    @Description("Navigate through different pages of product listings within a sub-category to ensure pagination controls are functioning properly, verifying that each page shows different results.")
    public void paginationTest() {
        swoopSteps.openSwoopPage()
                .clickAgreeButton()
                .goToSubCategory()
                .validateProductsChange()
                .validateBackAndNextButtons();
    }

    @Test(priority = 3, description = "Validates map navigation for offer locations.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Check Offer Location Mapping")
    @Description("Click the location button on an offer to ensure the interface correctly scrolls to and displays the map section.")
    public void offerLocationTest() {
        swoopSteps.openSwoopPage()
                .clickAgreeButton()
                .goToSubCategory()
                .validateLocationButton();
    }

    @Test(priority = 4, description = "Tests the 'Number of Guests' filter in Eat & Drink category.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Guest Number Filter on Offers")
    @Description("Apply the 'Number of Guests' filter and verify that the filtered offers match the selected criteria in their descriptions.")
    public void numberOfGuestsTest() {
        swoopSteps.openSwoopPage()
                .clickAgreeButton()
                .validateGuestsFilter();
    }

    @Test(priority = 5, description = "Ensures functionality of language switching.")
    @Severity(SeverityLevel.NORMAL)
    @Story("Ensure Language Switch Functionality")
    @Description("Switch between English and Georgian languages and validate that all user interface texts and labels update accordingly.")
    public void changeLanguageTest() {
        swoopSteps.openSwoopPage()
                .clickAgreeButton()
                .validateLanguageChange();
    }
}
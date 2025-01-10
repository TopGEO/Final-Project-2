package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.deque.axe.AXE;
import ge.tbc.testautomation.data.models.Product;
import ge.tbc.testautomation.pages.SwoopPages;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static ge.tbc.testautomation.data.SwoopConstants.*;
import static ge.tbc.testautomation.util.ImageComparison.compareImages;
import static ge.tbc.testautomation.util.LanguageDetector.checkLang;
import static ge.tbc.testautomation.util.LanguageDetector.getMainTexts;
import static ge.tbc.testautomation.util.ProductsScraper.getProducts;
import static ge.tbc.testautomation.util.ProductComparator.*;
import static ge.tbc.testautomation.util.GuestCountParser.parseGuestCount;
import static ge.tbc.testautomation.util.Utils.*;
import static org.testng.Assert.assertTrue;

// for OfferTests and for Visual and Accessibility tests
public class SwoopSteps extends CommonSteps<SwoopSteps> {
    SwoopPages swoopPages = new SwoopPages();

    @Step("Opening swoop's website.")
    public SwoopSteps openSwoopPage() {
        open(SWOOP_URL);

        return this;
    }

    @Step("Clicking accept cookies to ensure they wont overlap clickable divs")
    public SwoopSteps clickAgreeButton() {
        swoopPages.agreeButton.click();

        return this;
    }

    @Step("Validating valid and invalid searchs from dataprovider")
    public SwoopSteps validateSearch(String keyword, boolean isValid) {
        $("input").setValue(keyword).pressEnter();
        if (isValid) {
            sfa.assertTrue(swoopPages.productsDiv.shouldBe(Condition.visible, Duration.ofSeconds(10)).exists(), VALID_SEARCH_FAILED);
        } else {
            sfa.assertTrue(!swoopPages.productsDiv.shouldBe(Condition.disappear, Duration.ofSeconds(10)).exists(), INVALID_SEARCH_FAILED);
            sfa.assertTrue(swoopPages.noResultFound.shouldBe(Condition.visible, Duration.ofSeconds(10)).exists());
        }

        return this;
    }

    @Step("Hover on \"kveba\" category and choose \"restaurant\" sub-category")
    public SwoopSteps goToSubCategory() {
        swoopPages.categoriesDropdown.click();
        swoopPages.kvebaSubButton.hover();

        // Sometime error arises, so lets ensure there wont be any error
        swoopPages.restaurantButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        swoopPages.restaurantButton.shouldBe(Condition.enabled, Duration.ofSeconds(10));

        swoopPages.restaurantButton.click();

        return this;
    }

    @Step("Validating if products are changing for page 1,2,3")
    public SwoopSteps validateProductsChange() {
        List<Product> productsOfPage1 = getProducts(swoopPages.productElements.shouldHave(sizeGreaterThan(0)));
        swoopPages.productsPage2.click();
        List<Product> productsOfPage2 = getProducts(swoopPages.productElements.shouldHave(sizeGreaterThan(0)));
        swoopPages.productsPage3.click();
        List<Product> productsOfPage3 = getProducts(swoopPages.productElements.shouldHave(sizeGreaterThan(0)));

        sfa.assertTrue(areTablesDifferent(productsOfPage1, productsOfPage2), PAGE_1_2_MATCHED_PRODUCTS);
        sfa.assertTrue(areTablesDifferent(productsOfPage1, productsOfPage3), PAGE_1_3_MATCHED_PRODUCTS);
        sfa.assertTrue(areTablesDifferent(productsOfPage2, productsOfPage3), PAGE_2_3_MATCHED_PRODUCTS);

        return this;
    }

    @Step("Validating if Back and Next buttons are working correctly")
    public SwoopSteps validateBackAndNextButtons() {
        String prevPageNumber = swoopPages.activePageNumber.getText();
        // We consider that restaurant's products always have at least 3 different page, normally 4.
        swoopPages.productsPage2.click(); // To ensure it will have left arrow enabled

        // sometime currentPageNumber will get old meaning and sometime it will be same, so to ensure there wont be a bug, without sleep()
        swoopPages.activePageNumber.shouldNotHave(Condition.text(prevPageNumber));

        int currentPageNumber = Integer.parseInt(swoopPages.activePageNumber.getText());


        scrollToCenterWithClick(swoopPages.leftArrow);
        swoopPages.activePageNumber.shouldHave(
                Condition.text(Integer.toString(currentPageNumber - 1))
        );

        scrollToCenterWithClick(swoopPages.rightArrow);
        swoopPages.activePageNumber.shouldHave(
                Condition.text(Integer.toString(currentPageNumber))
        );

        return this;
    }

    @Step("Navigate to the first item which has map and check if scroll works")
    public SwoopSteps validateLocationButton() {
        // Loop until a product with a map is found or the last page is reached
        while (true) {
            List<Product> products = getProducts(swoopPages.productElements.shouldHave(sizeGreaterThan(0)));
            boolean foundMap = false;
            for (Product product : products) {
                open(product.getLink());
                if (swoopPages.mapContainer.exists() && swoopPages.mapContainer.isDisplayed()) {
                    foundMap = true;
                    break;
                }
            }

            // If a map is found, exit the loop
            if (foundMap) {
                break;
            }

            // Otherwise, navigate to the next page unless on the last page
            String activePage = swoopPages.activePageNumber.getText();
            String totalPages = swoopPages.totalPagesNumber.getText();

            if (activePage.equals(totalPages)) {
                // No product with a map found on any page
                throw new NoSuchElementException(PRODUCT_WITH_MAP_NOT_FOUND);
            } else {
                swoopPages.rightArrow.shouldBe(Condition.visible).click();
            }
        }

        scrollToCenterWithClick(swoopPages.locationButton.shouldBe(Condition.visible));
        waitForScrollToFinish();

        // for my width (on 1366x768) map's bottom side was overlapped out of viewport by 37px approximatelly, so lets ensure there wont be any error.
        int[] tolerances = {0, 0, 50, 0}; // bottom will have 50px tollerance
        sfa.assertTrue(isInViewport(swoopPages.mapContainer, tolerances), MAP_NOT_ON_VIEWPORT);
        swoopPages.mapContainer.shouldBe(Condition.visible);

        return this;
    }

    @Step("Validating if guests filter works well")
    public SwoopSteps validateGuestsFilter() {
        swoopPages.kvebaButton.click();
        scrollToCenterWithClick(swoopPages.guest_2_5);
        scrollToCenterWithClick(swoopPages.guest_6_10);
        scrollToCenterWithClick(swoopPages.guest_11_15);

        int minRange = 2, maxRange = 15; // For our case
        List<String> failedProducts = new ArrayList<>();

        int currentPageNumber = Integer.parseInt(swoopPages.activePageNumber.getText());
        int totalPageNumber = Integer.parseInt(swoopPages.totalPagesNumber.getText());

        do {
            List<Product> products = getProducts(swoopPages.productElements.shouldHave(sizeGreaterThan(0)));

            for (Product product : products) {
                String name = product.getName();
                boolean fits = parseGuestCount(name, minRange, maxRange);
                if (!fits) {
                    failedProducts.add(String.format("[%s] => %s%n", name, fits));
                }
            }

            if (currentPageNumber < totalPageNumber) {
                scrollToCenterWithClick(swoopPages.rightArrow);
            }
            currentPageNumber++;
        } while (currentPageNumber <= totalPageNumber);

        int sizeOfFailedProducts = failedProducts.size();
        sfa.assertEquals(sizeOfFailedProducts, 0, FAILED_PRODUCTS_MESSAGE(minRange, maxRange, failedProducts));


        return this;
    }

    @Step("Validating if language change works well")
    public SwoopSteps validateLanguageChange() {
        Set<String> textsKa = getMainTexts(swoopPages.mainDiv);

        swoopPages.languageChangeButton.click();
        swoopPages.changeToEnglish.click();

        Set<String> textsEn = getMainTexts(swoopPages.mainDiv);

        sfa.assertTrue(checkLang(textsKa, true));
        sfa.assertTrue(checkLang(textsEn, false)); // false means English, as default.

        return this;
    }

    @Step("Checking accessibility features for swoop")
    public SwoopSteps validatePageAccessibility() {
        // here we will check how well did developers fit this website to people with disabilities
        WebDriver driver = Selenide.webdriver().driver().getWebDriver();

        // load axe.min.js from resources and run accessibility analysis
        JSONObject responseJSON = new AXE.Builder(driver, getClass().getResource("/axe.min.js")).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");

        int validationsLenth = violations.length();
        if (validationsLenth == 0) {
            System.out.println(NO_VIOLATIONS_FOUND);
        } else {
            for (int i = 0; i < validationsLenth; i++) {
                JSONObject violation = violations.getJSONObject(i);
                System.out.println(VIOLATION_HEADER + violation.getString("help"));
                System.out.println(DESCRIPTION_HEADER + violation.getString("description"));
                System.out.println(IMPACT_HEADER + violation.getString("impact"));
                System.out.println(NODES_AFFECTED_HEADER + violation.getJSONArray("nodes").toString());
                System.out.println(MESSAGES_SEPARATOR);
            }
            sfa.fail(validationsLenth + FAIL_MESSAGE_PREFIX);
        }

        return this;
    }

    @Step("Capturing screens before&after language switch")
    public SwoopSteps validateVisualChange() throws IOException {
        // very simple presentation of visual testing, just checking differences, nothing serious but just found out for myself what was visual testing about, generally.
        swoopPages.cartButton.click();

        String beforeStartPath = Selenide.screenshot("before_start").replace("file:", "").trim();;
        assert beforeStartPath != null;
        File beforeImage = new File(beforeStartPath).getAbsoluteFile();

        swoopPages.languageChangeButton.click();
        swoopPages.changeToEnglish.click();

        String afterLoadPath = Selenide.screenshot("after_loading").replace("file:", "").trim();
        assert afterLoadPath != null;
        File afterImage = new File(afterLoadPath).getAbsoluteFile();

        boolean imagesAreEqual = compareImages(beforeImage, afterImage);
        beforeImage.delete();
        afterImage.delete();

        sfa.assertFalse(imagesAreEqual);

        return this;
    }
}
package ge.tbc.testautomation;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.models.Product;
import ge.tbc.testautomation.pages.SwoopPages;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.testautomation.data.SwoopConstants.*;
import static ge.tbc.testautomation.util.ProductsScraper.getProducts;
import static ge.tbc.testautomation.util.Utils.*;

public class Tests extends BaseTest {
    SwoopPages swoopPages = new SwoopPages();

    @Test
    public void mapTest() {
        open("https://swoop.ge/category/229/kveba/restorani/");

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

        int[] tolerances = {0, 0, 50, 0};
        sfa.assertTrue(isInViewport(swoopPages.mapContainer, tolerances), MAP_NOT_ON_VIEWPORT);
        swoopPages.mapContainer.shouldBe(Condition.visible);
    }
}

package ge.tbc.testautomation.util;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.models.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsScraper {

    public static List<Product> getProducts(ElementsCollection productElements) {
        List<Product> fetchedProducts = new ArrayList<>();

        for (int i = 1; i <= productElements.size(); i++) {
            try {
                SelenideElement productElement = $x("(//a[contains(@class, 'group flex flex-col')])[" + i + "]");

                // Extract product name
                String name = productElement.$("h4[class*=\"font-tbcx-medium\"]").getText();

                // Extract product price
                String price = productElement.$("h4[class*=\"font-tbcx-bold\"]").getText();

                // Extract product link
                String link = productElement.getAttribute("href");

                Product product = new Product(name, price, link);
                fetchedProducts.add(product);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // we use sort just for comparator of products on different page, but for other usages it does not really matter, its just "nicer" )))
        fetchedProducts.sort(Comparator.comparing(Product::getLink));
        return fetchedProducts;
    }
}
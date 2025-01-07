package ge.tbc.testautomation.data; // creating conflict 2

import java.util.List;

public class SwoopConstants {
    public static String SWOOP_URL = "https://swoop.ge";
    public static String VALID_SEARCH_FAILED = "Results should be displayed for valid keyword.";
    public static String INVALID_SEARCH_FAILED = "No results message should be displayed for invalid keyword.";
    public static String PAGE_1_2_MATCHED_PRODUCTS = "Products on Page 1 and Page 2 should not have any matches.";
    public static String PAGE_1_3_MATCHED_PRODUCTS = "Products on Page 1 and Page 3 should not have any matches.";
    public static String PAGE_2_3_MATCHED_PRODUCTS = "Products on Page 2 and Page 3 should not have any matches.";
    public static String PRODUCT_WITH_MAP_NOT_FOUND = "No product with a map was found on any page!";
    public static String MAP_NOT_ON_VIEWPORT = "Map container should be in the viewport.";

    public static String FAILED_PRODUCTS_MESSAGE(int minRange, int maxRange, List<String> failedProducts) {
        return "These products have out of bound (min - " + minRange + ", max - " + maxRange + ") guests number:" +
                "\n\u001B[33m" + String.join("\n", failedProducts) + "\u001B[0m";
    }
}

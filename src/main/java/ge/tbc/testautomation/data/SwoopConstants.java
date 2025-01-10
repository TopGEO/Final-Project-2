package ge.tbc.testautomation.data;

import java.net.URL;
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
    public static String RESPONSE_NULL_ERROR = "Response object should not be null";
    public static String OFFERS_LIST_NULL_ERROR = "Offers list should not be null";
    public static String OFFERS_LIST_EMPTY_ERROR = "Offers list should not be empty";
    public static String PAGINATION_ERROR = "Default page should be 1 (if not specified otherwise)";
    public static String OFFER_ID_NULL_ERROR = "Offer ID should not be null";
    public static String OFFERS_NAME_EMPTY_ERROR = "Offer name should not be null";
    public static final String NO_VIOLATIONS_FOUND = "No accessibility violations found.";
    public static final String VIOLATION_HEADER = "Violation: ";
    public static final String DESCRIPTION_HEADER = "Description: ";
    public static final String IMPACT_HEADER = "Impact: ";
    public static final String NODES_AFFECTED_HEADER = "Nodes affected: ";
    public static final String MESSAGES_SEPARATOR = "----------------------------------------";
    public static final String FAIL_MESSAGE_PREFIX = " accessibility violation(s) found.";
    public static final String FAILED_PRODUCT_MESSAGE_TEMPLATE =
            "Product [Name: %s, Provider: %s] does not contain keyword '%s'";
    public static final String PRODUCTS_DID_NOT_MATCH = "Some products did not match the search criteria: ";




    public static String FAILED_PRODUCTS_MESSAGE(int minRange, int maxRange, List<String> failedProducts) {
        return "These products have out of bound (min - " + minRange + ", max - " + maxRange + ") guests number:" +
                "\n\u001B[33m" + String.join("\n", failedProducts) + "\u001B[0m";
    }
}

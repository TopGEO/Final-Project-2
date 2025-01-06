package ge.tbc.testautomation.util;

import ge.tbc.testautomation.models.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductComparator {
    public static boolean areTablesDifferent(List<Product> list1, List<Product> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Product lists must not be null.");
        }

        Set<Product> set2 = new HashSet<>(list2);

        for (Product product : list1) {
            if (set2.contains(product)) {
                return false;
            }
        }

        return true;
    }


    public static List<Product> getMatchingProducts(List<Product> list1, List<Product> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Product lists must not be null.");
        }

        Set<Product> set2 = new HashSet<>(list2);

        return list1.stream()
                .filter(set2::contains)
                .toList();
    }
}
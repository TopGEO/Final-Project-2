package ge.tbc.testautomation.data.dataproviders;

import org.testng.annotations.DataProvider;

public class OfferDataProviders {

    @DataProvider(name = "validCategoryData")
    public static Object[][] validCategoryData() {
        return new Object[][]{
                {229, 2},  // English
                {229, 1}   // Georgian
        };
    }
}

package ge.tbc.testautomation.data.dataprovider;
import org.testng.annotations.DataProvider;

public class DProvider {
    @DataProvider(name = "searchKeywords")
    public Object[][] provideSearchKeywords() {
        return new Object[][] {
                {"კაფე", true},  // Valid keyword
                {"asdfghjkl", false}    // Invalid keyword
                // we can add more if we want
        };
    }
}

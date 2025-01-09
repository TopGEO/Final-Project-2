package ge.tbc.testautomation.steps;

import org.testng.asserts.SoftAssert;

public class CommonSteps<T extends CommonSteps<T>> {
    // single shared SoftAssert for all tests
    protected static SoftAssert sfa = new SoftAssert();

    @SuppressWarnings("unchecked")
    public T checkSoftAssert() {
        try {
            CommonSteps.sfa.assertAll();
        } finally {
            // Re-init so next test doesn’t see old errors
            sfa = new SoftAssert();
        }

        return (T) this;
    }
}
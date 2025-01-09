package ge.tbc.testautomation.steps;

import org.testng.asserts.SoftAssert;

public class CommonSteps<T extends CommonSteps<T>> {
    // single shared SoftAssert for all tests
    protected static SoftAssert sfa = new SoftAssert();


    // we have to do assert all after all test to take screenshots (screenshot can be taken only when fail is detected during Test and another Test method is not started
    @SuppressWarnings("unchecked")
    public T checkSoftAssert() {
        try {
            CommonSteps.sfa.assertAll();
        } finally {
            // re-init so next test doesn’t see old errors
            sfa = new SoftAssert();
        }

        return (T) this;
    }
}
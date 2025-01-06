package ge.tbc.testautomation.util;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Utils {
    public static void scrollToCenterWithClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", element);
        waitForScrollToFinish();
        element.click();
    }

    public static boolean isInViewport(SelenideElement element) {
        return Boolean.TRUE.equals(executeJavaScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (" +
                        "rect.top >= 0 && " +
                        "rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                        ");",
                element));
    }

    public static void waitForScrollToFinish() {
        Number lastPosition;
        Number currentPosition;
        do {
            lastPosition = (Number) executeJavaScript("return window.scrollY;");
            sleep(100); // pause to allow any ongoing scrolling to settle
            currentPosition = (Number) executeJavaScript("return window.scrollY;");
        } while (!lastPosition.equals(currentPosition));
    }
}

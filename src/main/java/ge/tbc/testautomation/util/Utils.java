package ge.tbc.testautomation.util;

import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class Utils {
    public static void scrollToCenterWithClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", element);
        waitForScrollToFinish();
        element.click();
    }

    public static boolean isInViewport(SelenideElement element) {
        int[] defaultTolerance = {0, 0, 0, 0};  // default tolerances here
        return isInViewport(element, defaultTolerance);
    }

    public static boolean isInViewport(SelenideElement element, int[] tolerance) {
        if (tolerance.length != 4) {
            throw new IllegalArgumentException("Tolerance array must have four elements: [top, right, bottom, left]");
        }

        Map<String, Object> rect = executeJavaScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return {" +
                        "  top: rect.top, " +
                        "  left: rect.left, " +
                        "  bottom: rect.bottom, " +
                        "  right: rect.right, " +
                        "  windowHeight: window.innerHeight || document.documentElement.clientHeight, " +
                        "  windowWidth: window.innerWidth || document.documentElement.clientWidth " +
                        "};",
                element
        );

        // Cast each value to Double before comparison
        double top = ((Number) rect.get("top")).doubleValue();
        double left = ((Number) rect.get("left")).doubleValue();
        double bottom = ((Number) rect.get("bottom")).doubleValue();
        double right = ((Number) rect.get("right")).doubleValue();
        double windowHeight = ((Number) rect.get("windowHeight")).doubleValue();
        double windowWidth = ((Number) rect.get("windowWidth")).doubleValue();

        boolean isInViewport = (top + tolerance[0]) >= 0 &&
                (right - tolerance[1]) <= windowWidth &&
                (bottom - tolerance[2]) <= windowHeight &&
                (left + tolerance[3]) >= 0;

        // to debug :
//        System.out.println("rect.top >= 0: " + ((top + tolerance[0]) >= 0) + " (rect.top = " + top + ")");
//        System.out.println("rect.right <= window.innerWidth: " + ((right - tolerance[1]) <= windowWidth) +
//                " (rect.right = " + right + ", window.innerWidth = " + windowWidth + ")");
//        System.out.println("rect.bottom <= window.innerHeight: " + ((bottom - tolerance[2]) <= windowHeight) +
//                " (rect.bottom = " + bottom + ", window.innerHeight = " + windowHeight + ")");
//        System.out.println("rect.left >= 0: " + ((left + tolerance[3]) >= 0) + " (rect.left = " + left + ")");
//
//        System.out.println("Element is in viewport with tolerances [Top, Right, Bottom, Left]: [" + tolerance[0] + ", " + tolerance[1] + ", " + tolerance[2] + ", " + tolerance[3] + "]: " + isInViewport);

        return isInViewport;
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

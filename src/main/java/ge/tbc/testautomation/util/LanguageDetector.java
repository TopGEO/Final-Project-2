package ge.tbc.testautomation.util;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;

public class LanguageDetector {
    private static final Logger log = LoggerFactory.getLogger(LanguageDetector.class);

    // Georgian Unicode range: \u10A0 – \u10FF
    private static boolean isGeorgianChar(char ch) {
        return (ch >= '\u10A0' && ch <= '\u10FF');
    }

    // Basic check for English letters
    private static boolean isEnglishChar(char ch) {
        return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'));
    }

    public static boolean checkLang(Set<String> str, boolean isGeorgian) {
        // if isGeorgian then we check if Website's UI texs's 80% is on georgian, otherwise, defaultly, we check for English.
        int targetChars = 0, nonTargetChars = 0;

        for (char c : String.join("", str).toCharArray()) {
            if ((isGeorgian && isGeorgianChar(c)) || (!isGeorgian && isEnglishChar(c))) {
                targetChars++;
            } else if ((isGeorgian && isEnglishChar(c)) || (!isGeorgian && isGeorgianChar(c))) {
                nonTargetChars++;
            }
        }

        return nonTargetChars <= 0.2 * targetChars;
    }

    private static String getInputPlaceholder() {
        try {
            return $("input").getAttribute("placeholder").trim();
        } catch (StaleElementReferenceException | NoSuchElementException | NullPointerException e) {
            return "";
        }
    }

    public static Set<String> getMainTexts(SelenideElement mainDivSelector) {
        Set<String> texts = new HashSet<>();
        // im taking main tags for UI texts and it will check if language for checking has 80% of it's alphabet.
        String[] tags = {"h1", "h2", "h3", "h4", "h5", "h6", "p", "a", "li"};

        // Add the placeholder text if available
        String placeholder = getInputPlaceholder();
        if (!placeholder.isEmpty()) {
            texts.add(placeholder);
        }

        SelenideElement mainDiv;
        try {
            mainDiv = mainDivSelector.shouldBe(Condition.exist, Duration.ofSeconds(5));
        } catch (StaleElementReferenceException | ElementNotFound e) {
            return texts;
        }

        for (String tag : tags) {
            try {
                List<String> tagTexts = mainDiv.$$(tag).texts();

                for (String text : tagTexts) {
                    String trimmedText = text.trim();
                    if (!trimmedText.isEmpty()) {
                        texts.add(trimmedText);
                    }
                }
            } catch (StaleElementReferenceException | ElementNotFound ignored) {
            }
        }

        return texts;
    }
}
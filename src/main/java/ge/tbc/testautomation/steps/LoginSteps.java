package ge.tbc.testautomation.steps;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.MSSQLConnection;
import ge.tbc.testautomation.pages.LoginPages;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;
import static ge.tbc.testautomation.data.LoginConstants.*;
import static ge.tbc.testautomation.util.Utils.*;

public class LoginSteps {
    LoginPages loginPages = new LoginPages();
    SoftAssert sfa;

    public LoginSteps(SoftAssert sfa) {
        this.sfa = sfa;
    }

    @Step("Opening saucedemo's website.")
    public LoginSteps openLoginPage() {
        open(Login_URL);

        return this;
    }

    @Step("Login with user's password taken from db.")
    public LoginSteps login(String username) {
        String password = null;
        String query = SQL_PASSWORD_TAKE;

        try (Connection conn = MSSQLConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString(PASSWORD_COLUMN_LABEL);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        loginPages.usernameField.sendKeys(username);
        loginPages.passwordField.sendKeys(password);

        loginPages.loginButton.click();

        return this;
    }

    @Step("User is logging out.")
    public LoginSteps logout() {
        loginPages.menuButton.click();
        loginPages.logoutButton.click();

        return this;
    }

    @Step("Validate that all images on the landing page are loaded.")
    public LoginSteps imagesLoadValidation() {
        for (SelenideElement element : loginPages.productItems) {
            element.shouldBe(visible);

            // lets also check if its in viewport
            executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", element);
            sfa.assertTrue(isInViewport(element));
        }

        return this;
    }

    @Step("Validate that 'Epic sadface: Sorry, this user has been locked out.' message appears.")
    public LoginSteps messageAppearanceValidation() {
        sfa.assertEquals(loginPages.errorMessage.shouldBe(visible).getText(), BANNED_USER_ERROR_MESSAGE);

        return this;
    }

    @Step("Validate that the red X icon also is visible.")
    public LoginSteps closeButtonValidation() {
        sfa.assertTrue(isInViewport(loginPages.closeButtonSVG.shouldBe(visible)));

        return this;
    }

    @Step("Validate that Username and Password inputs are empty.")
    public LoginSteps inputFieldsEmptinessValidation() {
        sfa.assertTrue(loginPages.usernameField.val().isEmpty()
                && loginPages.passwordField.val().isEmpty());

        return this;
    }
}

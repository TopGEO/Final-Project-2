package ge.tbc.testautomation;

import ge.tbc.testautomation.steps.LoginSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.LoginConstants.LOCKED_OUT_USER;
import static ge.tbc.testautomation.data.LoginConstants.STANDART_USER;

@Epic("Testing Login Functionalities")
@Feature("SauceDemo Login Functionality Tests")
public class LoginTests extends BaseTest {
    LoginSteps loginSteps = new LoginSteps();

    @Test(priority = 1, groups = {"SauceDemoLogin"})
    @Story("Successful Login Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that users can log in successfully using valid credentials from the database. Additionally, validate that all images on the landing page are loaded, ensuring the page is rendered.")
    public void successfulLoginTest() {
        loginSteps.openLoginPage()
                .login(STANDART_USER)
                .imagesLoadValidation()
                .checkSoftAssert();
    }

    @Test(priority = 2, groups = {"SauceDemoLogin"})
    @Story("Banned User Login Handling")
    @Severity(SeverityLevel.NORMAL)
    @Description("Confirm that the login process correctly identifies and restricts access for banned users by displaying the appropriate error message 'Epic sadface: Sorry, this user has been locked out.' Ensure the red X icon is visible, indicating an unsuccessful login attempt.")
    public void bannedUserLoginTest() {
        loginSteps.openLoginPage()
                .login(LOCKED_OUT_USER)
                .messageAppearanceValidation()
                .closeButtonValidation()
                .checkSoftAssert();
    }

    @Test(priority = 3, groups = {"SauceDemoLogin"})
    @Story("Verify Logout Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure that users can log out successfully from SauceDemo. After logging out, validate that the Username and Password input fields are cleared, confirming the reset of the login form.")
    public void logOutTest() {
        loginSteps.openLoginPage()
                .login(STANDART_USER)
                .logout()
                .inputFieldsEmptinessValidation()
                .checkSoftAssert();
    }
}

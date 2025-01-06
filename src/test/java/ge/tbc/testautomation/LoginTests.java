package ge.tbc.testautomation;

import ge.tbc.testautomation.steps.LoginSteps;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;

import static ge.tbc.testautomation.data.LoginConstants.LOCKED_OUT_USER;
import static ge.tbc.testautomation.data.LoginConstants.STANDART_USER;

@Epic("Testing Login Functionalities")
@Feature("SauceDemo Login Functionality Tests")
public class LoginTests extends BaseTest {
    LoginSteps loginSteps = new LoginSteps(sfa);

    @Test(priority = 1, groups = {"SauceDemoLogin"}, description = "Logs in with standard_user credentials and verifies that all images on the landing page are correctly loaded.")
    @Story("Successful Login Validation")
    @Description("Verify that users can log in successfully using valid credentials from the database. Additionally, validate that all images on the landing page are loaded, ensuring the page is rendered.")
    public void successfulLoginTest() {
        loginSteps.openLoginPage()
                .login(STANDART_USER)
                .imagesLoadValidation();
    }

    @Test(priority = 2, groups = {"SauceDemoLogin"}, description = "Attempts to log in with locked_out_user credentials and checks for the correct error message and UI response.")
    @Story("Banned User Login Handling")
    @Description("Confirm that the login process correctly identifies and restricts access for banned users by displaying the appropriate error message 'Epic sadface: Sorry, this user has been locked out.' Ensure the red X icon is visible, indicating an unsuccessful login attempt.")
    public void bannedUserLoginTest() {
        loginSteps.openLoginPage()
                .login(LOCKED_OUT_USER)
                .messageAppearanceValidation()
                .closeButtonValidation();
    }

    @Test(priority = 3, groups = {"SauceDemoLogin"}, description = "Logs in and then logs out, verifying that the login form resets and the Username and Password fields are empty.")
    @Story("Verify Logout Functionality")
    @Description("Ensure that users can log out successfully from SauceDemo. After logging out, validate that the Username and Password input fields are cleared, confirming the reset of the login form.")
    public void logOutTest() {
        loginSteps.openLoginPage()
                .login(STANDART_USER)
                .logout()
                .inputFieldsEmptinessValidation();
    }
}

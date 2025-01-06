package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPages {
    public SelenideElement usernameField = $("#user-name");
    public SelenideElement passwordField = $("#password");
    public SelenideElement loginButton = $("#login-button");
    public ElementsCollection productItems = $$("div[data-test=\"inventory-item\"] img");
    public SelenideElement errorMessage = $("h3[data-test=\"error\"]");
    public SelenideElement closeButtonSVG = $("button[data-test=\"error-button\"] svg");
    public SelenideElement menuButton = $("#react-burger-menu-btn");
    public SelenideElement logoutButton = $("#logout_sidebar_link");
}

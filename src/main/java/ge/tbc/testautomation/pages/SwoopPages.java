package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$;

public class SwoopPages {
    public SelenideElement agreeButton = $("button[data-testid='primary-button']");
    public SelenideElement categoriesDropdown = $x("//button//p[text()=\"კატეგორიები\"]");
    public SelenideElement kvebaSubButton = $x("//h4[text()=\"კვება\"]");
    public SelenideElement restaurantButton = $x("//h4[text()=\"რესტორანი\"]");
    public SelenideElement noResultFound = $x("//h2[text()=\"შეთავაზება არ მოიძებნა\"]");
    public SelenideElement productsDiv = $("div[class*=\"grid-flow-row\"]");
    public ElementsCollection productElements = productsDiv.$$x(".//a[contains(@class, 'group flex flex-col')]");
    public SelenideElement paginatorDiv = $("div[class*='justify-center gap-1']");// //div[contains(@class,'justify-center gap-1')]
    public SelenideElement productsPage2 = paginatorDiv.$x(".//div[text()=\"2\"]");
    public SelenideElement productsPage3 = paginatorDiv.$x(".//div[text()=\"3\"]");
    public SelenideElement totalPagesNumber = paginatorDiv.$x(".//div[last()-1]");
    public SelenideElement activePageNumber = paginatorDiv.$("div[class*=\"bg-primary_green-110-value\"]");
    public SelenideElement rightArrow = paginatorDiv.$("img[alt='right arrow']");
    public SelenideElement leftArrow = paginatorDiv.$("img[alt='left arrow']");
    public SelenideElement locationButton = $("[data-testid='tertiary-button'] img[alt='location.svg']").closest("button");
    public SelenideElement mapContainer = $(".leaflet-container");
    public SelenideElement kvebaButton = $("a[href=\"/category/15/kveba/\"]");
    public ElementsCollection guestFiltersDiv = $$("div[id^='headlessui-disclosure-panel-'] label");
    public SelenideElement guest_2_5 = guestFiltersDiv.findBy(partialText("2-5"));
    public SelenideElement guest_6_10 = guestFiltersDiv.findBy(partialText("6-10"));
    public SelenideElement guest_11_15 = guestFiltersDiv.findBy(partialText("11-15"));
    public SelenideElement languageChangeButton = $("div[aria-haspopup=\"menu\"]");
    public SelenideElement changeToEnglish = $x("//button//p[text()=\"English\"]");
    public SelenideElement mainDiv = $("#__next");
    public SelenideElement cartButton = $("img[src=\"/icons/basket-black.svg\"]");
}

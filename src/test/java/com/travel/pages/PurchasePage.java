package com.travel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PurchasePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "inputName")
    private WebElement firstName;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "cardType")
    private WebElement cardType;

    @FindBy(id = "creditCardNumber")
    private WebElement cardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement cardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement cardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(css = "input[type='submit']")
    private WebElement purchaseButton;

    @FindBy(css = "h1")
    private WebElement confirmationHeading;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String value) {
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void enterLastName(String value) {
        // BlazeDemo's demo form has one name field; append last name to represent
        // a full customer name while keeping the PageFactory pattern realistic.
        firstName.sendKeys(" " + value);
    }

    public void enterAddress(String value) {
        address.clear();
        address.sendKeys(value);
    }

    public void enterCity(String value) {
        city.clear();
        city.sendKeys(value);
    }

    public void enterState(String value) {
        state.clear();
        state.sendKeys(value);
    }

    public void enterZip(String value) {
        zipCode.clear();
        zipCode.sendKeys(value);
    }

    public void selectCardType(String value) {
        new Select(cardType).selectByVisibleText(value);
    }

    public void enterCardNumber(String value) {
        cardNumber.clear();
        cardNumber.sendKeys(value);
    }

    public void enterCardMonth(String value) {
        cardMonth.clear();
        cardMonth.sendKeys(value);
    }

    public void enterCardYear(String value) {
        cardYear.clear();
        cardYear.sendKeys(value);
    }

    public void enterCardName(String value) {
        nameOnCard.clear();
        nameOnCard.sendKeys(value);
    }

    public void purchase() {
        purchaseButton.click();
    }

    public boolean isReservationConfirmed() {
        return wait.until(d -> confirmationHeading.isDisplayed())
                && confirmationHeading.getText().contains("Thank you for your purchase");
    }
}

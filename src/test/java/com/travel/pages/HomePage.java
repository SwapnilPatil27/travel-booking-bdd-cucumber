package com.travel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name = "fromPort")
    private WebElement departureCity;

    @FindBy(name = "toPort")
    private WebElement destinationCity;

    @FindBy(css = "input[type='submit']")
    private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void selectDeparture(String city) {
        new Select(departureCity).selectByVisibleText(city);
    }

    public void selectDestination(String city) {
        new Select(destinationCity).selectByVisibleText(city);
    }

    public void searchFlights() {
        findFlightsButton.click();
    }

    public boolean isSearchPageDisplayed() {
        return driver.getCurrentUrl().contains("blazedemo.com");
    }
}

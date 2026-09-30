package com.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlightResultsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By flightRows = By.cssSelector("table.table tbody tr");
    private final By chooseFlightButtons = By.cssSelector("input[type='submit']");

    public FlightResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isResultsDisplayed() {
        return wait.until(d -> d.findElements(flightRows).size() > 0);
    }

    public void selectFirstFlight() {
        List<WebElement> buttons = driver.findElements(chooseFlightButtons);
        if (buttons.isEmpty()) {
            throw new IllegalStateException("No available flights found.");
        }
        buttons.get(0).click();
    }
}

package com.travel.steps;

import com.travel.config.ConfigReader;
import com.travel.driver.DriverFactory;
import com.travel.pages.FlightResultsPage;
import com.travel.pages.HomePage;
import com.travel.pages.PurchasePage;
import io.cucumber.java.en.*;

import org.testng.Assert;

public class FlightBookingSteps {
    private HomePage homePage;
    private FlightResultsPage resultsPage;
    private PurchasePage purchasePage;

    @Given("I open the travel booking application")
    public void openApplication() {
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.open(ConfigReader.get("baseUrl"));
    }

    @When("I select departure city {string}")
    public void selectDeparture(String city) {
        homePage.selectDeparture(city);
    }

    @When("I select destination city {string}")
    public void selectDestination(String city) {
        homePage.selectDestination(city);
    }

    @When("I search for available flights")
    public void searchFlights() {
        homePage.searchFlights();
    }

    @Then("I should see the flight results page")
    public void verifyResults() {
        resultsPage = new FlightResultsPage(DriverFactory.getDriver());
        Assert.assertTrue(resultsPage.isResultsDisplayed(),
                "Flight results were not displayed.");
    }

    @When("I select the first available flight")
    public void selectFirstFlight() {
        resultsPage.selectFirstFlight();
        purchasePage = new PurchasePage(DriverFactory.getDriver());
    }

    @When("I enter passenger first name {string}")
    public void enterFirstName(String value) {
        purchasePage.enterFirstName(value);
    }

    @When("I enter passenger last name {string}")
    public void enterLastName(String value) {
        purchasePage.enterLastName(value);
    }

    @When("I enter billing address {string}")
    public void enterAddress(String value) {
        purchasePage.enterAddress(value);
    }

    @When("I enter city {string}")
    public void enterCity(String value) {
        purchasePage.enterCity(value);
    }

    @When("I enter state {string}")
    public void enterState(String value) {
        purchasePage.enterState(value);
    }

    @When("I enter zip code {string}")
    public void enterZip(String value) {
        purchasePage.enterZip(value);
    }

    @When("I select card type {string}")
    public void selectCardType(String value) {
        purchasePage.selectCardType(value);
    }

    @When("I enter card number {string}")
    public void enterCardNumber(String value) {
        purchasePage.enterCardNumber(value);
    }

    @When("I enter card month {string}")
    public void enterCardMonth(String value) {
        purchasePage.enterCardMonth(value);
    }

    @When("I enter card year {string}")
    public void enterCardYear(String value) {
        purchasePage.enterCardYear(value);
    }

    @When("I enter card name {string}")
    public void enterCardName(String value) {
        purchasePage.enterCardName(value);
    }

    @When("I click purchase")
    public void clickPurchase() {
        purchasePage.purchase();
    }

    @Then("I should see the reservation confirmation")
    public void verifyConfirmation() {
        Assert.assertTrue(purchasePage.isReservationConfirmed(),
                "Reservation confirmation was not displayed.");
    }

    @Then("I should remain on the travel search page")
    public void verifySearchPage() {
        Assert.assertTrue(homePage.isSearchPageDisplayed(),
                "Expected to remain on the search application.");
    }
}

Feature: Travel flight booking

  As a travel customer
  I want to search and book a flight
  So that I can complete my travel reservation

  Background:
    Given I open the travel booking application

  @smoke @flight
  Scenario: Search and complete a flight reservation
    When I select departure city "Boston"
    And I select destination city "London"
    And I search for available flights
    Then I should see the flight results page
    When I select the first available flight
    And I enter passenger first name "Swapnil"
    And I enter passenger last name "Patil"
    And I enter billing address "Pune"
    And I enter city "Pune"
    And I enter state "Maharashtra"
    And I enter zip code "411001"
    And I select card type "Visa"
    And I enter card number "4111111111111111"
    And I enter card month "12"
    And I enter card year "2028"
    And I enter card name "Swapnil Patil"
    And I click purchase
    Then I should see the reservation confirmation

  @negative
  Scenario: Validate that departure and destination must be selected
    When I search for available flights
    Then I should remain on the travel search page
    
    
    
    

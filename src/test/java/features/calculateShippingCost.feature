Feature: Calculate shipping cost from source to destination for a specific delivery type
  This feature deals with calculating shipping cost from source to destination using Australia Post

  Scenario: Calculate shipping cost based on source, destination and delivery type
    Given User finds postcode for the source and destination
    When User selects a delivery type
    Then Calculate the total shipping cost

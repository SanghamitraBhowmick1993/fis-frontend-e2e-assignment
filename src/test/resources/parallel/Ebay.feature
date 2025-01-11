Feature: Verify item can be added to Cart

  Scenario: Verify item can be added to Cart
    Given I am on ebay homepage
    When I search for "book"
    And I click on the first book in the list
    And I add the book to cart
    Then the cart should display the number of items
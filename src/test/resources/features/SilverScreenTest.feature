Feature: Cinema

  Scenario: Search movie
    Given I open an app
    When I search for "Thing" word
    Then I see the list of movie items
    And each item name or description contains "Thing"
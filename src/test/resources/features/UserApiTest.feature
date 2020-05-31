Feature: Search User API Test

  @qa
  Scenario: Check User by full name
    Given I start execution
    When I search user by "Vova" name
    Then I verify that I got "Vova"

  @qa
  Scenario: Get full list of users
    Given I start execution
    When I search "" with strict true
    Then List of users returned from WS

  @qa
  Scenario: Check Search of User by full strict name
    Given I start execution
    When I search full name "rangaradjangoo" with strict true
    Then List of users returned from WS

  @qa
  Scenario: Check Search of User by short strict name
    Given I start execution
    When I search short name "a" with strict true
    Then List of users returned from WS

  @qa
  Scenario: Check Search of User by full not strict name
    Given I start execution
    When I search long name "rangara" with strict false
    Then List of users returned from WS

  @qa
  Scenario: Check Search of User by short not strict name
    Given I start execution
    When I search short name "s" with strict false
    Then List of users returned from WS

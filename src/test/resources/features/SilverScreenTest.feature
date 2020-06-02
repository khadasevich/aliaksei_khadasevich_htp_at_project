Feature: Cinema

  Scenario: Search movie
    Given I open an app
    When I search for "Thing" word
    Then I see the list of movie items
    And each item name or description contains "Thing"

  @qa
  Scenario: Login app
    Given I open an app
    When I login
    Then I can see Red Carpet Club "Новичок" in upper right corner


  Scenario Outline: Login app blank field
    Given I open an app
    When I left blank <field> field
    Then I see <message> message

    Examples:
      | field    | message                            |
      | login    | Необходимо заполнить поле "E-mail" |
      | password | Необходимо заполнить поле "Пароль" |
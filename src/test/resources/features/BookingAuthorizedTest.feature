Feature: Booking Authorized Tests

# @qa
  Scenario: I login as registered user there is no notification about registration
    Given Open Main Page
    When I login
    Then List of notifications doesn't contain reminder about registration

  @qa
  Scenario Outline: I login as registered user, searching trip with params <city>, <rooms>, <children>, <adults>, <startFrom>, <endingDate> and select first and last item in the list these hotels appear in my wishlist
    Given Open Main Page
    When I login
    And Search trip with param: <city>, <rooms>, <children>, <adults>, <startFrom>, <endingDate> and select hotels
    Then These hotels appear in the wishlist

    Examples:
      | city     | rooms | children | adults | startFrom | endingDate |
      | "Madrid" | 1     | 0        | 2      | 3         | 5          |

#  @qa
  Scenario: I login as registered user and expect to see 12 itesm in the booking header
    Given Open Main Page
    When I login
    Then I expect to see right items in header
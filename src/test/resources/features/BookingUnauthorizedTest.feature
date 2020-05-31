Feature: Booking Unauthorized Tests

  @qa
  Scenario Outline: I search hotel in <city> with <rooms> apartments for <children> and <adults> for a trip <startFrom> and <endingDate>
    Given Open Main Page
    When I search trip with param: <city>, <rooms>, <children>, <adults>, <startFrom>, <endingDate> and filter it by max price and sort by price
    Then I expect price for a night of first in the list > than max price from filter

    Examples:
      | city    | rooms | children | adults | startFrom | endingDate |
      | "Paris" | 2     | 0        | 4      | 3         | 10         |

  @qa
  Scenario Outline: I search hotel in <city> with <rooms> apartments for <children> and <adults> for a trip <startFrom> and <endingDate>
    Given Open Main Page
    When I search trip with param: <city>, <rooms>, <children>, <adults>, <startFrom>, <endingDate> and filter result by min price
    Then I expect price of first item in the list < then value in the filter

    Examples:
      | city     | rooms | children | adults | startFrom | endingDate |
      | "Moscow" | 2     | 0        | 4      | 10        | 15         |

  @qa
  Scenario Outline: I search hotel in <city> with <rooms> apartments for <children> and <adults> for a trip <startFrom> and <endingDate>
    Given Open Main Page
    When I search trip with param: <city>, <rooms>, <children>, <adults>, <startFrom>, <endingDate> and change background if the item
    Then I expect that item has expected color

    Examples:
      | city   | rooms | children | adults | startFrom | endingDate |
      | "Oslo" | 1     | 2        | 2      | 1         | 2          |





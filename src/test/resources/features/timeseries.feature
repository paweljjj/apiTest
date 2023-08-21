@timeseries
Feature: Check GET /timeseries enpoint

  Scenario: Check 200 OK. Response contains rates for request with all parameters
    Given set api layer base uri
    And set valid authorization header
    And set start date to: '2023-06-26'
    And set end date to: '2023-06-28'
    And set base currency to: 'PLN'
    And set symbols to: 'EUR,PLN,SEK'
    When make rest call GET timeseries
    Then status code is 200
    And response contains rates for given date
      | 2023-06-26 |
      | 2023-06-27 |
      | 2023-06-28 |
    And response does not contains rates for given date
      | 2023-06-25 |
      | 2023-06-29 |

  Scenario: Check 200 OK. Response contains rates for request with mandatory parameters
    Given set api layer base uri
    And set valid authorization header
    And set start date to: '2023-06-10'
    And set end date to: '2023-06-10'
    When make rest call GET timeseries
    Then status code is 200
    And response contains rates for given date
      | 2023-06-10 |
    And response does not contains rates for given date
      | 2023-06-09 |
      | 2023-06-11 |

  Scenario: Check 400 BAD_REQUEST. Request does not have mandatory date parameters
    Given set api layer base uri
    And set valid authorization header
    When make rest call GET timeseries
    Then status code is 400

  Scenario: Check 401 UNAUTHORIZED. Request does not have authorization header
    Given set api layer base uri
    And set start date to: '2023-06-28'
    And set end date to: '2023-06-28'
    When make rest call GET timeseries
    Then status code is 401

  Scenario: Check 403 FORBIDDEN. Endpoint does not accept POST request
    Given set api layer base uri
    And set valid authorization header
    And set start date to: '2023-06-28'
    And set end date to: '2023-06-28'
    When make rest call POST timeseries
    Then status code is 403

  Scenario: Check 404 NOT_FOUND. Invalid endpoint is called
    Given set api layer base uri
    And set valid authorization header
    And set start date to: '2030-06-28'
    And set end date to: '2030-06-28'
    When make rest call GET invalid timeseries url
    Then status code is 404

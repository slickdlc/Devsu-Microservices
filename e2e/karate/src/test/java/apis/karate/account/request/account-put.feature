@ignore
Feature: Executes doRequest

  Scenario: doRequest
    * def req = __arg
    Given url urls.accountBaseUrl
    And path req.path
    And request req.body
    When method PUT

    * def expectedStatusCode = req.statusCode || responseStatus
    * match responseStatus == expectedStatusCode
    * def expectedResponse = req.expectedResponse || response
    * match response == expectedResponse
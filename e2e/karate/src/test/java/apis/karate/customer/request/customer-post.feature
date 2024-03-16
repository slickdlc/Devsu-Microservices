@ignore
Feature: Executes doRequest

  Scenario: doRequest
    * def req = __arg
    Given url urls.customerUrl
    And path req.path
    And request req.body
    When method POST

    * def expectedStatusCode = req.statusCode || responseStatus
    * match responseStatus == expectedStatusCode
    * def expectedResponse = req.expectedResponse || response
    * match response == expectedResponse
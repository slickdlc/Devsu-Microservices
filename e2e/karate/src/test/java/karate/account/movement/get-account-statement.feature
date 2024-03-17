Feature: Movement Get Account Statement API

  Background:
    Given def accountGet = read('classpath:apis/karate/account/request/account-get.feature')

  Scenario: Negative Case - Empty parameters
    * def req = read('testdata/getaccountstatement/negative-empty-parameters-request.yml')
    * call accountGet req

  Scenario: Positive Case - Get Account Statement by customer id
    * def req = read('testdata/getaccountstatement/positive-by-customer-id-request.yml')
    Given url urls.accountBaseUrl
    And path req.path
    And request req.body
    And param fechaInicio = "01/03/2024"
    And param fechaFin = "30/04/2024"
    And param clienteId = 5
    When method GET
    
    * match responseStatus == req.statusCode
    * match response == req.expectedResponse

  Scenario: Positive Case - Get Account Statement by customer identification
    * def req = read('testdata/getaccountstatement/positive-by-customer-identification-request.yml')
    Given url urls.accountBaseUrl
    And path req.path
    And request req.body
    And param fechaInicio = "01/03/2024"
    And param fechaFin = "30/04/2024"
    And param clienteIdentification = "71717167"
    When method GET

    * match responseStatus == req.statusCode
    * match response == req.expectedResponse

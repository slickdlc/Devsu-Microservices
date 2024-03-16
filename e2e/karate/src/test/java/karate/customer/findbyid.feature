Feature: Customer Create API

  Background:
    Given def customerGet = read('classpath:apis/karate/customer/request/customer-get.feature')

  Scenario: Negative Case - Invalid Id
    * def req = read('testdata/findbyid/negative-request.yml')
    * call customerGet req

  Scenario: Positive Case - Customer retrieved successfully
    * def req = read('testdata/findbyid/positive-request.yml')
    * call customerGet req

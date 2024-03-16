Feature: Customer Create API

  Background:
    Given def customerGet = read('classpath:apis/karate/customer/request/customer-get.feature')

  Scenario: Positive Case - Customers retrieved successfully
    * def req = read('testdata/getall/positive-request.yml')
    * def response = call customerGet req
    * match response.body != "#[1]"

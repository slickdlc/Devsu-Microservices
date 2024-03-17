Feature: Account Create API

  Background:
    Given def accountGet = read('classpath:apis/karate/account/request/account-get.feature')

  Scenario: Negative Case - Invalid Id
    * def req = read('testdata/findbyid/negative-request.yml')
    * call accountGet req

  Scenario: Positive Case - Account retrieved successfully
    * def req = read('testdata/findbyid/positive-request.yml')
    * call accountGet req

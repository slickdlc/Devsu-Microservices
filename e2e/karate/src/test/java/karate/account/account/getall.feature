Feature: Account Create API

  Background:
    Given def accountGet = read('classpath:apis/karate/account/request/account-get.feature')

  Scenario: Positive Case - Accounts retrieved successfully
    * def req = read('testdata/getall/positive-request.yml')
    * def response = call accountGet req
    * match response.body != "#[0]"

Feature: Account Create API

  Background:
    Given def accountDelete = read('classpath:apis/karate/account/request/account-delete.feature')

  Scenario: Negative Case - Invalid Id
    * def req = read('testdata/delete/negative-invalid-id-request.yml')
    * call accountDelete req

  Scenario: Negative Case - Account id with movements
    * def req = read('testdata/delete/negative-account-id-with-movements-request.yml')
    * call accountDelete req

  Scenario: Positive Case - Account deleted
    * def req = read('testdata/delete/positive-request.yml')
    * call accountDelete req

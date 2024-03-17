Feature: Account Create API

  Background:
    Given def accountPut = read('classpath:apis/karate/account/request/account-put.feature')

  Scenario: Negative Case - Identification duplicated
    * def req = read('testdata/update/negative-duplicate-identification-request.yml')
    * call accountPut req

  Scenario: Negative Case - Invalid Id Request
    * def req = read('testdata/update/negative-invalid-id-request.yml')
    * call accountPut req

  Scenario: Positive Case - Account updated successfully
    * def req = read('testdata/update/positive-request.yml')
    * call accountPut req

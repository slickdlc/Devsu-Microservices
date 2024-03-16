Feature: Customer Create API

  Background:
    Given def customerPut = read('classpath:apis/karate/customer/request/customer-put.feature')

  Scenario: Negative Case - Identification duplicated
    * def req = read('testdata/update/negative-duplicate-identification-request.yml')
    * call customerPut req

  Scenario: Negative Case - Invalid Id Request
    * def req = read('testdata/update/negative-invalid-id-request.yml')
    * call customerPut req

  Scenario: Positive Case - Customer updated successfully
    * def req = read('testdata/update/positive-request.yml')
    * call customerPut req

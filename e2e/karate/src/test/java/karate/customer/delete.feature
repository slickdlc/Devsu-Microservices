Feature: Customer Create API

  Background:
    Given def customerDelete = read('classpath:apis/karate/customer/request/customer-delete.feature')

  Scenario: Negative Case - Invalid Id
    * def req = read('testdata/delete/negative-invalid-id-request.yml')
    * call customerDelete req

  Scenario: Positive Case - Customer deleted
    * def req = read('testdata/delete/positive-request.yml')
    * call customerDelete req

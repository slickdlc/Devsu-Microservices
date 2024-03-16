Feature: Customer Create API

  Background:
    Given def customerPost = read('classpath:apis/karate/customer/request/customer-post.feature')

  Scenario: Negative Case - Bad Identification
    * def req = read('testdata/create/negative-request-bad-identification.yml')
    * call customerPost req

  Scenario: Negative Case - Bad Parameters
    * def req = read('testdata/create/negative-request-bad-parameters.yml')
    * call customerPost req

  Scenario: Positive Case - Customer created
    * def req = read('testdata/create/positive-request.yml')
    * call customerPost req

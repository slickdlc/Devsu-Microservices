Feature: Movement Add API

  Background:
    Given def accountPost = read('classpath:apis/karate/account/request/account-post.feature')

  Scenario: Negative Case - Customer Id Is Not Active
    * def req = read('testdata/add/negative-customer-is-not-active-request.yml')
    * call accountPost req

  Scenario: Negative Case - Empty Parameters
    * def req = read('testdata/add/negative-empty-parameters-request.yml')
    * call accountPost req

  Scenario: Negative Case - Timestamp Too Early
    * def req = read('testdata/add/negative-timestamp-too-early-request.yml')
    * call accountPost req

  Scenario: Positive Case - Add Movement by account id
    * def req = read('testdata/add/positive-by-account-id-request.yml')
    * call accountPost req

  Scenario: Positive Case - Add Movement by account number
    * def req = read('testdata/add/positive-by-account-number-request.yml')
    * call accountPost req

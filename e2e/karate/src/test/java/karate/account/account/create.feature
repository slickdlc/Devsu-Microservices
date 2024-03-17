Feature: Account Create API

  Background:
    Given def accountPost = read('classpath:apis/karate/account/request/account-post.feature')

  Scenario: Negative Case - Bad Account Number
    * def req = read('testdata/create/negative-request-bad-account-number.yml')
    * call accountPost req

  Scenario: Negative Case - Bad Parameters
    * def req = read('testdata/create/negative-request-empty-parameters.yml')
    * call accountPost req

  Scenario: Negative Case - Customer Id Is Not Active
    * def req = read('testdata/create/negative-request-customer-is-not-active-request.yml')
    * call accountPost req

  Scenario: Positive Case - Account created
    * def req = read('testdata/create/positive-request.yml')
    * call accountPost req

Feature: Test Gmail Api


  @Gmail
  Scenario: Login and Send mail with Valid Credentials
    Given User send a mail using "user1" credential to "recepient1"
    Then verify that the email has been recieved by the recipient
    When  User send a mail using "user1" credential to "wrongRecepient1"
    Then verify that the email has been recieved by the recipient

  @Gmail
  Scenario: Login and Send mail with attachment with Valid Credentials
    Given User send a mail with attatchmtn using "user1" credential to "recepient1"
    Then verify that the email has been recieved by the recipient
    When  User send a mail with attatchmtn using "user1" credential to "wrongRecepient1"


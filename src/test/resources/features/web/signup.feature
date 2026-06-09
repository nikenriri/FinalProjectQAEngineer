Feature: Sign up feature for WEB UI

  Scenario: User sign up using the valid data
    Given User in the homepage
    When User click on menu "Sign up"
    And User fill out the username "dadang dudung"
    And User fill out the password "dadangdudung123"
    And User click on the sign up button
    Then System shows the succesfull message "Sign up successful."
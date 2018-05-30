Feature: System allows registering a new user

  Scenario: Registration single user is performed properly
    Given John Deere who was born in 1979-05-17 and lives in Chicago Ill
    When Submit button has been clicked
    Then User is added to system registry


  Scenario: Registration of many user is performed properly
    Given list of users to register
      | name      | surname      | city      | login      | year | month | day |
      | Testname1 | testsurname1 | testcity1 | testlogin1 | 1974 | 11    | 02  |
      | Testname2 | testsurname2 | testcity2 | testlogin2 | 1989 | 01    | 08  |
      | Testname3 | testsurname3 | testcity3 | testlogin3 | 1994 | 05    | 21  |

    When POST Request are send to system
    Then System repository contains all of those users
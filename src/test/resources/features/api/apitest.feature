Feature: API CRUD Test

  Scenario: Complete User CRUD Cycle
    Given base API URL "https://dummyapi.io/data/v1/"
    And authentication using app-id token

    # 1. POST (Create User)
    When I send a POST request to create a new user with body:
      """
      {
        "title": "miss",
        "firstName": "Pevita",
        "lastName": "Carroline",
        "picture": "https://drive.google.com/file/d/1JDnfa31USaqoL2g2xBLsjuZkMwGR2fQX/view?usp=sharing",
        "gender": "female",
        "email": "pevita02@example.com",
        "dateOfBirth": "1979-08-14T14:44:52.955Z",
        "phone": "923-955-2282",
        "location": {
             "street": "3425, Washington",
             "city": "Bluetown",
             "state": "United State",
             "country": "Washington",
             "timezone": "+6:00"
        }
      }
      """
    Then the API response status code should be 200
    And the response field "firstName" should be "Pevita"

    # 2. GET (Read User)
    When I send a GET request to the endpoint to get all of the users list
    Then the API response status code should be 200
#    And the response field "data[0].firstName" should be "Charlotte"

    # 3. PUT (Update User)
    When I send a PUT request for id "60d0fe4f5311236168a109f6" with body:
      """
      {
        "title": "miss",
        "firstName": "Rere",
        "lastName": "Agustine",
        "picture": "https://randomuser.me/api/portraits/med/women/15.jpg",
        "gender": "female",
        "email": "madison.ambrose@example.com",
        "dateOfBirth": "1947-04-14T14:44:52.955Z",
        "phone": "756-040-5467",
        "location": {
            "street": "4122, Stanley Way",
            "city": "Cornwall",
            "state": "British Columbia",
            "country": "Canada",
            "timezone": "+6:00"
        }
      }
      """
    Then the API response status code should be 200
    And the response field "lastName" should be "Agustine"

    # 4. GET (all tags)
    When I send a GET request to the endpoint to get all of the tags
    Then the API response status code should be 200

    # 5. DELETE (Delete User)
    When I send a delete request for id "60d0fe4f5311236168a10a13"
    Then the API response status code should be 200
    And the response should return the correct deleted user ID

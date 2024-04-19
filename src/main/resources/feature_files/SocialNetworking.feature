@TestToRun
Feature: Social Networking
  As a User
  I want to be able to use the service - JSONPlaceholder - Free Fake REST API
  so that I can create posts or comments or Users

  Background:
#    Given service jsonplaceholder service is up and running


  Scenario Outline:Get a specific post
    When I send GET request to get a specific post with "<id>"
    Then the specific post details "<id>", "<title>" and "<body>"  are returned with status code of 200
    Examples:
      | id | title        | body                                                                                                                                                                                                              |
      | 2  | qui est esse | est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla |

#  @TestToRun
  Scenario Outline:Create a new post
    When I make a post with the following details "<userId>", "<title>" and "<body>"
    Then I should get a response with status code of 201 and the following "<userId>", "<title>" and "<body>"
    Examples:
      | userId | title             | body                             |
      | 2      | My recent Holiday | I enjoyed my Holiday. It was fun |


#  @TestToRun
  Scenario Outline: Update a post
    When I update an existing post with "<id>"  using the following details "<userId>", "<title>" and "<body>"
    Then I should get a response with status code of 200 and the following "<userId>", "<title>" and "<body>" for Put
    Examples:
      | id | userId | title             | body                             |
      | 20 | 2      | My recent Holiday | I enjoyed my Holiday. It was fun |

  @TestToRun
  Scenario Outline:Delete a specific post
    When I send DELETE request for a specific post with "<id>"
    Then the status code of 200 is returned
    Examples:
      | id |
      | 2  |
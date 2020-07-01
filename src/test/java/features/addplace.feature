Feature: validating Place Api's

@AddPlace
Scenario Outline: User will add the employee
     Given Add place payload with "<name>" "<language>" "<address>"
     When user calls "AddPlaceApi" with "POST" http request
     Then the Api call got Success with Status code "200"
     And "status" in Response body is "OK"
     And Verify place_id created maps to "<name>" using "GetPlaceApi"
     
Examples:
    |name | language | address  |
    |Shubu| English  | bangalore|
#    |Alok | Hindi    | bangalore|
    
     
@DeletePlace 
Scenario: Verify if delete place functionality is working 
      
      Given delete place payload
      When user calls "DeletePlaceApi" with "POST" http request
      Then the Api call got Success with Status code "200"
      And "status" in Response body is "OK"
# Api testing framework with serenity and cucumber
This framework can be used for REST API testing.


#Prerequisites: 
- maven3, java8 or greater

#Capabilities:
- do REST API (GET/POST/PUT/DELETE)calls to predefined endpoints
- check response code, headers,response body using jsonPath
- check number of items returned
- deserialize predefined endpoints response to Object for assertions - 

#Structure:
- src/test/java/tests - Test runners
- src/main/resources/features - Feature files
- src/main/resources/baselines - baselines files e.g. Response JSON Schema
- src/main/resources/configs - config files
- src/test/java/ - Cucumber stepdefinitions and suporting code

#How to run:
- Go to src/test/java/tests and run EmployeeTestsSuite01 - will run all features files from src/main/resources/features
- Run from maven command: mvn test -Dcucumber.options="{featureFilePath}"



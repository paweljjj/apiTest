# api test

This project is do some example API tests. As a test API `apilayer.com` was chosen. For testing purposes only one
endpoint is checked.

While I was creating this project I had 3 things in my mind:

- to write tests using Cucumber
- to run tests in parallel
- to be open for extensions

## tech stack:
- Java 17
- Rest Assured
- Spring boot
- Cucumber
- Lombok

## quick start

1. Update `apilayer.apikey` in environment.properties file with your private key obtained from apilayer.com

2. To run all tests:
   `mvn clean test`

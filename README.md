# Solving Test Automation Task
================================================================
## Scope
* This program is used to test the website http://the-internet.herokuapp.com with scenarios that are specified in the task sheet 'Test Automation Engineer | Candidate Task'.
* There is one doubt in scenarior 1, which is the string 'You logged into a secure area is present'. The string presents in website is 'You logged into a secure area!'. However, the test currently follows the content in task sheet and so this test case get fail now.

## Technology

* Tests are written using Selenium framework and JUnit.
* Use geckodriver for executing test in firefox and chromedriver for chrome
* Java as programming language and Maven to build
* For creating report file in html format, maven surefire plugin is used. 
* Additionally, you can install Katalon Recorder in your browser. With this plugin, you can record the actions we make on the site and Katalon provides functionality to transfer the actions to your favorite programming language. In my case i use Java.
And Developer Tools also help in some cases. 

## How to use
#### Requisites: 
Java & Maven are installed in your machine. (Java >= 1.8 and Maven >= 3 )

#### Steps:
* Dowload source code from git hub (link: https://github.com/ngahoang1/TestAutomationTask ) to your local machine and store at 'my_folder'
* Navigate to 'my_folder'
* Run cmd command: 'runTests.bat'
* Find test results at: '$my_folder/target/site/surefire-report.html'







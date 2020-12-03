Kotlin Tutorials
====================
This project is a collection of small and focused tutorials - each covering a single and well defined area of development in the Kotlin ecosystem.
All the tutorials can be found on https://www.baeldung.com/kotlin 


Building the project
====================

To build the entire repository with only Unit Tests enabled run:

`mvn clean install`

or if we want to build the entire repository with Integration Tests enabled, we can do:

`mvn clean install -Pintegration`

This should not be needed often as we are usually concerned with a specific module.


Building a single module
====================
To build a specific module run the command: `mvn clean install` in the module directory.


Working with the IDE
====================
This repo contains a large number of modules. 
When you're working with an individual module, there's no need to import all of them (or build all of them) - you can simply import that particular module in either Eclipse or IntelliJ. 


Running Tests
=============
The command `mvn clean install` from within a module will run the unit tests in that module.
For Spring modules this will also run the `SpringContextTest` if present.

To run the integration tests, use the command:

`mvn clean install -Pintegration` 


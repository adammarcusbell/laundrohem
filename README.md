<h2>Laundrohem Application</h2>

---
In this example codebase my intention has been to build something that pointed towards the solution I would be satisfied with, rather than a solution which reflected the time constraints or the simplicity of the task.  It is, by no means how I would spend two days in building something that had a strict two day deadline and just needed to "work", that solution would be far simpler and rudimentary.


---
**The Problem**

Build a platform that allows 20 residents sharing two laundry rooms to book, review & cancel bookings for either facility for a specified period of time between 7-22. 

*Problems of authentication, load testing, deployment, backups etc have been ignored as being beyond the scope of this assignment.*

---
**The Primary Drivers**

The primary drivers affecting the technical direction of the small solution were to,

* Establish a consistent working environment for a team (in this case the interviewers) to enable common understanding and predictability of outcome (build, test, consistent environment)
* Establish a clear technical approach that is structured and more or less self evident, and is capable of changes/update/extension-
* Testing that covered the largest proportion of functionality with the minimal amount of effort 
* Take into account the suitability of technologies based upon their widespread adoption 

---
**The Solution**

The solution to the aforementioned problem is comprised of the following.
* A Spring Boot Application written in Kotlin and utilising,
  * Spring Webflux
  * Spring Boot
  * Spring R2DBC
  * Spring JDBC (for the Liquibase functionality to work)
* Liquibase for version control of the SQL Database
* Test Fixtures that provide common test data for both Integration and Unit Tests
* Postgres Database for persistence
* Gradle DSL for the build mechanism
* Docker and Docker Compose for a replicable operating environment
* Swagger in combination with SpringDoc to provide easy viewing and experimentation of the API for convenience

The directory structure is a typical environment for the stack, comprising of source directories for core, test and integration test of the application.  The only items of note being the SQL scripts found in the resources directory of main that provide Liquibase with the creation of the database schema and initial dataset.

The API that is visible via the Swagger UI provides the basic requirements of the imagined application UI (be it an Mobile App or Web Application).  This use case being of the following steps.

1. User lists available Laundry Rooms and their current Bookings.
2. User selects a Laundry Room and time for a Booking and submits the request.
3. User selects the Booking for deletion/cancellation.

This scenario can be viewed from a testing point of view in the BookingIntegrationTest.

Although, some of the technical solutions are not as thorough as they need to be within a working application, the idea was to create a base application from which a team could collaborate around accepted and debatable technical ideas.

---
**Starting up The Solution**

Follow these steps to start up a working environment (it is assumed that this is running on a devs machine, and requirements such as Docker are already in position)

Straight forward docker compose run;
* Build the Spring Boot Jar using './gradlew bootJar'
* Run 'docker-compose up -d'
* View the API through the Swagger UI at http://localhost:8080/swagger-ui.html
* To stop the containers - 'docker-compose stop'

To start the application via an IDE (allowing the stepping-through of the codebase);
* Run 'docker-compose up -d laundrohempostgresdb' to start the database
* Then set the following Environment Variables in the IDE,
  *  POSTGRES_HOST = localhost
  *  POSTGRES_USERNAME=laundrohemdb
  *  POSTGRES_PASSWORD=password
  *  POSTGRES_DATABASE=laundrohemdb
  *  SPRING_PROFILES_ACTIVE=test
* Select bootRun in the Gradle panel or './gradlew bootRun'
* View the API through the Swagger UI at http://localhost:8080/swagger-ui.html

Once the application has been created (run once so that the tables have been created etc), the Integration Tests can be executed.

---
**The Imagined Solution**

To provide some kind of background to where my mind was going in regards to design decisions, initially I wanted to create something where an Appliance was bookable as well as the entire Laundry Room.  This granularity would have offered a better experience for the user, and a more efficient utilisation of the Appliances themselves.

Interfaces are intended to have the potential to be serviced by different backend systems that provide more sophisticated state information in relation to the Appliance.

---
**Initial things to do**

If this was a real project, I would recommend the following tasks to tighten up the presented solution.

* Account for the 7-22 time limitation - to do this properly would require setting a timezone for the building in which the Laundry Room is in, that would allow for the correct method of storing all temporal data as UTC.
* Use the status of the Booking - instead of simply deleting it mark it as CANCELLED
* More tests for invalid input and non-standard scenarios
* Exception Handling
* More sophisticated querying of Bookings based on Booking query object  
* Logging 
* Unit Testing with Mocks
* More comprehensive validation of input
* Security that would provide a more customised and secured data experience - allowing for visibility and administration of only a users bookings.
* Move the test data into a file that can be consumed by the postgres COPY command as well as the kotlin Test Data to ensure consistency

---
**Lessons Learnt**

The use of a Postgres database was necessary due to the relational nature of the data model, however, as a result of using a reactive approach to the solution, the need for the relatively under-developed R2DBC was essential.  This library is less mature than the other reactive libraries that service databases such as Neo4J and Mongo.  This resulted in having to perform queries for nested objects manually.

In hindsight it would have been more prudent to either use Neo4J (which I wanted to avoid due to its less than ubiquitous use), or adopt a more traditional MVC approach to the API which would have allowed for the use of more tried and tested ORM layers.

A more focused approach to the specific Laundry Room use case would have been beneficial, rather than attempting to create a generic Bookable that could be reused for other applications. Although, combining PoC/exploratory methods like this offer advantages in a real project with long terms aims.

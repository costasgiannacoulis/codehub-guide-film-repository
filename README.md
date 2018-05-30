# Code.Hub Film Repository
This project acts as the basis of the Java-Spring seminar presentation. In this code base, we will implement a series of features covering the basics of a Spring Boot REST-enabled application with an RDBMS repository.

### Database generation
Database change management is done via Flyway migration tool. You may find existing scripts under **src/resources/db/migration**. Scripts run automatically during application bootstrap process. In case we need to reset the database with initial structure and data, issue the following Maven command `mvnw flyway:clean`.

### Appendix: Maven commands
Find below some useful Maven commands that should be used throughout the lifecycle of this project for libraries and plugins maintenance along with DevOps purposes.

#### Maven versions check

Periodically, we should run these commands
* `mvnw versions:display-dependency-updates` to scan a project's dependencies and produces a report of those dependencies which have newer versions available.
* `mvnw versions:display-property-updates` to scan a project and produces a report of those properties which are used to control artifact versions and which properties have newer versions available.
* `mvnw versions:display-plugin-updates` to scan project's dependencies and produces a report of those dependencies which have newer versions available.
* `mvnw versions:use-latest-releases` to update project dependencies with their latest releases. Since this command is going to modify pom files, in order to finalize changes we must either run `mvnw versions:commit` to save or `mvnw versions:revert` to revert changes being made.

#### Maven project's version change
In order to massively modify the project's version to all pom files, run the command `mvnw versions:set -DnewVersion=<VERSION>`.
Since this command is going to modify pom files, we must either run `mvnw versions:commit` to save or `mvnw versions:revert` to revert changes being made.

#### Maven project tagging
Every time we release to production either a normal release or a hotfix release, we need to tag right after the merge is concluded in the **master** branch.
In order to achieve this, we need to run the following command`mvnw scm:tag -Dtag=<VERSION>`.
````

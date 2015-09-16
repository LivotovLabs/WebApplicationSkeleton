# Web Application Skeleton

This is a ready to use, gradle powered, pure Java, lightweight and scalable and without the fat JEE stuff, web-application template, which contains all the major modules, typically required for every typical project - backend, REST API with client, web UI. 

Use it as starting point for your new Java Web App - just copy to your new project folder.

## Parts included
- WAR packaging, suitable to run on any Servlet 3.0 container
- IoC by Google Guice v4
- Timer services by Quartz
- Database objects management by Hibernate
- REST API, based on Jersey (server) and Retrofit (client connector)
- Web UI by Vaadin v7
- Embedded web server runner, so you can deploy it right from git - pull and then run appStartWar gradle task.

Each part contains the complete working sample for easy copying and extending.

Entire skeleton is managed by the gradle build script and runnable out of the box. Also, thanks to the great gradle support in Intellij IDEA, it is simple to create the IDE project for the skeleton - just import the root folder from your Intellij IDEA and it will build the IDE project ready to code and refactor.

## Quick Test
Clone from git and run ```./gradlew appStartWar``` gradle task, then go to:

- http://localhost:8080 for the web UI skeleton
- http://localhost:8080/api/v1/log for the sample REST resource
- Run the ```eu.livotov.labs.webskel.client.v1.ApiClient.java``` class (it contains the main() method) from the ```client``` module to check the REST API client sample.
- Explore the sources
- Copy and adjust for your own project

Documentation and code insight will be added later.

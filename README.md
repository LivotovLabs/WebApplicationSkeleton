# Web Application Skeleton

This is a ready to use, pure Java, lightweight and scalable, without the fat JEE stuff, web-application skeleton, which contains all the major modules, 
typically required for every typical project - backend, REST API with client, web UI. 

## Parts included
- WAR packaging, suitable to run on any Servlet 3.0 container
- IoC by Google Guice v4
- Timer services by Quartz
- Database objects management by Hibernate
- REST API, based on Jersey (server) and Retrofit (client connector)
- Web UI by Vaadin v7

Each part contains the complete working sample for easy copying and extending.

Entire skeleton is managed by the gradle build scripts and runnable out of the box. Also, thanks to the great gradle support in Intellij IDEA, 
it is simple to create the IDE project for the skeleton - just import the root folder from your IDEA and it will take over.

## Quick Test
Checkout and run ```gradle appStartWar``` , then go to:

- http://localhost:8080/server for the web UI skeleton
- http://localhost:8080/server/api/v1/log for the sample REST resource
- Run the ```eu.livotov.labs.webskel.client.v1.ApiClient.java``` class from the ```client``` module to check the REST API client
- Explore the sources
- Copy and adjust for your own project

Documentation and code insight will be added later.

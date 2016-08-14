# Web Application Skeleton

This is a ready to use, gradle powered, pure Java, lightweight and scalable and without the fat JEE stuff, web-application template, which contains all the major modules, typically required for every typical project - backend, REST API with client, web UI. 

Use it as starting point for your new Java Web App - just copy to your new project folder.

## Parts included
- WAR packaging, suitable to run on any Servlet 3.0 container
- IoC by Google Guice v4
- Timer services by Quartz
- Database objects management by Hibernate
- REST API, based on Jersey (server side) and Retrofit (client side)
- Web UI examples: one by by Vaadin v7 ("server" module), another one by Apache Wicket ("front" module)
- Embedded gretty web runner, so you can deploy it right from git - pull and run appStartWar gradle task.

Each part contains the complete working sample for easy copying and extending.

Entire skeleton is managed by the gradle build script and runnable out of the box. Also, thanks to the great gradle support in Intellij IDEA, it is simple to create the IDE project for the skeleton - just import the root folder from your Intellij IDEA and it will build the IDE project ready to code and refactor.

## Quick Test
Clone from git and run ```./gradlew appStartWar``` gradle task, then go to:

- http://localhost:8080 for the web UI skeleton
- http://localhost:8080/api/v1/log for the sample REST resource
- Explore the sources
- Copy and adjust for your own project

Documentation and code insight will be added later.

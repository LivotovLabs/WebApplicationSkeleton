# WebApplicationSkeleton

This is a ready to use lightweight and scalable web-application skeleton, which contains all the major modules, typically required for every project - backend, REST API with client, web UI. Without the fat JEE stuff.

## Parts included
- Self-running server (Jetty powered) but can be also deployed as a WAR file to other servlter container
- IoC by Google Guice v4
- Timer services by Quartz
- Database objects management by Hibernate
- REST API with buildable client jar by JAX WS and Retrofit (can be used directly in other java and android apps)
- Web UI by Vaadin v7

Each part contains the complete working sample for easy copying and extending

Entire skeleton is managed by the gradle build scripts and runnable out of the box. Also, thanks to the great gradle support in Intellij IDEA, it is simple to create the IDE project for the skeleton - just import the root folder from your IDEA and it will take over.

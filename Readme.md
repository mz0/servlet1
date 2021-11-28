# Servlet Primer

An implementation of recipe
[2021-08-31 How to Start an HTTP Servlet](https://www.twilio.com/blog/java-http-servlets-beginner)

To see it in action, run `./gradlew clean tomcatRun`

Please change line `httpPort = 9090` in `build.gradle`
if an Exception 'Failed to bind to /0.0.0.0:9090' is thrown.

To stop, run `./gradlew tomcatRun`. To start Tomcat again, you may have
to identify and kill Java process, left by `tomcat` plugin.
This plugin is defective a.t.m. ;)

Requires Java 8+, tested on Java 11

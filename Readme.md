# Servlet Primer

An implementation of recipe
[2021-08-31 How to Start an HTTP Servlet](https://www.twilio.com/blog/java-http-servlets-beginner)

To see it in action, run `./gradlew clean appRun`

Please change line `httpPort = 9090` in `build.gradle`
if an Exception 'Failed to bind to /0.0.0.0:9090' is thrown.

In that case you may have to identify and kill Java process, left by Gretty plugin.
It seems Gretty has no idea of a clean test ;)

Requires Java 8+, tested on Java 11

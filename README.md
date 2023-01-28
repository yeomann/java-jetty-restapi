##  Resources
- `mvn clean install` ("clean" is a phase of the clean lifecycle, "install" is a phase of the default lifecycle)
- Maven Resources Plugin: https://maven.apache.org/plugins/maven-resources-plugin/index.html
- Maven repository: https://mvnrepository.com/ 
- See plugin available commands (goals):
  - example; failsafe plugin: https://maven.apache.org/surefire/maven-failsafe-plugin/plugin-info.html
  - example; compiler plugin: https://maven.apache.org/plugins/maven-compiler-plugin/plugin-info.html
- Maven build phases: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
- Java compiler args: https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javac.html
  - Werror
  - Xlint
  - etc...
- Maven Dependency Plugin: https://maven.apache.org/plugins/maven-dependency-plugin/
- Logback Configuration: https://logback.qos.ch/manual/configuration.html

## Run: verify SSL enabled Localhost
- `curl -vvv https://localhost:8443` (got a response from jetty but curl failed to verify the legitimacy of the server and therefore could not
establish a secure connection to it)
- `curl -k -vvv https://localhost:8443` (-k to ignore ssl legitimacy error)
- best way with certification in curl params `curl --cacert my-app-server/src/main/resources/certs/ca.crt -is https://localhost:8443`

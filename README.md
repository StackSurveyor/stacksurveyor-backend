# StackSurveyor Backend

This is the codebase for StackSurveyor's backend API.

Here's a list of technologies used at the moment:
 - JDK 8
 - Spring Boot
 - Maven

## Running it locally
In order to run the backend API locally, you need to have two things installed.
First, you need to have JDK 8, Apache Cassandra, and Maven installed in order to run
the project.

Once you have made modifications, here's how you build the project:
 1. Rename `application.example.properties` to `application.properties` and fill in the credentials. This can be found at
    `src/main/resources`.
 2. Open `cqlsh` and run `CREATE KEYSPACE stacksurveyor WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };` to create
    a keyspace for the API.
 3. Run `mvn install` to install all of the dependencies (You can also make your
    IDE do it).
 4. Then run `mvn package` in order to build the project and convert it to
a JAR file.
 5. Once you have successfully built the project, run `java -jar target/backend-0.0.1.jar`.
 6. Now go to `127.0.0.1:4040` and you will see the backend running.


## License

This project is licensed under the [GNU General Public License version
3](https://www.gnu.org/licenses/gpl-3.0.html) and our copy of the license can be found in the
[COPYING.md](COPYING.md) file.

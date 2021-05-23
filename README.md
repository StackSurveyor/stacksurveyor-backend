# StackSurveyor Backend

This is the codebase for StackSurveyor's backend API.

Here's a list of technologies used at the moment:
 - JDK 8
 - Spring Boot
 - Maven
 - Docker

## Running it locally
In order to run the backend API locally, you need to have two things installed.
First, you need to have JDK 8, Docker, and Maven installed in order to build
the project.

Once you have made modifications, here's how you build the project:
 1. Run `mvn install` to install all of the dependencies (You can also make your
    IDE do it).
 2. Then run `mvn package -DskipTests` in order to build the project and convert it to
a JAR file. We are using `-DskinTests` here because Docker URI's are not recognized by Maven's
    tests and they result in the tests failing when the app cannot connect to DB using Docker's URI or something else.
 3. Once you have successfully built the project, run `docker-compose up` and/or add the `-D` flag to detach to the container.
 4. Now go to `127.0.0.1:8080` and you will see the backend running.


## License

This project is licensed under the [GNU General Public License version
3](https://www.gnu.org/licenses/gpl-3.0.html) and our copy of the license can be found in the
[COPYING.md](COPYING.md) file.

MASTER [![Build Status - MASTER](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=master)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/master/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

DEV [![Build Status - DEV](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=dev)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/dev/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

Sept 2016, © FEXCO Software Group

# FEXCO_postcode - [TASK PROPOSAL](TASK_PROPOSAL.md)

This application was generated using JHipster, you can find documentation and help at [https://jhipster.github.io](https://jhipster.github.io).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools (like
[Bower][] and [BrowserSync][]). You will only need to run this command when dependencies change in package.json.

    npm install

We use [Gulp][] as our build system. Install the Gulp command-line tool globally with:

    npm install -g gulp

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    gulp

Bower is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in `bower.json`. You can also run `bower update` and `bower install` to manage dependencies.
Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.


## Building for production

To optimize the FEXCO_postcode client for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify CSS and JavaScript files. It will also modify `index.html` so it references
these new files.

To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Building and running a Docker image of your application - [MORE INFO](https://jhipster.github.io/docker-compose/)
To create a Docker image of your application, and push it into your Docker registry:

With Maven, type: 

    ./mvnw package -Pprod docker:build

This will package your application with the prod profile, and install the image.

To run this image, use the Docker Compose configuration located in the src/main/docker folder of your application:

    docker-compose -f src/main/docker/app.yml up

This command will start up your application and the services it relies on (database, search engine, JHipster Registry…).

## Testing

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript/` and can be run with:

    gulp test

UI end-to-end tests are powered by [Protractor][], which is built on top of WebDriverJS. They're located in `src/test/javascript/e2e`
and can be run by starting Spring Boot in one terminal (`./mvnw spring-boot:run`) and running the tests (`gulp itest`) in a second one.

Performance tests are run by [Gatling]() and written in Scala. They're located in `src/test/gatling` and can be run with:

    ./mvnw gatling:execute
    
## Continuous Integration
## Test Coverage
## Branching
## Cache Tests
## Profiling - XRebel
## Sonar

[JHipster]: https://jhipster.github.io/
[Gatling]: http://gatling.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Gulp]: http://gulpjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/

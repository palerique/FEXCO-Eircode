MASTER [![Build Status - MASTER](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=master)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/master/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

DEV [![Build Status - DEV](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=dev)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/dev/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

Sept 2016, © FEXCO Software Group

# FEXCO_postcode - [TASK PROPOSAL](TASK_PROPOSAL.md)

Docker REST Web Service API that consumes third-party services to retrieve, persist, cache and serve to other clients.

To avoid reaching the third-party API we chose to persist in an application database every address found.

Moreover we utilize a cache in memory ([Redis][]) that prevents access to the database and allows the application 
be even more performatic.

We consume JSON objects and respond with JSON objects so we chose to persist the addresses found in a NoSQL database 
([MongoDB][]) without normalizing the data. For this type of application it fits well and the impact on performance 
is quite interesting.
    
For simplicity we have created the API client along with the API itself but the ideal scenario is to separate each 
in microservices approach.

Despite being a monolith the application can be scaled easily as it is all containerized using Docker. 
And the client can be easily separated from the backend.

The backend is safe, protected. 
For some client access it is necessary for him to authenticate. 
We chose to use the JSON Web Token (JWT) mechanism. 
Using the JWT approaches allow to use the stateless application architecture (They do not rely on the HTTP Session).

All parts of the application are well tested! We used Gatling, Cucumber, protractor to check the correct operation of the application.

## Continuous Integration

We chose to do continuous integration of the project using Travis-CI: 
[https://travis-ci.org/palerique/FEXCO-Eircode](https://travis-ci.org/palerique/FEXCO-Eircode)

## Test Coverage

To the code test coverage we use Codecov.io. 
Note that this tool takes into account the third-party JavaScript libraries to calculate code coverage, 
so the value is low! 

The goal was to keep all developed backend code near 100% covered.

[https://codecov.io/gh/palerique/FEXCO-Eircode](https://codecov.io/gh/palerique/FEXCO-Eircode)

## Performance - Responses and Requests Throughput:

Suppose that the desired throughput was 2 million responses per month, the application should respond approximately 
67,000 requests per day, if we consider only 8 hours of that day we would have to answer 145 requests per minute.

We did a test using Gatling to check if the application would be able to reach this throughput.

The result was... TODO:

## Branching

Regarding branching strategy there are two very interesting approaches that fit in certain cases. 
For the development of this web service we chose to have only one main branch and branch development, 
a strategy that is advocated by [Martin Fowler](http://martinfowler.com/bliki/FeatureBranch.html).

In a project with multiple teams and multiple development fronts we could use a more complex model as proposed 
by [Vincent Driessen](http://nvie.com/posts/a-successful-git-branching-model/).

You could access the code repository here [https://github.com/palerique/FEXCO-Eircode](https://github.com/palerique/FEXCO-Eircode).

## About Eircode Third-party API:

We noted that it is necessary a special subscription to access the code database postal Irish.

We contacted the data supplier and they released our access but the credits have been consumed.

We Mockamos these interactions for much of the development and rather used the free TOKEN but there came a time we need to see the application fact interacting with the service.

To facilitate the change of TOKENS we created a system configuration that allows you to switch to another without much effort.

## JHipster:

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

[JHipster]: https://jhipster.github.io/
[Gatling]: http://gatling.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Gulp]: http://gulpjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
[Redis]: http://redis.io/
[MongoDB]: https://www.mongodb.com/

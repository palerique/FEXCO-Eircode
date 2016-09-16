# FEXCO Software Group

## FEXCO-Eircode

### Task Proposal

Develop a docker API service to query for addresses based on their Eircode (Irish post code) using a third party API.

We want to install your API service, query it with some Irish Eircodes and receive JSON response with the address details.
There is a third party API available (free for limited use) with the information you need.

These are the two endpoints that require implementation.
- https://developers.alliescomputing.com/postcoder-web-api/address-lookup/eircode
- https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise

### Why?

Each call to the third party API has a cost of 4.5 credits per request. We expect this API being called by multiple services that all together add up to one million requests per month.
In order to minimize the costs we need to minimize the number of requests to the third party API, without interfering with how the consumer services work.

### What?

The implemented solution must contemplate the following requirements:
- Expose an API that is compatible with and uses the third-party API. (same API options)
- Avoid repeated requests to hit the third party API. A proposed solution is to use an in-memory cache.
- Make sure the previous requests survive on service restarts (e.g. after a new version of your service is deployed). A proposed solution involves a long term persistent mechanism, that preloads the in-memory cache on startup.
- During the development process we expect you to mock the responses from the third-party API (to avoid unnecessarily consuming credits from the 3rd party API)
- A testing client that hits the built service (e.g HTML5/JS app or cURL script)

### Ensure That:
- The developed service is production grade, we expect unit tests and any other testing techniques as well as well designed classes and easily maintainable clean code.
- Use appropriate open source frameworks/libraries where applicable.
- Good usage of git and development workflow (use your public github account).

Sept 2016, © FEXCO Software Group

# FEXCO_Eircode

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

To optimize the FEXCO_Eircode client for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify CSS and JavaScript files. It will also modify `index.html` so it references
these new files.

To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Testing

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript/` and can be run with:

    gulp test

UI end-to-end tests are powered by [Protractor][], which is built on top of WebDriverJS. They're located in `src/test/javascript/e2e`
and can be run by starting Spring Boot in one terminal (`./mvnw spring-boot:run`) and running the tests (`gulp itest`) in a second one.

Performance tests are run by [Gatling]() and written in Scala. They're located in `src/test/gatling` and can be run with:

    ./mvnw gatling:execute

    
## Continuous Integration

To setup this project in Jenkins, use the following configuration:

* Project name: `FEXCO_Eircode`
* Source Code Management
    * Git Repository: `git@github.com:xxxx/FEXCO_Eircode.git`
    * Branches to build: `*/master`
    * Additional Behaviours: `Wipe out repository & force clone`
* Build Triggers
    * Poll SCM / Schedule: `H/5 * * * *`
* Build
    * Invoke Maven / Tasks: `-Pprod clean package`
    * Execute Shell / Command:
        ````
        ./mvnw spring-boot:run &
        bootPid=$!
        sleep 30s
        gulp itest
        kill $bootPid
        ````
* Post-build Actions
    * Publish JUnit test result report / Test Report XMLs: `build/test-results/*.xml,build/reports/e2e/*.xml`

[JHipster]: https://jhipster.github.io/
[Gatling]: http://gatling.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Gulp]: http://gulpjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/

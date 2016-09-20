MASTER [![Build Status - MASTER](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=master)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/master/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

DEV [![Build Status - DEV](https://travis-ci.org/palerique/FEXCO-Eircode.svg?branch=dev)](https://travis-ci.org/palerique/FEXCO-Eircode)
[![codecov](https://codecov.io/gh/palerique/FEXCO-Eircode/branch/dev/graph/badge.svg)](https://codecov.io/gh/palerique/FEXCO-Eircode)

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

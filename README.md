# java-rest-template
## Overview

Testing: [Travis-ci](https://travis-ci.org/leopppp/java-rest-template) [![Build Status](https://travis-ci.org/leopppp/java-rest-template.svg?branch=master)](https://travis-ci.org/leopppp/java-rest-template)

Scenario:

A large micro services project has set course to develop a considerable number of REST API's in the next 12 months. They are aiming to use a standard set of technologies and patterns to bring consistency to their delivery.

As part of this, you are required to build a boilerplate git repository that includes the basic scaffolding required for each team to kickoff their projects.

Your repository should define a comprehensive pipeline that has at least the following stages: test, build, publish.

As part of this, a simple REST API should also be present in the repository that has a root / endpoint that returns a basic "Hello World" message, and a /status endpoint that returns the following response:
```
{
  "myapplication": [
    {
      "version": "1.0",
      "description": "pre-interview technical test",
      "lastcommitsha": "abc57858585"
    }
  ]
}
```

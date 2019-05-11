# java-rest-template
## Overview

Build status: [Travis-ci](https://travis-ci.org/leopppp/java-rest-template) [![Build Status](https://travis-ci.org/leopppp/java-rest-template.svg?branch=master)](https://travis-ci.org/leopppp/java-rest-template)

Java RESTful template project

## API
* `GET /` : Returns "Hello World" message
* `GET /status` : Returns a JSON status object describing the version, description and the current git commit SHA

It is already deployed onto Goolge cloud as a service, which you can access via the Internet.
Click the link below to have a try:
* http://35.244.64.100
* http://35.244.64.100/status

## Docker Image
The docker image is published to: index.docker.io/leopppp/java-rest-template:1.0

## Commands
* `mvn clean install` : Create Java jar file
* `java -jar target/resttemplate-1.0.jar` : Run the application locally, then http://localhost:8080/status should work
* `docker build -t java-rest-template:1.0 --build-arg LAST_COMMIT_SHA=$(git rev-parse HEAD) .` : Build Docker image
* `docker tag java-rest-template:1.0 leopppp/java-rest-template:1.0` : Tag Docker image
* `docker push leopppp/java-rest-template:1.0` : Publish Docker image
* `kubectl create -f deploy.yml --save-config` : Deploy to Google Cloud
* `kubectl expose deployment rest-template-deployment --type=LoadBalancer --name=rest-template-service  --port=80 --target-port=8080` : Expose the servive to the public
* `kubectl set image deployments/rest-template-deployment resttemplate=leopppp/java-rest-template:1.0` : Update Kubernetes Docker container image
* `gcloud container clusters get-credentials standard-cluster-1 --zone australia-southeast1-a --project leoproject-239411 && kubectl port-forward $(kubectl get pod --selector="app=resttemplate" --output jsonpath='{.items[0].metadata.name}') 8080:8080` : Port fording to local computer

## Structure
* `/src` : Java source code
* `.travis.yml` : Travis CI configuration file
* `Dockerfile` : Docker build file
* `pom.xml` : Maven configuration file
* `deploy.yml` : Kubernetes deployment specification file
* `deploy.sh` : Shell commands to build docker image and expose Kubernetes service 

## CI Pipeline
Three stages:
* `Test` : Compile and unit test
* `Build` : Build Docker image
* `Publish`: Publish Docker image
![CI screenshot](https://github.com/leopppp/java-rest-template/blob/master/CI-Pipeline.png)

## Toolchain
* TravisCI
* GitHub
* Java
* Maven
* Spring
* Spring boot
* Google Cloud
* Kubernetes
* Docker
* Docker Hub



#!/usr/bin/env bash

# Build API from source code (Prerequisite: Have JDK 1.8 and maven installed)

mvn clean install # Creates jar in target->resttemplate-1.0.jar
# To run the jar on local, you can run this command -> java -jar target/resttemplate-1.0.jar

# Build container from Docker file (Prerequisite: Docker installed)
docker build -t java-rest-template:1.0 .
# Pushing of docker image to registry is not included to avoid sharing credentials
docker tag java-rest-template:1.0 leopppp/java-rest-template:1.0
docker push leopppp/java-rest-template:1.0

# Deploy image to Kubernetes
#(Prerequisites:
# 1. docker image should be present in a registry from where Kubernetes should be able to download it)
# 2. kubectl should be set up on local
kubectl create -f deploy.yml --save-config
kubectl expose deployment rest-template-deployment --type=LoadBalancer --name=rest-template-service  --port=80 --target-port=8080

# Then you can access the REST API like the following:
# http://<load balancer ip>
# http://<load balancer ip>/status

# Port forwarding to your local computer



#!/usr/bin/env bash

# Build API from source code (Prerequisite: Have JDK 1.8 and maven installed)

#Change current directory to the root directoy of java-rest-template repository
mvn clean install 
# Creates jar in target->resttemplate-1.0.jar
# To run the jar on local, you can run this command -> java -jar target/resttemplate-1.0.jar

# Build container from Docker file (Prerequisite: Docker installed)
docker build -t java-rest-template:1.0 --build-arg LAST_COMMIT_SHA=$(git rev-parse HEAD) .
# Pushing of docker image to registry is not included to avoid sharing credentials
docker tag java-rest-template:1.0 leopppp/java-rest-template:1.0
docker push leopppp/java-rest-template:1.0

# Deploy image to Kubernetes
#(Prerequisites:
# 1. docker image should be present in a registry from where Kubernetes should be able to download it)
# 2. Google cloud SDK and kubectl is setup locally
#kubectl create -f deploy.yml --save-config
#kubectl expose deployment rest-template-deployment --type=LoadBalancer --name=rest-template-service  --port=80 --target-port=8080

#Update Kubernetes container image
kubectl set image deployments/rest-template-deployment resttemplate=leopppp/java-rest-template:1.0

# Then you can access the REST API like the following:
# http://<load balancer ip>
# http://<load balancer ip>/status

# Port forwarding to your local computer
#gcloud container clusters get-credentials standard-cluster-1 --zone australia-southeast1-a --project leoproject-239411 \
 #&& kubectl port-forward $(kubectl get pod --selector="app=resttemplate" --output jsonpath='{.items[0].metadata.name}') 8080:8080



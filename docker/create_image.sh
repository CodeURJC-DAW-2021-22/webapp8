#!/bin/bash

docker build -f Dockerfile -t $1 ../backend/webandtech
docker push $1
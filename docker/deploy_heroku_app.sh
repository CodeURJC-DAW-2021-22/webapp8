#!/bin/bash

mvn spring-boot:build-image -f ../backend/webandtech -Dspring-boot.build-image.imageName=registry.heroku.com/$1/web

heroku container:login

docker push registry.heroku.com/$1/web

heroku container:release web -a $1

heroku logs --tail -a $1
#!/bin/bash

mvn install

docker stop video-sharing
docker container rm video-sharing
docker build -t video-sharing .
docker run -p 8080:8080 -dit --name videoeeee-sharing video-sharing:latest
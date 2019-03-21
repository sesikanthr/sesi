#!/usr/bin/env bash

set -e

echo "removing exiting docker container"
sudo docker stop cdr-dashboard || true && docker rm -f cdr-dashboard || true
echo "removing exiting docker image"
sudo docker images | grep "cdr-dashboard" | awk '{print $1":"$2}' | xargs docker rmi
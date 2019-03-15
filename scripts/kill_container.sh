#!/usr/bin/env bash

set -e

echo "removing exiting docker image"
sudo docker stop nms-dashboard && docker rm -f nms-dashboard && docker rmi 860360332628.dkr.ecr.us-west-2.amazonaws.com/nms-dashboard
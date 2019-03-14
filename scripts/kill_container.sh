#!/usr/bin/env bash

set -e

echo "removing exiting docker image"
docker stop nms-dashboard || true && docker rm -f nms-dashboard || true && docker rmi 860360332628.dkr.ecr.us-west-2.amazonaws.com/nms-dashboard || true
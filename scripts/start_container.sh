#!/usr/bin/env bash

set -e

docker run -d --name nms-dashboard -p 8443:8443 860360332628.dkr.ecr.us-west-2.amazonaws.com/nms-dashboard
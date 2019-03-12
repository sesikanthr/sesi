#!/usr/bin/env bash

set -e

$(aws ecr get-login --no-include-email --region us-west-2)
sudo docker run -d --name nms-dashboard -p 8443:8443 860360332628.dkr.ecr.us-west-2.amazonaws.com/nms-dashboard
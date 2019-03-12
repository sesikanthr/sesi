#!/usr/bin/env bash

set -e
echo "Logging in to Amazon ECR..."
pwd
$(aws ecr get-login --no-include-email --region us-west-2)
echo "removing exiting docker image"
docker stop nms-dashboard || true && docker rm -f nms-dashboard || true
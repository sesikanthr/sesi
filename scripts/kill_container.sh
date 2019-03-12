#!/usr/bin/env bash

set -e

$(aws ecr get-login --no-include-email --region us-west-2)
docker rm -f nms-dashboard || true
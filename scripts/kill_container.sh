#!/usr/bin/env bash

set -e

echo "removing exiting docker image"
docker stop nms-dashboard || true && docker rm -f nms-dashboard || true
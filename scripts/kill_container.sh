#!/usr/bin/env bash

set -e

echo "removing exiting docker image"
sudo docker stop cdr-dashboard || true && docker rm -f cdr-dashboard || true
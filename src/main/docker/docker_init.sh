#!/usr/bin/env bash

check=$( sudo docker ps -a -f "name=git-postgresql" | tail -n +2 )
if [ -z "$check" ]; then
  docker build -t postgres_coddlers .
  docker run --name git-postgresql -d -p 5432:5432 postgres_coddlers
fi

docker start git-postgresql

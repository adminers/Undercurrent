#!/bin/bash
docker-compose down && docker-compose up -d git-simple && docker logs -f git-simple

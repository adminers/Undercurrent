#!/bin/bash
docker-compose down && docker-compose up -d hello-simple && docker stats

#!/bin/bash
mvn clean package docker:push

mvn clean package -DpushImage

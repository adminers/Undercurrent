#!/usr/bin/env bash
echo "Creating mongo users..."

mongo admin --host localhost -u root -p 123456 --eval "db.createUser({user:'admin',pwd:'123456',roles:[{role:'userAdminAnyDatabase',db:'admin'},{role:'readWrite',db:'test'}]});"

mongo admin -u root -p 123456 << EOF
use test
db.createCollection("logs", { autoIndexId : true })
EOF
echo "Mongo users created."


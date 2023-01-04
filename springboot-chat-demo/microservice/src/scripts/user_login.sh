#!/bin/bash



curl -X POST http://localhost:8080/login \
   -H 'Content-Type: application/json' \
   -d "{\"username\":\"douglas_$1\",\"password\":\"password\"}"

printf "\n\n"

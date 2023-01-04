#!/bin/bash


MYTIME=$(( $RANDOM % 100 ))
echo $MYTIME
JSON="{\"username\":\"douglas_${MYTIME}\",\"password\":\"password\"}"
echo $JSON
curl -X POST http://localhost:8080/user  -H "Content-Type: application/json"  -d "$JSON"


printf "\n\n"

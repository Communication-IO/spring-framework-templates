#!/bin/bash

JSON="{\"uuid\":\"$1\", \"token\":\"$2\"}"
echo "${JSON}" 
curl  -H 'Content-Type: application/json' \
   -d "${JSON}" \
   -X POST http://127.0.0.1:8080/logout

printf "\n\n"

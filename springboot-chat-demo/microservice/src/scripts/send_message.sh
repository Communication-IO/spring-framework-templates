#!/bin/bash



curl -X POST http://localhost:8080/messages \
   -H 'Content-Type: application/json' \
   -d '{"recipient":"fb9aa6f9-4571-4c1d-a5aa-5a5a9d06f7a6","start":1,content:{"type":"plain/text","text":"Hello World"}}'

printf "\n\n"

#!/bin/bash
#
# args $1 recipient_uuid 
#      $2 sender_uuid token sender_token
#      $3 token 
# multipart/form-data

RECIPIENT=$1
SENDER=$2
TOKEN=$3

# 0ca51e75-0cda-4ddd-81fc-c3fe0705fb8a 0fa37828-9e7a-46ca-b161-68167bc00081 MzFNMzFZTUJiVHgyek1jd2szMTIzTndpRE1nbjhZelpNSmdhWGNYaGRHWlROVGNNazJObGJNRWdqVEZIaDA5

# JSON='{"recipient":"${RECIPIENT}","sender":"${SENDER}","content":{"type":"image/png","text":"tweet_bird.png"}}'

# MESSAGE="message='{\"recipient\":\"0ca51e75-0cda-4ddd-81fc-c3fe0705fb8a\",\"sender\":\"0fa37828-9e7a-46ca-b161-68167bc00081\",\"content\":{\"type\":\"image/png\",\"text\":\"tweet_bird.png\"}}'"
# echo ${MESSAGE}


curl --verbose -X POST \
  http://127.0.0.1:8080/media \
  -H "Content-Type: multipart/form-data" \
  -H "token: ${TOKEN}" \
  --form message=message='{\"recipient\":\"0ca51e75-0cda-4ddd-81fc-c3fe0705fb8a\",\"sender\":\"0fa37828-9e7a-46ca-b161-68167bc00081\",\"content\":{\"type\":\"image/png\",\"text\":\"tweet_bird.png\"}}';type=application/json \
  --form file=tweet_bird.png;type=image/png

printf "\n\n"

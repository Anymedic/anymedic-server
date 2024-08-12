#!/bin/bash

IS_GREEN=$(docker ps | grep green) # 현재 실행중인 App이 blue인지 확인합니다.
DEFAULT_CONF=" /etc/nginx/nginx.conf"

BLUE_PORT=8081
GREEN_PORT=8082

if [ -z $IS_GREEN  ];then # blue인 경우

  echo "### BLUE => GREEN ###"

  echo "1. get green image"
  docker compose pull green # green으로 이미지를 내려받습니다.

  echo "2. green container up"
  docker compose up -d green # green 컨테이너 실행

  while [ 1 = 1 ]; do
  echo "3. green health check..."
  sleep 3

  REQUEST=$(curl http://127.0.0.1:$GREEN_PORT) # green으로 request
      if [ -n "$REQUEST" ]; then # 서비스 가능하면 health check 중지
              echo "health check success"
              break ;
              fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.green.conf /etc/nginx/nginx.conf
  sudo nginx -s reload

  echo "5. blue container down"
  docker compose stop blue
else
  echo "### GREEN => BLUE ###"

  echo "1. get blue image"
  docker compose pull blue

  echo "2. blue container up"
  docker compose up -d blue

  while [ 1 = 1 ]; do
    echo "3. blue health check..."
    sleep 3
    REQUEST=$(curl http://127.0.0.1:$BLUE_PORT) # blue로 request

    if [ -n "$REQUEST" ]; then # 서비스 가능하면 health check 중지
      echo "health check success"
      break ;
    fi
  done;

  echo "4. reload nginx"
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf
  sudo nginx -s reload

  echo "5. green container down"
  docker compose stop green
fi

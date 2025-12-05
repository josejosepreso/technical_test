#/bin/sh

curl localhost:8081/orders/$1/summary | jq

#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	localhost:8080/products/pricing \
	-d '{
		"productsIds": [1,2,3,4]
	}' \
	| jq

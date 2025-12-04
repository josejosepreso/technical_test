#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	localhost:8080/orders/create \
	-d '{
		"id": 1,
		"clientId": 1,
		"orderDetails": [
			{
				"productId": 1,
				"quantity": 4
			}
		]
	}' \
	| jq

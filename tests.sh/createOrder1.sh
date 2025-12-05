#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	localhost:8081/orders/create \
	-d '{
		"id": 3,
		"clientId": 1,
		"orderDetails": [
			{
				"productId": 1,
				"quantity": 4
			},
			{
				"productId": 10,
				"quantity": 100
			}
		]
	}' \
	| jq

#!/bin/sh

curl -X POST \
	-H "Content-Type: application/json" \
	localhost:8082/payments/create \
	-d '{
		"paymentId": 1,
		"orderId": 3,
		"paymentMethodDescription": "CASH"
	}' \
	| jq

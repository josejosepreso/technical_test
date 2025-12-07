# Shopping cart API

## Products

#### GET getAllProductsPricing

GET /pricing

> Response Examples

> 200 Response

```json
[
  {
    "productId": 1,
    "unitPrice": 109.95
  },
  {
    "productId": 2,
    "unitPrice": 22.3
  },
  {
    "productId": 3,
    "unitPrice": 55.99
  },
  {
    "productId": 4,
    "unitPrice": 15.99
  },
  {
    "productId": 5,
    "unitPrice": 695
  },
  {
    "productId": 6,
    "unitPrice": 168
  },
  {
    "productId": 7,
    "unitPrice": 9.99
  },
  {
    "productId": 8,
    "unitPrice": 10.99
  },
  {
    "productId": 9,
    "unitPrice": 64
  },
  {
    "productId": 10,
    "unitPrice": 109
  },
  {
    "productId": 11,
    "unitPrice": 109
  },
  {
    "productId": 12,
    "unitPrice": 114
  },
  {
    "productId": 13,
    "unitPrice": 599
  },
  {
    "productId": 14,
    "unitPrice": 999.99
  },
  {
    "productId": 15,
    "unitPrice": 56.99
  },
  {
    "productId": 16,
    "unitPrice": 29.95
  },
  {
    "productId": 17,
    "unitPrice": 39.99
  },
  {
    "productId": 18,
    "unitPrice": 9.85
  },
  {
    "productId": 19,
    "unitPrice": 7.95
  },
  {
    "productId": 20,
    "unitPrice": 12.99
  }
]
```

#### POST getProductsPricing

POST /pricing

> Body Parameters

```json
{
  "productsIds": [
    1,
    2,
    3,
    4
  ]
}
```


> Response Examples

> 200 Response

```json
[
  {
    "productId": 1,
    "unitPrice": 109.95
  },
  {
    "productId": 2,
    "unitPrice": 22.3
  },
  {
    "productId": 3,
    "unitPrice": 55.99
  },
  {
    "productId": 4,
    "unitPrice": 15.99
  }
]
```

## auth

#### POST authAdmin

POST /auth

> Body Parameters

```json
{
  "username": "admin",
  "password": "def456"
}
```

> Response Examples

> 200 Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpYXQiOjE3NjUxMjQxNTMsImV4cCI6MTc2NTEyNDQ1M30.0Zh9M6S52PxB00aJ2dlQjrF43LZDpnpGbEM3FHcdXM8"
}
```



#### POST validateAuth

POST /validateAuth

> Body Parameters

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlIiwicm9sZSI6IkNMSUVOVCIsImlhdCI6MTc2NTAwMDQ2OCwiZXhwIjoxNzY1MDAwNTI4fQ.qHa3d1f89kE2wzlUpQYvx0UYOIUnBBXX4j4aLp0D_Fa",
  "url": "http://localhost:8081/api/orders/all"
}
```


> Response Examples

> 200 Response

```json
{
  "valid": false
}
```

## orders

#### GET getOrderById

GET /10

> Body Parameters

```json
{
  "id": 1,
  "clientId": 1,
  "orderDetails": [
    {
      "productId": 1,
      "quantity": 4
    }
  ]
}
```



> Response Examples

> 200 Response

```json
{
  "client": {
    "clientId": 1,
    "fullName": "Jose Bautista"
  },
  "date": "2025-12-07T16:19:59.209Z",
  "id": 10,
  "orderDetails": [
    {
      "productId": 3,
      "quantity": 666,
      "unitPrice": 55.99
    }
  ],
  "orderStatusDescription": "PENDING"
}
```

#### GET getOrderByIdSummary

GET /2/summary


> Response Examples

> 200 Response

```json
{
  "clientId": 1,
  "orderDetails": [
    {
      "productId": 1,
      "quantity": 6,
      "unitPrice": 109.95
    }
  ],
  "orderId": 2,
  "status": "PENDING"
}
```

#### POST confirmOrder

POST /confirm

> Body Parameters

```json
{
  "orderId": 2
}
```

> Response Examples

> 200 Response

```json
{
  "client": {
    "clientId": 1,
    "fullName": "Jose Bautista"
  },
  "date": "2025-12-07T23:12:33.876Z",
  "id": 2,
  "orderDetails": [
    {
      "productId": 1,
      "quantity": 6,
      "unitPrice": 109.95
    }
  ],
  "orderStatusDescription": "CONFIRMED"
}
```

> 400 Response

```json
{
  "msg": "Order not in \"PENDING\" status.",
  "ok": false
}
```


#### POST cancelOrder

POST /cancel

> Body Parameters

```json
{
  "orderId": 13
}
```


> Response Examples

> 200 Response

```json
{
  "client": {
    "clientId": 1,
    "fullName": "Jose Bautista"
  },
  "date": "2025-12-07T23:12:49.768Z",
  "id": 13,
  "orderDetails": [
    {
      "productId": 1,
      "quantity": 4,
      "unitPrice": 109.95
    },
    {
      "productId": 15,
      "quantity": 10,
      "unitPrice": 56.99
    },
    {
      "productId": 4,
      "quantity": 12,
      "unitPrice": 15.99
    },
    {
      "productId": 15,
      "quantity": 16,
      "unitPrice": 56.99
    }
  ],
  "orderStatusDescription": "CANCELLED"
}
```

> 400 Response

```json
{
  "msg": "Order not in \"PENDING\" status.",
  "ok": false
}
```


## payments

#### GET getAllPayments

GET /all

> Response Examples

> 200 Response

```json
[
  {
    "clientId": 1,
    "date": "2025-12-07T23:29:38.468Z",
    "id": 2,
    "orderId": 20,
    "paymentStatus": "PENDING",
    "subtotal": 2113.42,
    "tax": 317.013,
    "total": 2430.433
  },
  {
    "cardNumber": "111222333",
    "clientId": 1,
    "date": "2025-12-07T23:31:42.637Z",
    "id": 3,
    "orderId": 40,
    "paymentStatus": "PAID",
    "subtotal": 659.7,
    "tax": 98.955,
    "total": 758.6550000000001
  },
  {
    "clientId": 1,
    "date": "2025-12-07T23:31:56.700Z",
    "id": 5,
    "orderId": 5,
    "paymentStatus": "PENDING",
    "subtotal": 218,
    "tax": 32.699999999999996,
    "total": 250.7
  }
]
```



#### POST cashPayment

POST /create

> Body Parameters

```json
{
  "paymentId": 5,
  "orderId": 5,
  "paymentMethodDescription": "CASH",
  "cardNumber": ""
}
```


> Response Examples

> 200 Response

```json
{
  "clientId": 1,
  "date": "2025-12-07T23:17:46.957Z",
  "id": 2,
  "orderId": 10,
  "paymentStatus": "PENDING",
  "subtotal": 659.7,
  "tax": 98.955,
  "total": 758.6550000000001
}
```


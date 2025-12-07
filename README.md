---
title: shopping_cart_responses
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.30"

---

# shopping_cart_responses

Base URLs:

# Authentication

# products

## GET getAllProductsPricing

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

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» productId|integer|true|none||none|
|» unitPrice|number|true|none||none|

## POST getProductsPricing

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

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|body|body|object| no |none|
|» productsIds|body|[integer]| yes |none|

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

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» productId|integer|true|none||none|
|» unitPrice|number|true|none||none|

# orders

## GET getOrderById

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

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|Authorization|header|string| yes |none|
|body|body|object| no |none|
|» id|body|integer| yes |none|
|» clientId|body|integer| yes |none|
|» orderDetails|body|[object]| yes |none|
|»» productId|body|integer| no |none|
|»» quantity|body|integer| no |none|

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

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» client|object|true|none||none|
|»» clientId|integer|true|none||none|
|»» fullName|string|true|none||none|
|» date|string|true|none||none|
|» id|integer|true|none||none|
|» orderDetails|[object]|true|none||none|
|»» productId|integer|false|none||none|
|»» quantity|integer|false|none||none|
|»» unitPrice|number|false|none||none|
|» orderStatusDescription|string|true|none||none|

## GET getOrderByIdSummary

GET /3/summary

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| yes |none|

> Response Examples

> 200 Response

```json
{
  "clientId": 1,
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
  "orderId": 3,
  "status": "CONFIRMED"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» clientId|integer|true|none||none|
|» orderDetails|[object]|true|none||none|
|»» productId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» unitPrice|number|true|none||none|
|» orderId|integer|true|none||none|
|» status|string|true|none||none|

## POST confirmOrder

POST /confirm

> Body Parameters

```json
{
  "orderId": 3
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|Authorization|header|string| yes |none|
|body|body|object| no |none|
|» orderId|body|integer| yes |none|

> Response Examples

> 200 Response

```json
{
  "client": {
    "clientId": 1,
    "fullName": "Jose Bautista"
  },
  "date": "2025-12-07T16:06:20.471Z",
  "id": 3,
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
  "orderStatusDescription": "CONFIRMED"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» client|object|true|none||none|
|»» clientId|integer|true|none||none|
|»» fullName|string|true|none||none|
|» date|string|true|none||none|
|» id|integer|true|none||none|
|» orderDetails|[object]|true|none||none|
|»» productId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» unitPrice|number|true|none||none|
|» orderStatusDescription|string|true|none||none|

# payments

## GET getAllPayments

GET /all

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| yes |none|

> Response Examples

> 200 Response

```json
[
  {
    "clientId": 1,
    "date": "2025-12-07T16:08:54.451Z",
    "id": 3,
    "orderDetails": [
      {
        "productId": 3,
        "quantity": 666,
        "unitPrice": 55.99
      }
    ],
    "orderId": 2,
    "paymentMethodDescription": "CASH",
    "paymentStatusDescription": "PENDING",
    "subtotal": 37289.340000000004,
    "tax": 5593.401000000001,
    "total": 42882.741
  },
  {
    "clientId": 1,
    "date": "2025-12-07T16:22:21.646Z",
    "id": 54,
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
    "orderId": 13,
    "paymentMethodDescription": "CARD",
    "paymentStatusDescription": "PAID",
    "subtotal": 2113.42,
    "tax": 317.013,
    "total": 2430.433
  }
]
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» clientId|integer|true|none||none|
|» date|string|true|none||none|
|» id|integer|true|none||none|
|» orderDetails|[object]|true|none||none|
|»» productId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» unitPrice|number|true|none||none|
|» orderId|integer|true|none||none|
|» paymentMethodDescription|string|true|none||none|
|» paymentStatusDescription|string|true|none||none|
|» subtotal|number|true|none||none|
|» tax|number|true|none||none|
|» total|number|true|none||none|

## POST createPayment

POST /create

> Body Parameters

```json
{
  "paymentId": 54,
  "orderId": 13,
  "paymentMethodDescription": "CARD"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|Authorization|header|string| yes |none|
|body|body|object| no |none|
|» paymentId|body|integer| yes |none|
|» orderId|body|integer| yes |none|
|» paymentMethodDescription|body|string| yes |none|

> Response Examples

> 200 Response

```json
{
  "clientId": 1,
  "date": "2025-12-07T16:22:21.646Z",
  "id": 54,
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
  "orderId": 13,
  "paymentMethodDescription": "CARD",
  "paymentStatusDescription": "PAID",
  "subtotal": 2113.42,
  "tax": 317.013,
  "total": 2430.433
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» clientId|integer|true|none||none|
|» date|string|true|none||none|
|» id|integer|true|none||none|
|» orderDetails|[object]|true|none||none|
|»» productId|integer|true|none||none|
|»» quantity|integer|true|none||none|
|»» unitPrice|number|true|none||none|
|» orderId|integer|true|none||none|
|» paymentMethodDescription|string|true|none||none|
|» paymentStatusDescription|string|true|none||none|
|» subtotal|number|true|none||none|
|» tax|number|true|none||none|
|» total|number|true|none||none|

# auth

## POST authAdmin

POST /auth

> Body Parameters

```json
{
  "username": "admin",
  "password": "def456"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|body|body|object| no |none|
|» username|body|string| yes |none|
|» password|body|string| yes |none|

> Response Examples

> 200 Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpYXQiOjE3NjUxMjQxNTMsImV4cCI6MTc2NTEyNDQ1M30.0Zh9M6S52PxB00aJ2dlQjrF43LZDpnpGbEM3FHcdXM8"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» token|string|true|none||none|

## GET health

GET /health

> Response Examples

> 200 Response

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

## POST validateToken

POST /validateAuth

> Body Parameters

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlIiwicm9sZSI6IkNMSUVOVCIsImlhdCI6MTc2NTAwMDQ2OCwiZXhwIjoxNzY1MDAwNTI4fQ.qHa3d1f89kE2wzlUpQYvx0UYOIUnBBXX4j4aLp0D_Fa",
  "url": "http://localhost:8081/api/orders/all"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Content-Type|header|string| yes |none|
|body|body|object| no |none|
|» token|body|string| yes |none|
|» url|body|string| yes |none|

> Response Examples

> 200 Response

```json
{
  "valid": false
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» valid|boolean|true|none||none|

# Data Schema


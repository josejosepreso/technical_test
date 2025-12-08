# Shopping Cart API

## Microservices

* **Products Service** – Manage product catalog and pricing
* **Orders Service** – Manage orders lifecycle
* **Payments Service** – Handle payments for orders
* **Auth Service** – User authentication and token validation

---

## Endpoints

### Products Service

| Method | Endpoint           | Description                           | Authorization |
| ------ | ------------------ | ------------------------------------- | ------------- |
| GET    | `/all`             | Get all products                      | Public        |
| GET    | `/?category={cat}` | Get products by category              | Public        |
| GET    | `/pricing`         | Get pricing for all products          | Public        |
| POST   | `/pricing`         | Get pricing for a list of product IDs | Public        |
| GET    | `/{id}`            | Get product details by ID             | Public        |

---

### Auth Service

| Method | Endpoint        | Description                        | Authorization      |
| ------ | --------------- | ---------------------------------- | ------------------ |
| POST   | `/auth`         | Authenticate user with credentials | Public             |
| POST   | `/validateAuth` | Validate token for a given URL     | Authenticated user |

---

### Orders Service

| Method | Endpoint        | Description         | Authorization             |
| ------ | --------------- | ------------------- | ------------------------- |
| POST   | `/create`       | Create a new order  | Authenticated user        |
| GET    | `/all`          | Get all orders      | Admin                     |
| GET    | `/{id}`         | Get order by ID     | Authenticated user        |
| GET    | `/{id}/summary` | Get order summary   | Authenticated user        |
| POST   | `/confirm`      | Confirm order by ID | Authenticated user, Admin |
| POST   | `/cancel`       | Cancel order by ID  | Authenticated user, Admin |

---

### Payments Service

| Method | Endpoint              | Description                | Authorization      |
| ------ | --------------------- | -------------------------- | ------------------ |
| POST   | `/create`             | Create a payment           | Authenticated user |
| POST   | `/cancel`             | Cancel payment by ID       | Admin              |
| POST   | `/confirmCashPayment` | Confirm cash payment by ID | Admin              |
| GET    | `/all`                | Get all payments           | Admin              |

---

## Authorizations Summary

* **Public endpoints**: Products listing, product details, pricing, and user authentication
* **Authenticated user endpoints**: Creating orders, viewing personal orders, validating token
* **Admin endpoints**: Viewing all orders/payments, confirming/canceling orders and payments

---

# Offchain Gpi API Specification

---

## Public endpoints

### POST /gpi/confirm
- Mô tả: Xác nhận một thanh toán
- Auth: không
- Request JSON:

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "data": {
    "uetr": "...",
    "confirmation_type": "...",
    "timestamp": "..."
  }
}
```

- Response 200 (object `v1ConfirmPaymentResponse`):

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "result": {
    "code": "...",
    "message": "..."
  },
  "data": {
    "id": "...",
    "uetr": "...",
    "amount": 1000.0,
    "currency": "...",
    "debtor_agent": "...",
    "creditor_agent": "...",
    "end_to_end_id": "...",
    "status": "...",
    "created_at": "2025-...",
    "debtor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "creditor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "transaction_reference": "..."
  }
}
```

- Response default: An unexpected error response.

```json
{
  "code": 1000,
  "message": "...",
  "details": [
    {
      "@type": "..."
    }
  ]
}
```

### POST /gpi/create
- Mô tả: Tạo thanh toán mới
- Auth: không
- Request JSON:

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "data": {
    "id": "...",
    "uetr": "...",
    "amount": 1000.0,
    "currency": "...",
    "debtor_agent": "...",
    "creditor_agent": "...",
    "end_to_end_id": "...",
    "status": "...",
    "created_at": "2025-...",
    "debtor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "creditor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "transaction_reference": "..."
  }
}
```

- Response 200 (object `v1CreatePaymentResponse`):

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "result": {
    "code": "...",
    "message": "..."
  },
  "data": {
    "id": "...",
    "uetr": "...",
    "amount": 1000.0,
    "currency": "...",
    "debtor_agent": "...",
    "creditor_agent": "...",
    "end_to_end_id": "...",
    "status": "...",
    "created_at": "2025-...",
    "debtor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "creditor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "transaction_reference": "..."
  }
}
```

- Response default: An unexpected error response.

```json
{
  "code": 1000,
  "message": "...",
  "details": [
    {
      "@type": "..."
    }
  ]
}
```

### GET /gpi/id/{id}
- Mô tả: Lấy thông tin chi tiết của một thanh toán theo `id`
- Auth: không

- Response 200 (object `v1GetPaymentResponse`):

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "result": {
    "code": "...",
    "message": "..."
  },
  "data": {
    "id": "...",
    "uetr": "...",
    "amount": 1000.0,
    "currency": "...",
    "debtor_agent": "...",
    "creditor_agent": "...",
    "end_to_end_id": "...",
    "status": "...",
    "created_at": "2025-...",
    "debtor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "creditor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "transaction_reference": "..."
  }
}
```

- Response default: An unexpected error response.

```json
{
  "code": 1000,
  "message": "...",
  "details": [
    {
      "@type": "..."
    }
  ]
}
```

### GET /gpi/uetr/{uetr}
- Mô tả: Lấy trạng thái của một thanh toán theo `uetr`
- Auth: không

- Response 200 (object `v1GetPaymentResponse`):

```json
{
  "metadata": {
    "request_id": "...",
    "request_time": "...",
    "version": "..."
  },
  "signature": {
    "s_type": "...",
    "s": "...",
    "b": "..."
  },
  "result": {
    "code": "...",
    "message": "..."
  },
  "data": {
    "id": "...",
    "uetr": "...",
    "amount": 1000.0,
    "currency": "...",
    "debtor_agent": "...",
    "creditor_agent": "...",
    "end_to_end_id": "...",
    "status": "...",
    "created_at": "2025-...",
    "debtor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "creditor_agent_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "transaction_reference": "..."
  }
}
```

- Response default: An unexpected error response.

```json
{
  "code": 1000,
  "message": "...",
  "details": [
    {
      "@type": "..."
    }
  ]
}
```

---

## Notes chung / Behaviour
- Server sử dụng in-memory stores (maps) cho dữ liệu. Dữ liệu sẽ mất khi server dừng.
- Một vài sample data có thể được seed sẵn để test.

---

End of spec.

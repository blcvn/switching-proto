# Notifications Notification API Specification

---

## Public endpoints

### POST /notification/notify
- Mô tả: Gửi thông báo về trạng thái giao dịch
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
  "transaction": {
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

- Response 200 (object `v1NotificationResponse`):

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
  "transaction": {
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

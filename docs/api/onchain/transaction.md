# Onchain Transaction API Specification

Mô tả: Định nghĩa message và service liên quan đến giao dịch on-chain (GPI - Global Payments Innovation).
File này chỉ bổ sung các định nghĩa request/response cho các thao tác Credit/Debit/Transfer
và truy vấn thông tin thanh toán.
Lưu ý: Các kiểu Metadata, Signature, Transaction, Result được định nghĩa trong onchain/common.proto.

---

## Public endpoints

### POST /onchain/bank/info
- Mô tả: Gửi yêu cầu
- Auth: không
- Request JSON:

```json
{
  "bic": "...",
  "name": "...",
  "country": "...",
  "lei": "...",
  "updated_at": "...",
  "balance": "..."
}
```

- Response 200 (object `v1KycEntity`):

```json
{
  "bic": "...",
  "name": "...",
  "country": "...",
  "lei": "...",
  "updated_at": "...",
  "balance": "..."
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

### POST /onchain/bank/register
- Mô tả: Gửi yêu cầu
- Auth: không
- Request JSON:

```json
{
  "bic": "...",
  "name": "...",
  "country": "...",
  "lei": "...",
  "updated_at": "...",
  "balance": "..."
}
```

- Response 200 (object `v1KycEntity`):

```json
{
  "bic": "...",
  "name": "...",
  "country": "...",
  "lei": "...",
  "updated_at": "...",
  "balance": "..."
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

### POST /onchain/credit
- Mô tả: Tạo một giao dịch Credit (ghi có) mới.
Yêu cầu: TransactionRequest
Trả về: TransactionResponse (kết quả + giao dịch đã tạo)
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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
  }
}
```

- Response 200 (object `v1TransactionResponse`):

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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
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

### POST /onchain/debit
- Mô tả: Tạo một giao dịch Debit (ghi nợ) mới.
Yêu cầu: TransactionRequest
Trả về: TransactionResponse (kết quả + giao dịch đã tạo)
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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
  }
}
```

- Response 200 (object `v1TransactionResponse`):

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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
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

### POST /onchain/transfer
- Mô tả: Chuyển khoản (transfer) giữa hai tài khoản/điểm.
Yêu cầu: TransactionRequest
Trả về: TransactionResponse
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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
  }
}
```

- Response 200 (object `v1TransactionResponse`):

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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
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

### POST /onchain/txn/info
- Mô tả: Lấy thông tin chi tiết của một thanh toán theo ID.
Yêu cầu: GetTransactionRequest (chứa ID)
Trả về: GetTransactionResponse (chứa Transaction nếu tìm thấy)
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
  "id": "..."
}
```

- Response 200 (object `v1GetTransactionResponse`):

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
  "txn": {
    "instruction_id": "...",
    "debitor": "...",
    "creditor": "...",
    "amount": "...",
    "currency": "...",
    "status": "...",
    "created_at": "2025-...",
    "creditor_account": {
      "iban": "...",
      "account_number": "...",
      "account_name": "...",
      "currency": "...",
      "reference": "..."
    },
    "priority": "..."
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

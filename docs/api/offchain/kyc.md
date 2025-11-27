# Offchain Kyc API Specification

---

## Public endpoints

### POST /kyc/detail
- Mô tả: Lấy thông tin chi tiết của một thực thể KYC
- Auth: không
- Request JSON:

```json
{
  "bic": "..."
}
```

- Response 200 (object `v1KycEntity`):

```json
{
  "bic": "...",
  "name": "...",
  "country": "...",
  "lei": "...",
  "updated_at": "..."
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

### GET /kyc/list
- Mô tả: Liệt kê tất cả các thực thể KYC
- Auth: không

- Response 200 (object `v1ListEntitiesResponse`):

```json
{
  "entities": [
    {
      "bic": "...",
      "name": "...",
      "country": "...",
      "lei": "...",
      "updated_at": "..."
    }
  ]
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

### POST /kyc/register
- Mô tả: Đăng ký thực thể KYC mới
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
    "bic": "...",
    "name": "...",
    "country": "...",
    "lei": "...",
    "updated_at": "..."
  }
}
```

- Response 200 (object `v1RegisterResponse`):

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
    "bic": "...",
    "name": "...",
    "country": "...",
    "lei": "...",
    "updated_at": "..."
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

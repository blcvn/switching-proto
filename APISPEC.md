# SWIFT Mock API Specification

Tập hợp các API có trong `main.go` của dự án mock-swift-api.

Server: http://localhost:9000

Authentication: hầu hết các endpoint (ngoại trừ `/health` và `/auth/token`) yêu cầu header:

- Authorization: Bearer mock-access-token-12345

Token endpoint trả về token mẫu:

{
  "access_token": "mock-access-token-12345",
  "token_type": "Bearer",
  "expires_in": 3600
}

---

## Public endpoints

### GET /health
- Mô tả: Kiểm tra trạng thái server
- Auth: không
- Response 200:

```json
{ "status": "up", "time": "2025-..." }
```

### POST /auth/token
- Mô tả: Token endpoint (mock) — hỗ trợ form hoặc JSON body
- Auth: không
- Request (JSON or form):

```json
{ "grant_type": "client_credentials", "client_id": "...", "client_secret": "..." }
```

- Response 200 (JSON):

```json
{ "access_token": "mock-access-token-12345", "token_type": "Bearer", "expires_in": 3600 }
```

---

## Authorization
- Tất cả các route được đăng ký vào `api` subrouter đều sử dụng `authMiddleware`.
- Header bắt buộc: `Authorization: Bearer <token>`
- Trong mock, token đúng là `mock-access-token-12345`.

---

## GPI Endpoints

### POST /gpi/v1/payments
- Mô tả: Tạo payment mock
- Auth: Bearer
- Request JSON:

```json
{
  "uetr": "optional-uuid",
  "amount": 1000.0,
  "currency": "USD",
  "debtor_agent": "BKTRUS33",
  "creditor_agent": "BNPAFRPP",
  "debtor_agent_account": {
    "iban": "DE89370400440532013000",
    "account_number": "0532013000",
    "account_name": "Acme Ltd",
    "currency": "USD",
    "reference": "Invoice 123"
  },
  "creditor_agent_account": {
    "iban": "FR7630006000011234567890189",
    "account_number": "11234567890189",
    "account_name": "Recipient Corp",
    "currency": "USD",
    "reference": "Payment for invoice 123"
  },
  "end_to_end_id": "INV-2025-001"
}
```

- Response 200:

```json
{
  "id": "PAY-12345",
  "uetr": "generated-or-provided-uetr",
  "transaction_reference": "PAY-12345",
  "status": "ACSP",
  "processing_timestamp": "2025-..."
}
```

Notes: Payment được lưu vào in-memory map; nếu có subscriber SSE cho UETR sẽ nhận event khởi tạo.

### GET /gpi/v1/payments/{id}
- Mô tả: Lấy chi tiết payment theo `id`
- Auth: Bearer
- Response 200: object `Payment`:

```json
{
  "id": "PAY-001",
  "uetr": "...",
  "amount": 1000,
  "currency": "USD",
  "debtor_agent": "BKTRUS33",
  "debtor_agent_account": {
    "iban": "DE89370400440532013000",
    "account_number": "0532013000",
    "account_name": "Acme Ltd",
    "currency": "USD",
    "reference": "Invoice 123"
  },
  "creditor_agent": "BNPAFRPP",
  "creditor_agent_account": {
    "iban": "FR7630006000011234567890189",
    "account_number": "11234567890189",
    "account_name": "Recipient Corp",
    "currency": "USD",
    "reference": "Payment for invoice 123"
  },
  "end_to_end_id": "INV-2025-001",
  "status": "ACSP",
  "created_at": "2025-..."
}
```

Response 404 if not found.

### GET /gpi/v4/payments/{uetr}/status
- Mô tả: Lấy trạng thái payment theo UETR
- Auth: Bearer
- Response 200:

```json
{ "uetr": "...", "status": "ACSP", "amount": 1000, "currency": "USD" }
```

Response 404 nếu không tìm thấy UETR.

### GET /gpi/v4/payments/{uetr}/events (SSE)
- Mô tả: Server-Sent Events stream cho UETR
- Auth: Bearer
- Headers: `Accept: text/event-stream` (server trả `Content-Type: text/event-stream`)
- Events gửi:
  - `welcome` (khi subscribe)
  - `update` (các sự kiện publish từ server)
  - `ping` (heartbeat 30s)

Example: khi có payment mới hoặc confirmation, server gọi `publishEvent(uetr, message)` để gửi `update` event.

### POST /gpi/v4/payments/{uetr}/confirmations
- Mô tả: Gửi confirmation cho UETR
- Auth: Bearer
- Request JSON:

```json
{ "confirmation_type": "ACK", "timestamp": "2025-..." }
```

- Response 200:

```json
{ "uetr": "...", "status": "ACSC" }
```

Response 404 nếu UETR không tồn tại.

### POST /gpi/v4/events/subscribe
- Mô tả: Đăng ký sự kiện (mock)
- Auth: Bearer
- Request JSON:

```json
{ "callback_url": "https://example.com/cb" }
```

- Response 200:

```json
{ "subscribed": "https://example.com/cb" }
```

### DELETE /gpi/v4/events/unsubscribe
- Mô tả: Hủy đăng ký (mock)
- Auth: Bearer
- Response 200:

```json
{ "unsubscribed": "ok" }
```

---

## KYC Endpoints

### GET /kyc/v1/entities
- Mô tả: Danh sách entity
- Auth: Bearer
- Response 200: array of objects

```json
[{ "bic": "BKTRUS33", "name": "Mock Bank TR", "country": "US", "lei": "...", "updated_at": "..." }]
```

### GET /kyc/v1/entities/{bic}
- Mô tả: Lấy entity theo BIC
- Auth: Bearer
- Response 200: `KycEntity` object

### GET /kyc/v1/entities/{bic}/documents
- Mô tả: Tài liệu liên quan (mock)
- Auth: Bearer
- Response 200:

```json
[ { "id": "doc-1", "type": "certificate", "url": "https://<host>/documents/doc-1" } ]
```

### POST /kyc/v1/submissions
- Mô tả: Gửi cập nhật KYC (mock)
- Auth: Bearer
- Request JSON:

```json
{ "bic": "BKTRUS33", "updates": { "name": "New Name" } }
```

- Response 200:

```json
{ "status": "submitted" }
```

### GET /kyc/v1/metadata
- Mô tả: Trả metadata cho KYC
- Auth: Bearer
- Response 200:

```json
{ "version": "1.0", "fields": ["bic","name","country","lei","documents"] }
```

### GET /kyc/v1/audit/{bic}
- Mô tả: Lịch sử audit (mock)
- Auth: Bearer
- Response 200: array of audit entries

```json
[ { "bic": "BKTRUS33", "action": "created", "time": "..." }, { "bic": "BKTRUS33", "action": "updated", "time": "..." } ]
```

---

## FI Transfer Endpoints

### POST /fi/v4/transfer
- Mô tả: Tạo transfer
- Auth: Bearer
- Request JSON:

```json
{
  "instruction_id": "optional-id",
  "debtor": "BKTRUS33",
  "creditor": "BNPAFRPP",
  "amount": 15000,
  "currency": "EUR"
}
```

- Response 200:

```json
{ "instruction_id": "T-12345", "status": "PENDING" }
```

Note: `Transfer` objects in responses may include optional structured account details `debtor_account` and `creditor_account` with fields: `iban`, `account_number`, `account_name`, `currency`, `reference`.

### GET /fi/v4/transfer/{instruction_id}
- Mô tả: Lấy chi tiết transfer
- Auth: Bearer
- Response 200: `Transfer` object

### GET /fi/v4/transfer/{instruction_id}/status
- Mô tả: Lấy trạng thái transfer
- Auth: Bearer
- Response 200:

```json
{ "instruction_id": "ABC123", "status": "PENDING" }
```

### POST /fi/v4/transfer/confirmations
- Mô tả: Xác nhận transfer
- Auth: Bearer
- Request JSON:

```json
{ "instruction_id": "ABC123", "status": "COMPLETED", "timestamp": "..." }
```

- Response 200:

```json
{ "instruction_id": "ABC123", "status": "COMPLETED" }
```

Response 404 nếu instruction_id không tồn tại.

### POST /fi/v4/transfer/search
- Mô tả: Tìm transfer theo filter
- Auth: Bearer
- Request JSON:

```json
{ "filter": { "currency": "EUR", "date_from": "2025-01-01", "date_to": "2025-12-31" } }
```

- Response 200:

```json
{ "results": [ /* array of matching Transfer objects */ ] }
```

### POST /fi/v4/transfer/bulk
- Mô tả: Tạo batch transfer
- Auth: Bearer
- Request JSON:

```json
{
  "batch_id": "BATCH-1",
  "transfers": [ { "instruction_id": "", "amount": 1000, "currency": "EUR" } ]
}
```

- Response 200:

```json
{ "batch_id": "BATCH-1", "created": [ { "instruction_id": "T-123", "status": "PENDING" } ] }
```

---

## Notes chung / Behaviour
- Server sử dụng in-memory stores (maps) cho payments, transfers, kyc. Dữ liệu sẽ mất khi server dừng.
- Một vài sample data được seed sẵn qua `seedSampleData()` (ví dụ `PAY-001`, `ABC123`, BIC `BKTRUS33`).
- SSE clients được quản lý theo UETR; `publishEvent(uetr, message)` sẽ gửi `update` event tới các client đang subscribe.

---

## Ví dụ curl (token + call protected endpoint)

```bash
# Lấy token (mock)
curl -X POST http://localhost:9000/auth/token -d 'grant_type=client_credentials' -s

# Ví dụ tạo payment
curl -X POST http://localhost:9000/gpi/v1/payments \
  -H 'Authorization: Bearer mock-access-token-12345' \
  -H 'Content-Type: application/json' \
  -d '{"amount":1000,"currency":"USD"}'
```

---

End of spec.

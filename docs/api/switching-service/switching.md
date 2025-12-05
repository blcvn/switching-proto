# Switching-Service Switching API Specification

---

## Public endpoints

### POST /v1/switching/banks
- Mô tả: Register a new bank into the settlement data contract.
- Auth: không
- Request JSON:

```json
{
  "bank_code": "..."
}
```

- Response 200 (object `v1RegisterBankResponse`):

```json
{
  "bank_code": "...",
  "bank_address": "...",
  "created_at": "...",
  "status": 1000,
  "message": "..."
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

### POST /v1/switching/banks/deposit
- Mô tả: Deposit settlement tokens for a bank.
- Auth: không
- Request JSON:

```json
{
  "bank_code": "...",
  "bank_address": "...",
  "amount": "...",
  "note": "..."
}
```

- Response 200 (object `v1TransactionResponse`):

```json
{
  "tx_hash": "...",
  "message": "..."
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

### POST /v1/switching/banks/mint
- Mô tả: Mint settlement tokens directly to a bank by its bank code.
- Auth: không
- Request JSON:

```json
{
  "bank_code": "...",
  "bank_address": "...",
  "amount": "...",
  "note": "..."
}
```

- Response 200 (object `v1TransactionResponse`):

```json
{
  "tx_hash": "...",
  "message": "..."
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

### POST /v1/switching/banks/withdraw
- Mô tả: Withdraw settlement tokens for a bank.
- Auth: không
- Request JSON:

```json
{
  "bank_code": "...",
  "bank_address": "...",
  "amount": "...",
  "note": "..."
}
```

- Response 200 (object `v1TransactionResponse`):

```json
{
  "tx_hash": "...",
  "message": "..."
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

### GET /v1/switching/banks/{bank_address}/balance
- Mô tả: Query the balance in smart contract (settlement data contract balance) of a bank by address. theo `bank_address`
- Auth: không

- Response 200 (object `v1QueryBalanceResponse`):

```json
{
  "bank_address": "...",
  "balance": "...",
  "balance_type": "...",
  "bank_code": "...",
  "message": "..."
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

### GET /v1/switching/banks/{bank_address}/tokenBalance
- Mô tả: Query the ERC20 token balance of a bank by address. theo `bank_address`
- Auth: không

- Response 200 (object `v1QueryBalanceResponse`):

```json
{
  "bank_address": "...",
  "balance": "...",
  "balance_type": "...",
  "bank_code": "...",
  "message": "..."
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

### POST /v1/switching/payments/cancel
- Mô tả: Cancel a previously created hold payment (refund to sender) - for timeout/unhold.
- Auth: không
- Request JSON:

```json
{
  "uetr": "...",
  "sender_bank": "...",
  "bank_code": "...",
  "reason": "..."
}
```

- Response 200 (object `v1TransactionResponse`):

```json
{
  "tx_hash": "...",
  "message": "..."
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

### POST /v1/switching/payments/confirm
- Mô tả: Confirm a held payment and trigger settlement on-chain.
- Auth: không
- Request JSON:

```json
{
  "uetr": "...",
  "sender_bank": "...",
  "sender_bank_code": "...",
  "receiver_bank": "...",
  "receiver_bank_code": "...",
  "from_bank_account": "...",
  "to_bank_account": "...",
  "tx_reference": "...",
  "note": "..."
}
```

- Response 200 (object `v1PaymentResponse`):

```json
{
  "tx_hash": "...",
  "ref_id": "...",
  "status": "..."
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

### POST /v1/switching/payments/hold
- Mô tả: Create a single hold payment request.
- Auth: không
- Request JSON:

```json
{
  "uetr": "...",
  "sender_bank": "...",
  "sender_bank_code": "...",
  "receiver_bank": "...",
  "receiver_bank_code": "...",
  "beneficiary_account": "...",
  "amount": "...",
  "note": "..."
}
```

- Response 200 (object `v1PaymentResponse`):

```json
{
  "tx_hash": "...",
  "ref_id": "...",
  "status": "..."
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

### POST /v1/switching/payments/holdBatch
- Mô tả: Create a batch of hold payments in a single transaction.
- Auth: không
- Request JSON:

```json
{
  "sender_bank": "...",
  "sender_bank_code": "...",
  "items": [
    {
      "uetr": "...",
      "beneficiary_account": "...",
      "receiver_bank_code": "...",
      "receiver_bank_address": "...",
      "amount": "...",
      "note": "..."
    }
  ]
}
```

- Response 200 (object `v1BatchPaymentResponse`):

```json
{
  "results": [
    {
      "uetr": "...",
      "tx_hash": "...",
      "status": "...",
      "message": "..."
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

### POST /v1/switching/payments/unheld
- Mô tả: Unhold a held payment manually (only receiver bank or operator can cancel).
- Auth: không
- Request JSON:

```json
{
  "uetr": "...",
  "receiver_bank": "...",
  "receiver_bank_code": "...",
  "reason": "..."
}
```

- Response 200 (object `v1TransactionResponse`):

```json
{
  "tx_hash": "...",
  "message": "..."
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

### GET /v1/switching/payments/{uetr}
- Mô tả: Get payment status by UETR (offchain tracking reference).
Returns full on-chain hold payment information. theo `uetr`
- Auth: không

- Response 200 (object `v1PaymentInfo`):

```json
{
  "uetr": "...",
  "sender_bank": "...",
  "receiver_bank": "...",
  "receiver_bank_code": "...",
  "beneficiary_iban": "...",
  "amount": "...",
  "hold_timestamp": "...",
  "status": 1000,
  "expiry_time": "..."
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

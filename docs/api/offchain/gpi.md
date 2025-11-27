# Offchain Gpi API Specification

---

## Schemas

### `offchainv1Account`
Account represents the account details associated with an agent.

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `account_name` | string | `False` | Account holder name |
| `account_number` | string | `False` | Local account number |
| `currency` | string | `False` | Account currency (optional) |
| `iban` | string | `False` | International Bank Account Number (if available) |
| `reference` | string | `False` | Additional reference or identification (e.g., secondary id) |

### `offchainv1ConfirmMessage`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `confirmation_type` | string | `False` |  |
| `timestamp` | string | `False` |  |
| `uetr` | string | `False` |  |

### `offchainv1ConfirmPaymentResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

### `offchainv1CreatePaymentResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

### `offchainv1GetPaymentResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

### `offchainv1Metadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `request_id` | string | `False` | ID định danh duy nhất cho request |
| `request_time` | string (int64) | `False` | Thời gian gửi request (Unix timestamp) |
| `version` | string | `False` | Phiên bản của protocol |

### `offchainv1Payment`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `amount` | number (double) | `False` |  |
| `created_at` | string (date-time) | `False` |  |
| `creditor_agent` | string | `False` |  |
| `creditor_agent_account` | object [offchainv1Account] | `False` | Optional structured account details for the creditor (receiving) agent. |
| `currency` | string | `False` |  |
| `debtor_agent` | string | `False` |  |
| `debtor_agent_account` | object [offchainv1Account] | `False` | Optional structured account details for the debtor (sending) agent. |
| `end_to_end_id` | string | `False` |  |
| `id` | string | `False` |  |
| `status` | string | `False` |  |
| `transaction_reference` | string | `False` |  |
| `uetr` | string | `False` |  |

### `offchainv1Result`
CCResult định nghĩa cấu trúc kết quả trả về

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `code` | string | `False` | Mã kết quả thực thi |
| `message` | string | `False` | Thông điệp mô tả kết quả |

### `offchainv1Signature`
CCSignature định nghĩa cấu trúc chữ ký số cho request

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `b` | string (byte) | `False` | Chữ ký số |
| `s` | string | `False` | Chuỗi dùng tạo ra chữ ký |
| `s_type` | object [offchainv1SignatureSignatureType] | `False` | Loại chữ ký được sử dụng |

### `offchainv1SignatureSignatureType`
- NO_USE_TYPE: Giá trị mặc định, không sử dụng
 - J: Chữ ký loại J
 - C: Chữ ký loại C
 - S: Chữ ký loại S

---

End of spec.

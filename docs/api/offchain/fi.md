# Offchain Fi API Specification

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

### `offchainv1BulkTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |
| `transfers` | array | `False` |  |

### `offchainv1ConfirmTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `instruction_id` | string | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

### `offchainv1CreateTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Transfer] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

### `offchainv1Metadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `request_id` | string | `False` | ID định danh duy nhất cho request |
| `request_time` | string (int64) | `False` | Thời gian gửi request (Unix timestamp) |
| `version` | string | `False` | Phiên bản của protocol |

### `offchainv1Result`
CCResult định nghĩa cấu trúc kết quả trả về

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `code` | string | `False` | Mã kết quả thực thi |
| `message` | string | `False` | Thông điệp mô tả kết quả |

### `offchainv1SearchTransfersRequestFilter`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `currency` | string | `False` |  |
| `date_from` | string | `False` |  |
| `date_to` | string | `False` |  |

### `offchainv1SearchTransfersResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `results` | array | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

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

### `offchainv1Transfer`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `amount` | number (double) | `False` |  |
| `created_at` | string (date-time) | `False` |  |
| `creditor` | string | `False` |  |
| `creditor_account` | object [offchainv1Account] | `False` |  |
| `currency` | string | `False` |  |
| `debtor` | string | `False` |  |
| `debtor_account` | object [offchainv1Account] | `False` | Optional structured account details for the debtor/creditor in FI transfers |
| `instruction_id` | string | `False` |  |
| `status` | string | `False` |  |

### `offchainv1TransferStatus`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Transfer] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

---

End of spec.

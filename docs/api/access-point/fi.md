# Access-Point Fi API Specification

---

## Schemas

### `accesspointv1Account`
Account represents the account details associated with an agent.

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `account_name` | string | `False` | Account holder name |
| `account_number` | string | `False` | Local account number |
| `currency` | string | `False` | Account currency (optional) |
| `iban` | string | `False` | International Bank Account Number (if available) |
| `reference` | string | `False` | Additional reference or identification (e.g., secondary id) |

### `accesspointv1BulkTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [accesspointv1Metadata] | `False` |  |
| `result` | object [accesspointv1Result] | `False` |  |
| `signature` | object [accesspointv1Signature] | `False` |  |
| `transfers` | array | `False` |  |

### `accesspointv1ConfirmTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `instruction_id` | string | `False` |  |
| `metadata` | object [accesspointv1Metadata] | `False` |  |
| `result` | object [accesspointv1Result] | `False` |  |
| `signature` | object [accesspointv1Signature] | `False` |  |

### `accesspointv1CreateTransferResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [accesspointv1Transfer] | `False` |  |
| `metadata` | object [accesspointv1Metadata] | `False` |  |
| `result` | object [accesspointv1Result] | `False` |  |
| `signature` | object [accesspointv1Signature] | `False` |  |

### `accesspointv1Metadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `request_id` | string | `False` | ID định danh duy nhất cho request |
| `request_time` | string (int64) | `False` | Thời gian gửi request (Unix timestamp) |
| `version` | string | `False` | Phiên bản của protocol |

### `accesspointv1Result`
CCResult định nghĩa cấu trúc kết quả trả về

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `code` | string | `False` | Mã kết quả thực thi |
| `message` | string | `False` | Thông điệp mô tả kết quả |

### `accesspointv1SearchTransfersRequestFilter`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `currency` | string | `False` |  |
| `date_from` | string | `False` |  |
| `date_to` | string | `False` |  |

### `accesspointv1SearchTransfersResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [accesspointv1Metadata] | `False` |  |
| `result` | object [accesspointv1Result] | `False` |  |
| `results` | array | `False` |  |
| `signature` | object [accesspointv1Signature] | `False` |  |

### `accesspointv1Signature`
CCSignature định nghĩa cấu trúc chữ ký số cho request

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `b` | string (byte) | `False` | Chữ ký số |
| `s` | string | `False` | Chuỗi dùng tạo ra chữ ký |
| `s_type` | object [accesspointv1SignatureSignatureType] | `False` | Loại chữ ký được sử dụng |

### `accesspointv1SignatureSignatureType`
- NO_USE_TYPE: Giá trị mặc định, không sử dụng
 - J: Chữ ký loại J
 - C: Chữ ký loại C
 - S: Chữ ký loại S

### `accesspointv1Transfer`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `amount` | number (double) | `False` |  |
| `created_at` | string (date-time) | `False` |  |
| `creditor` | string | `False` |  |
| `creditor_account` | object [accesspointv1Account] | `False` | Optional structured account details for the debtor/creditor in FI transfers |
| `currency` | string | `False` |  |
| `instruction_id` | string | `False` |  |
| `status` | string | `False` |  |

### `accesspointv1TransferStatus`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [accesspointv1Transfer] | `False` |  |
| `metadata` | object [accesspointv1Metadata] | `False` |  |
| `result` | object [accesspointv1Result] | `False` |  |
| `signature` | object [accesspointv1Signature] | `False` |  |

---

End of spec.

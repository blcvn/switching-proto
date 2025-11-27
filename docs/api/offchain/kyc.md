# Offchain Kyc API Specification

---

## Schemas

### `offchainv1AuditEntry`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `action` | string | `False` |  |
| `bic` | string | `False` |  |
| `time` | string | `False` |  |

### `offchainv1AuditResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entries` | array | `False` |  |

### `offchainv1Document`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | string | `False` |  |
| `type` | string | `False` |  |
| `url` | string | `False` |  |

### `offchainv1GetEntityDocsResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `documents` | array | `False` |  |

### `offchainv1KycEntity`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `bic` | string | `False` |  |
| `country` | string | `False` |  |
| `lei` | string | `False` |  |
| `name` | string | `False` |  |
| `updated_at` | string | `False` |  |

### `offchainv1KycMetadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `fields` | array | `False` |  |
| `version` | string | `False` |  |

### `offchainv1ListEntitiesResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entities` | array | `False` |  |

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

### `offchainv1SubmitKycResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `status` | string | `False` |  |

### `v1RegisterResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1KycEntity] | `False` |  |
| `metadata` | object [offchainv1Metadata] | `False` |  |
| `result` | object [offchainv1Result] | `False` |  |
| `signature` | object [offchainv1Signature] | `False` |  |

---

End of spec.

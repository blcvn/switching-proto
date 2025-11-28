# Package Định Nghĩa Các Message Và Service Cho Kyc (Know Your Customer) Trong Access Point API Specification

---

## Schemas

### `accesspointv1AuditEntry`
Một entry trong lịch sử audit

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `action` | string | `False` | Hành động đã thực hiện (ví dụ: "create", "update", "verify") |
| `bic` | string | `False` | Bank Identifier Code của thực thể |
| `time` | string | `False` | Thời gian thực hiện hành động |

### `accesspointv1AuditResponse`
Response lịch sử audit

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entries` | array | `False` | Danh sách các entry trong lịch sử audit |

### `accesspointv1Document`
Thông tin tài liệu KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | string | `False` | ID của tài liệu |
| `type` | string | `False` | Loại tài liệu (ví dụ: "passport", "license") |
| `url` | string | `False` | URL để truy cập tài liệu |

### `accesspointv1GetEntityDocsResponse`
Response danh sách tài liệu của thực thể KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `documents` | array | `False` | Danh sách các tài liệu |

### `accesspointv1KycEntity`
Thông tin thực thể KYC (Know Your Customer)

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `bic` | string | `False` | Bank Identifier Code |
| `country` | string | `False` | Quốc gia |
| `lei` | string | `False` | Legal Entity Identifier |
| `name` | string | `False` | Tên thực thể |
| `updated_at` | string | `False` | Thời gian cập nhật lần cuối |

### `accesspointv1KycMetadata`
Metadata về các trường KYC được hỗ trợ

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `fields` | array | `False` | Danh sách các trường được hỗ trợ |
| `version` | string | `False` | Phiên bản của schema KYC |

### `accesspointv1ListEntitiesResponse`
Response danh sách các thực thể KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entities` | array | `False` | Danh sách các thực thể KYC |

### `accesspointv1SubmitKycResponse`
Response gửi thông tin KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `status` | string | `False` | Trạng thái xử lý (ví dụ: "pending", "approved", "rejected") |

---

End of spec.

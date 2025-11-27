# Offchain Kyc API Specification

---

## Schemas

### `offchainv1AuditEntry`
Một entry trong lịch sử audit

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `action` | string | `False` | Hành động đã thực hiện (ví dụ: "register", "update", "verify") |
| `bic` | string | `False` | Bank Identifier Code của thực thể |
| `time` | string | `False` | Thời gian thực hiện hành động |

### `offchainv1AuditResponse`
Phản hồi lịch sử audit

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entries` | array | `False` | Danh sách các entry trong lịch sử audit |

### `offchainv1Document`
Thông tin tài liệu

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | string | `False` | ID của tài liệu |
| `type` | string | `False` | Loại tài liệu |
| `url` | string | `False` | URL để truy cập tài liệu |

### `offchainv1GetEntityDocsResponse`
Phản hồi danh sách tài liệu

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `documents` | array | `False` | Danh sách các tài liệu |

### `offchainv1KycEntity`
Thông tin thực thể KYC (Know Your Customer)

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `bic` | string | `False` | Bank Identifier Code |
| `country` | string | `False` | Mã quốc gia |
| `lei` | string | `False` | Legal Entity Identifier |
| `name` | string | `False` | Tên tổ chức |
| `updated_at` | string | `False` | Thời gian cập nhật lần cuối |

### `offchainv1KycMetadata`
Metadata về cấu trúc KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `fields` | array | `False` | Danh sách các trường được hỗ trợ |
| `version` | string | `False` | Phiên bản của schema KYC |

### `offchainv1ListEntitiesResponse`
Phản hồi danh sách thực thể KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entities` | array | `False` | Danh sách các thực thể KYC |

### `offchainv1Metadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `request_id` | string | `False` | ID định danh duy nhất cho request |
| `request_time` | string (int64) | `False` | Thời gian gửi request (Unix timestamp) |
| `version` | string | `False` | Phiên bản của protocol |

### `offchainv1Result`
Result định nghĩa cấu trúc kết quả trả về

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `code` | object [offchainv1ResultCode] | `False` | Mã kết quả thực thi (enum) |
| `message` | string | `False` | Thông điệp mô tả kết quả |

### `offchainv1ResultCode`
- UNSPECIFIED: Giá trị mặc định, không xác định
 - SUCCESS: Thành công
 - BAD_REQUEST: Lỗi validation hoặc request không hợp lệ
 - UNAUTHORIZED: Chưa xác thực hoặc token không hợp lệ
 - FORBIDDEN: Không có quyền truy cập
 - NOT_FOUND: Không tìm thấy tài nguyên
 - DUPLICATE_REQUEST: Request trùng lặp
 - UNPROCESSABLE_ENTITY: Dữ liệu không thể xử lý
 - TOO_MANY_REQUESTS: Quá nhiều request trong khoảng thời gian
 - INTERNAL: Lỗi nội bộ của server
 - BAD_GATEWAY: Lỗi gateway
 - SERVICE_UNAVAILABLE: Dịch vụ không khả dụng
 - GATEWAY_TIMEOUT: Gateway timeout

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
Phản hồi gửi thông tin KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `status` | string | `False` | Trạng thái xử lý (ví dụ: "pending", "approved", "rejected") |

### `v1RegisterResponse`
Phản hồi đăng ký thực thể KYC

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1KycEntity] | `False` | Dữ liệu thực thể KYC đã đăng ký |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

---

End of spec.

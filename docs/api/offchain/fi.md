# Package Định Nghĩa Các Message Và Service Cho Financial Institution (Fi) Trong Offchain API Specification

---

## Schemas

### `offchainv1Account`
Thông tin chi tiết tài khoản liên kết với một agent

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `account_name` | string | `False` | Tên chủ tài khoản |
| `account_number` | string | `False` | Số tài khoản nội địa |
| `currency` | string | `False` | Mã tiền tệ của tài khoản (tùy chọn) |
| `iban` | string | `False` | Số tài khoản quốc tế IBAN (nếu có) |
| `reference` | string | `False` | Thông tin tham chiếu hoặc định danh bổ sung (ví dụ: ID phụ) |

### `offchainv1BulkTransferResponse`
Phản hồi chuyển tiền hàng loạt

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |
| `transfers` | array | `False` | Danh sách các lệnh chuyển tiền đã xử lý |

### `offchainv1ConfirmTransferResponse`
Phản hồi xác nhận lệnh chuyển tiền

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `instruction_id` | string | `False` | ID của lệnh chuyển tiền đã xác nhận |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1CreateTransferResponse`
Phản hồi tạo lệnh chuyển tiền

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Transfer] | `False` | Dữ liệu lệnh chuyển tiền đã tạo |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1Metadata`
Metadata chứa thông tin về request/response

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

### `offchainv1SearchTransfersRequestFilter`
Bộ lọc tìm kiếm

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `currency` | string | `False` | Lọc theo mã tiền tệ |
| `date_from` | string | `False` | Ngày bắt đầu (format: YYYY-MM-DD) |
| `date_to` | string | `False` | Ngày kết thúc (format: YYYY-MM-DD) |

### `offchainv1SearchTransfersResponse`
Phản hồi tìm kiếm lệnh chuyển tiền

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `results` | array | `False` | Danh sách các lệnh chuyển tiền tìm được |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1Signature`
Signature định nghĩa cấu trúc chữ ký số cho request/response

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `b` | string (byte) | `False` | Chữ ký số (dạng bytes) |
| `s` | string | `False` | Chuỗi dữ liệu dùng để tạo ra chữ ký |
| `s_type` | object [offchainv1SignatureSignatureType] | `False` | Loại chữ ký được sử dụng |

### `offchainv1SignatureSignatureType`
- NO_USE_TYPE: Giá trị mặc định, không sử dụng
 - J: Chữ ký loại J
 - C: Chữ ký loại C
 - S: Chữ ký loại S

### `offchainv1Transfer`
Thông tin chuyển tiền giữa các tổ chức tài chính

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `amount` | number (double) | `False` | Số tiền chuyển |
| `created_at` | string (date-time) | `False` | Thời gian tạo lệnh chuyển |
| `creditor` | string | `False` | BIC của ngân hàng người nhận |
| `creditor_account` | object [offchainv1Account] | `False` | Thông tin tài khoản chi tiết của người nhận (tùy chọn) |
| `currency` | string | `False` | Mã tiền tệ |
| `debtor` | string | `False` | BIC của ngân hàng người gửi |
| `debtor_account` | object [offchainv1Account] | `False` | Thông tin tài khoản chi tiết của người gửi (tùy chọn) |
| `instruction_id` | string | `False` | ID của lệnh chuyển tiền |
| `status` | string | `False` | Trạng thái chuyển tiền |

### `offchainv1TransferStatus`
Trạng thái lệnh chuyển tiền

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Transfer] | `False` | Dữ liệu lệnh chuyển tiền và trạng thái |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

---

End of spec.

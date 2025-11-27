# Offchain Gpi API Specification

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

### `offchainv1ConfirmMessage`
Thông điệp xác nhận thanh toán

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `confirmation_type` | string | `False` | Loại xác nhận |
| `timestamp` | string | `False` | Thời gian xác nhận |
| `uetr` | string | `False` | Unique End-to-End Transaction Reference |

### `offchainv1ConfirmPaymentResponse`
Phản hồi xác nhận thanh toán

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` | Dữ liệu thanh toán đã xác nhận |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1CreatePaymentResponse`
Phản hồi tạo thanh toán

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` | Dữ liệu thanh toán đã tạo |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1GetPaymentResponse`
Phản hồi lấy thông tin thanh toán

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | object [offchainv1Payment] | `False` | Dữ liệu thanh toán |
| `metadata` | object [offchainv1Metadata] | `False` | Metadata của response |
| `result` | object [offchainv1Result] | `False` | Kết quả thực thi |
| `signature` | object [offchainv1Signature] | `False` | Chữ ký số của response |

### `offchainv1Metadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `request_id` | string | `False` | ID định danh duy nhất cho request |
| `request_time` | string (int64) | `False` | Thời gian gửi request (Unix timestamp) |
| `version` | string | `False` | Phiên bản của protocol |

### `offchainv1Payment`
Thông tin thanh toán

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `amount` | number (double) | `False` | Số tiền thanh toán |
| `created_at` | string (date-time) | `False` | Thời gian tạo thanh toán |
| `creditor_agent` | string | `False` | BIC của ngân hàng người nhận |
| `creditor_agent_account` | object [offchainv1Account] | `False` | Thông tin tài khoản chi tiết của ngân hàng người nhận (tùy chọn) |
| `currency` | string | `False` | Mã tiền tệ (ví dụ: USD, VND) |
| `debtor_agent` | string | `False` | BIC của ngân hàng người gửi |
| `debtor_agent_account` | object [offchainv1Account] | `False` | Thông tin tài khoản chi tiết của ngân hàng người gửi (tùy chọn) |
| `end_to_end_id` | string | `False` | ID định danh end-to-end của giao dịch |
| `id` | string | `False` | ID định danh thanh toán |
| `status` | string | `False` | Trạng thái thanh toán |
| `transaction_reference` | string | `False` | Mã tham chiếu giao dịch |
| `uetr` | string | `False` | Unique End-to-End Transaction Reference |

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

---

End of spec.

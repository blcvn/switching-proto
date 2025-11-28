# Package Định Nghĩa Các Message Và Service Cho Offchain Authentication API Specification

---

## Schemas

### `offchainv1TokenResponse`
Phản hồi chứa token truy cập

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `access_token` | string | `False` | Token truy cập được cấp |
| `expires_in` | integer (int32) | `False` | Thời gian hết hạn của token (tính bằng giây) |
| `token_type` | string | `False` | Loại token (thường là "Bearer") |

---

End of spec.

# Package Định Nghĩa Các Message Và Service Cho Access Point Authentication API Specification

---

## Schemas

### `accesspointv1TokenResponse`
Response trả về token

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `access_token` | string | `False` | Access token để sử dụng cho các API call |
| `expires_in` | integer (int32) | `False` | Thời gian hết hạn của token (tính bằng giây) |
| `token_type` | string | `False` | Loại token (thường là "Bearer") |

---

End of spec.

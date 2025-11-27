# Offchain Events API Specification

---

## Schemas

### `offchainv1Event`
Sự kiện được gửi đến client

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | string | `False` | Dữ liệu của sự kiện (JSON string) |
| `event` | string | `False` | Loại sự kiện: welcome, update, ping |
| `timestamp` | string (date-time) | `False` | Thời gian xảy ra sự kiện |

### `offchainv1SubscribeCallbackResponse`
Phản hồi đăng ký callback

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `subscribed` | string | `False` | Trạng thái đăng ký (thường là "ok" hoặc "success") |

### `offchainv1UnsubscribeCallbackResponse`
Phản hồi hủy đăng ký callback

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `unsubscribed` | string | `False` | Trạng thái hủy đăng ký (thường là "ok" hoặc "success") |

---

End of spec.

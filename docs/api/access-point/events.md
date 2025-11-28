# Package Định Nghĩa Các Message Và Service Cho Events Trong Access Point API Specification

---

## Schemas

### `accesspointv1Event`
Event (sự kiện) được gửi đến client

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | string | `False` | Dữ liệu của event (dạng JSON string) |
| `event` | string | `False` | Loại event: welcome, update, ping |
| `timestamp` | string (date-time) | `False` | Thời gian xảy ra event |

### `accesspointv1SubscribeCallbackResponse`
Response đăng ký callback

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `subscribed` | string | `False` | Trạng thái đăng ký (thường là "ok" hoặc "success") |

### `accesspointv1UnsubscribeCallbackResponse`
Response hủy đăng ký callback

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `unsubscribed` | string | `False` | Trạng thái hủy đăng ký (thường là "ok" hoặc "success") |

---

End of spec.

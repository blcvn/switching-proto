# Package Định Nghĩa Các Message Và Service Cho Health Check Trong Offchain API Specification

---

## Schemas

### `offchainv1HealthResponse`
Phản hồi kiểm tra sức khỏe

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `status` | string | `False` | Trạng thái (thường là "ok", "healthy", "unhealthy") |
| `time` | string (date-time) | `False` | Thời gian kiểm tra |

---

End of spec.

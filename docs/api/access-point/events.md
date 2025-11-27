# Access-Point Events API Specification

---

## Schemas

### `accesspointv1Event`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | string | `False` |  |
| `event` | string | `False` | welcome, update, ping |
| `timestamp` | string (date-time) | `False` |  |

### `accesspointv1SubscribeCallbackResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `subscribed` | string | `False` |  |

### `accesspointv1UnsubscribeCallbackResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `unsubscribed` | string | `False` |  |

---

End of spec.

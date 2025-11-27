# Offchain Events API Specification

---

## Schemas

### `offchainv1Event`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `data` | string | `False` |  |
| `event` | string | `False` | welcome, update, ping |
| `timestamp` | string (date-time) | `False` |  |

### `offchainv1SubscribeCallbackResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `subscribed` | string | `False` |  |

### `offchainv1UnsubscribeCallbackResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `unsubscribed` | string | `False` |  |

---

End of spec.

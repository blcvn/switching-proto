# Access-Point Kyc API Specification

---

## Schemas

### `accesspointv1AuditEntry`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `action` | string | `False` |  |
| `bic` | string | `False` |  |
| `time` | string | `False` |  |

### `accesspointv1AuditResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entries` | array | `False` |  |

### `accesspointv1Document`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | string | `False` |  |
| `type` | string | `False` |  |
| `url` | string | `False` |  |

### `accesspointv1GetEntityDocsResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `documents` | array | `False` |  |

### `accesspointv1KycEntity`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `bic` | string | `False` |  |
| `country` | string | `False` |  |
| `lei` | string | `False` |  |
| `name` | string | `False` |  |
| `updated_at` | string | `False` |  |

### `accesspointv1KycMetadata`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `fields` | array | `False` |  |
| `version` | string | `False` |  |

### `accesspointv1ListEntitiesResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `entities` | array | `False` |  |

### `accesspointv1SubmitKycResponse`

| Field | Type | Required | Description |
| --- | --- | --- | --- |
| `status` | string | `False` |  |

---

End of spec.

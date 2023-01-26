# bulk-operations
Test bulk inserts

## Test example

```
POST http://localhost:8080/orders
Content-Type: application/json

{
  "id": 2,
  "createdAt": "2023-01-30T20:53:12",
  "items": [
    {
      "skuCode": "10101",
      "quantity": 21,
      "brand": "BOT"
    }
  ]
}
```

## OR

```
curl -X POST --location "http://localhost:8080/orders" \
    -H "Content-Type: application/json" \
    -d "{
          \"id\": 2,
          \"createdAt\": \"2023-01-30T20:53:12\",
          \"items\": [
            {
              \"skuCode\": \"10101\",
              \"quantity\": 21,
              \"brand\": \"BOT\"
            }
          ]
        }"
```

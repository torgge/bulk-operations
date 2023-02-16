import { check } from 'k6';
import http from 'k6/http';

const IP = '192.168.0.164'
const PORT = '8080'
export const options = {
    vus: 1,
    // duration: '30s',
};
export default function () {
    const url = `http://${IP}:${PORT}/orders`;
    const payloadOne = JSON.stringify({
        "id": 1000,
        "createdAt": "2023-02-17T00:00:00",
        "items": [
            {
                "id": 1,
                "skuCode": "72500",
                "quantity": 10,
                "brand": "APPLE"
            }
        ]
    });

    const payloadTwo = JSON.stringify({
        "id": 1000,
        "createdAt": "2023-02-17T00:00:01",
        "items": [
            {
                "id": 1,
                "skuCode": "72500",
                "quantity": 9,
                "brand": "APPLE"
            }
        ]
    });

    const payloadThree = JSON.stringify({
        "id": 1000,
        "createdAt": "2023-02-17T00:00:02",
        "items": [
            {
                "id": 1,
                "skuCode": "72500",
                "quantity": 8,
                "brand": "APPLE"
            }
        ]
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
        tags: { name: 'POST-ENVERS' }
    };

    const resOne = http.post(url, payloadOne, params);
    check(resOne, {
        'is status 200': (r) => r.status === 201,
    });
    const resTwo = http.post(url, payloadTwo, params);
    check(resTwo, {
        'is status 200': (r) => r.status === 201,
    });
    const resThree = http.post(url, payloadThree, params);
    check(resThree, {
        'is status 200': (r) => r.status === 201,
    });
}

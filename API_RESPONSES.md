# 📋 API Responses & Output Examples

## 1. Authentication Endpoints

### Register User Response
**Endpoint:** `POST /api/auth/register`

**Status:** `201 Created`

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjI4Njk3MDAyLCJleHAiOjE2Mjg3ODM0MDJ9.K9y8z3xN2k5m1qR8pT7vW4x9z0aB3cD5eF6gH7iJ8kL9mN0oP1qR2sT3uV4wX5yZ6aB7cD8eF9gH0iJ1kL2mN3o",
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjI4Njk3MDAyLCJleHAiOjE2Mjk1ODE4MDJ9.X7z9w4v5u6t7s8r9q0p1o2n3m4l5k6j7i8h9g0f1e2d3c4b5a",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "user": {
    "id": 4,
    "username": "johndoe",
    "email": "johndoe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phone": null,
    "role": "USER",
    "isActive": true,
    "createdAt": "2026-05-15T06:52:12"
  },
  "message": "Registration successful"
}
```

---

### Login Response
**Endpoint:** `POST /api/auth/login`

**Status:** `200 OK`

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyODY5NzAwMiwiZXhwIjoxNjI4NzgzNDAyfQ.K9y8z3xN2k5m1qR8pT7vW4x9z0aB3cD5eF6gH7iJ8kL9mN0oP1qR2sT3uV4wX5yZ6aB7cD8eF9gH0iJ1kL2mN3o",
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyODY5NzAwMiwiZXhwIjoxNjI5NTgxODAyfQ.X7z9w4v5u6t7s8r9q0p1o2n3m4l5k6j7i8h9g0f1e2d3c4b5a",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@donation.com",
    "firstName": "Admin",
    "lastName": "User",
    "phone": null,
    "role": "ADMIN",
    "isActive": true,
    "createdAt": "2026-05-15T06:52:12"
  },
  "message": "Login successful"
}
```

---

### Get Current User Response
**Endpoint:** `GET /api/auth/me`

**Status:** `200 OK`

```json
{
  "id": 1,
  "username": "admin",
  "email": "admin@donation.com",
  "firstName": "Admin",
  "lastName": "User",
  "phone": null,
  "role": "ADMIN",
  "isActive": true,
  "createdAt": "2026-05-15T06:52:12"
}
```

---

## 2. Donor Endpoints

### Create Donor Response
**Endpoint:** `POST /api/donors`

**Status:** `201 Created`

```json
{
  "id": 4,
  "firstName": "Michael",
  "lastName": "Johnson",
  "email": "michael.johnson@example.com",
  "phone": "+1-555-987-6543",
  "address": "789 Oak Avenue",
  "city": "Boston",
  "state": "MA",
  "postalCode": "02101",
  "country": "USA",
  "donorType": "INDIVIDUAL",
  "totalDonated": 0.00,
  "donationCount": 0,
  "isActive": true,
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T10:30:00"
}
```

---

### Get All Donors Response
**Endpoint:** `GET /api/donors?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 1,
      "firstName": "Jane",
      "lastName": "Smith",
      "email": "jane@example.com",
      "phone": "+1234567890",
      "address": "123 Main St",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "USA",
      "donorType": "INDIVIDUAL",
      "totalDonated": 2500.00,
      "donationCount": 5,
      "isActive": true,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T06:52:12"
    },
    {
      "id": 2,
      "firstName": "ABC",
      "lastName": "Corporation",
      "email": "contact@abccorp.com",
      "phone": "+1987654321",
      "address": "456 Business Ave",
      "city": "Los Angeles",
      "state": "CA",
      "postalCode": "90001",
      "country": "USA",
      "donorType": "CORPORATE",
      "totalDonated": 15000.00,
      "donationCount": 3,
      "isActive": true,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T06:52:12"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 3,
  "first": true,
  "last": true,
  "numberOfElements": 3
}
```

---

### Get Donor by ID Response
**Endpoint:** `GET /api/donors/1`

**Status:** `200 OK`

```json
{
  "id": 1,
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "phone": "+1234567890",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "USA",
  "donorType": "INDIVIDUAL",
  "totalDonated": 2500.00,
  "donationCount": 5,
  "isActive": true,
  "createdAt": "2026-05-15T06:52:12",
  "updatedAt": "2026-05-15T06:52:12"
}
```

---

### Search Donors Response
**Endpoint:** `GET /api/donors/search?searchTerm=jane&page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 1,
      "firstName": "Jane",
      "lastName": "Smith",
      "email": "jane@example.com",
      "phone": "+1234567890",
      "address": "123 Main St",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "USA",
      "donorType": "INDIVIDUAL",
      "totalDonated": 2500.00,
      "donationCount": 5,
      "isActive": true,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T06:52:12"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "last": true,
  "numberOfElements": 1
}
```

---

### Get Donors by Type Response
**Endpoint:** `GET /api/donors/type/CORPORATE?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 2,
      "firstName": "ABC",
      "lastName": "Corporation",
      "email": "contact@abccorp.com",
      "phone": "+1987654321",
      "address": "456 Business Ave",
      "city": "Los Angeles",
      "state": "CA",
      "postalCode": "90001",
      "country": "USA",
      "donorType": "CORPORATE",
      "totalDonated": 15000.00,
      "donationCount": 3,
      "isActive": true,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T06:52:12"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "last": true,
  "numberOfElements": 1
}
```

---

## 3. Campaign Endpoints

### Create Campaign Response
**Endpoint:** `POST /api/campaigns`

**Status:** `201 Created`

```json
{
  "id": 4,
  "name": "Clean Water for Everyone",
  "description": "Providing clean drinking water to rural communities",
  "category": "COMMUNITY",
  "goalAmount": 50000.00,
  "raisedAmount": 0.00,
  "startDate": "2026-05-15T00:00:00",
  "endDate": "2026-10-15T00:00:00",
  "status": "DRAFT",
  "imageUrl": "https://example.com/water.jpg",
  "organizationName": "Water Foundation",
  "isFeatured": false,
  "progressPercentage": 0.0,
  "donationCount": 0,
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T10:30:00"
}
```

---

### Get All Campaigns Response
**Endpoint:** `GET /api/campaigns?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 1,
      "name": "Education for All",
      "description": "Support education for underprivileged children",
      "category": "EDUCATION",
      "goalAmount": 100000.00,
      "raisedAmount": 25000.00,
      "startDate": "2026-05-15T00:00:00",
      "endDate": "2026-08-15T00:00:00",
      "status": "ACTIVE",
      "imageUrl": "https://example.com/education.jpg",
      "organizationName": "Education Foundation",
      "isFeatured": true,
      "progressPercentage": 25.0,
      "donationCount": 50,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T08:00:00"
    },
    {
      "id": 2,
      "name": "Healthcare Initiative",
      "description": "Provide free healthcare to remote areas",
      "category": "HEALTH",
      "goalAmount": 150000.00,
      "raisedAmount": 75000.00,
      "startDate": "2026-06-01T00:00:00",
      "endDate": "2026-12-31T00:00:00",
      "status": "ACTIVE",
      "imageUrl": "https://example.com/health.jpg",
      "organizationName": "Health Ministry",
      "isFeatured": true,
      "progressPercentage": 50.0,
      "donationCount": 120,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T09:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 3,
  "first": true,
  "last": true,
  "numberOfElements": 2
}
```

---

### Get Featured Campaigns Response
**Endpoint:** `GET /api/campaigns/featured?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 1,
      "name": "Education for All",
      "description": "Support education for underprivileged children",
      "category": "EDUCATION",
      "goalAmount": 100000.00,
      "raisedAmount": 25000.00,
      "startDate": "2026-05-15T00:00:00",
      "endDate": "2026-08-15T00:00:00",
      "status": "ACTIVE",
      "imageUrl": "https://example.com/education.jpg",
      "organizationName": "Education Foundation",
      "isFeatured": true,
      "progressPercentage": 25.0,
      "donationCount": 50,
      "createdAt": "2026-05-15T06:52:12",
      "updatedAt": "2026-05-15T08:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "last": true,
  "numberOfElements": 1
}
```

---

## 4. Donation Endpoints

### Create Donation Response
**Endpoint:** `POST /api/donations`

**Status:** `201 Created`

```json
{
  "id": 10,
  "donorId": 1,
  "donorName": "Jane Smith",
  "campaignId": 1,
  "campaignName": "Education for All",
  "amount": 500.00,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "paymentStatus": "PENDING",
  "transactionId": "550e8400-e29b-41d4-a716-446655440000",
  "donationType": "ONE_TIME",
  "anonymous": false,
  "message": "Great cause, supporting your mission!",
  "receiptSent": false,
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T10:30:00"
}
```

---

### Get All Donations Response
**Endpoint:** `GET /api/donations?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 5,
      "donorId": 1,
      "donorName": "Jane Smith",
      "campaignId": 1,
      "campaignName": "Education for All",
      "amount": 1000.00,
      "currency": "USD",
      "paymentMethod": "CREDIT_CARD",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440001",
      "donationType": "ONE_TIME",
      "anonymous": false,
      "message": "Keep up the good work!",
      "receiptSent": true,
      "createdAt": "2026-05-14T15:20:00",
      "updatedAt": "2026-05-15T08:00:00"
    },
    {
      "id": 6,
      "donorId": 2,
      "donorName": "Anonymous",
      "campaignId": 2,
      "campaignName": "Healthcare Initiative",
      "amount": 5000.00,
      "currency": "USD",
      "paymentMethod": "BANK_TRANSFER",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440002",
      "donationType": "ONE_TIME",
      "anonymous": true,
      "message": null,
      "receiptSent": true,
      "createdAt": "2026-05-14T10:15:00",
      "updatedAt": "2026-05-15T07:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 5,
  "first": true,
  "last": true,
  "numberOfElements": 2
}
```

---

### Get Donations by Donor Response
**Endpoint:** `GET /api/donations/donor/1?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 5,
      "donorId": 1,
      "donorName": "Jane Smith",
      "campaignId": 1,
      "campaignName": "Education for All",
      "amount": 1000.00,
      "currency": "USD",
      "paymentMethod": "CREDIT_CARD",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440001",
      "donationType": "ONE_TIME",
      "anonymous": false,
      "message": "Keep up the good work!",
      "receiptSent": true,
      "createdAt": "2026-05-14T15:20:00",
      "updatedAt": "2026-05-15T08:00:00"
    },
    {
      "id": 7,
      "donorId": 1,
      "donorName": "Jane Smith",
      "campaignId": 2,
      "campaignName": "Healthcare Initiative",
      "amount": 500.00,
      "currency": "USD",
      "paymentMethod": "DIGITAL_WALLET",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440003",
      "donationType": "MONTHLY",
      "anonymous": false,
      "message": null,
      "receiptSent": true,
      "createdAt": "2026-05-13T12:00:00",
      "updatedAt": "2026-05-15T06:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 2,
  "first": true,
  "last": true,
  "numberOfElements": 2
}
```

---

### Get Donations by Campaign Response
**Endpoint:** `GET /api/donations/campaign/1?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 5,
      "donorId": 1,
      "donorName": "Jane Smith",
      "campaignId": 1,
      "campaignName": "Education for All",
      "amount": 1000.00,
      "currency": "USD",
      "paymentMethod": "CREDIT_CARD",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440001",
      "donationType": "ONE_TIME",
      "anonymous": false,
      "message": "Keep up the good work!",
      "receiptSent": true,
      "createdAt": "2026-05-14T15:20:00",
      "updatedAt": "2026-05-15T08:00:00"
    },
    {
      "id": 8,
      "donorId": 3,
      "donorName": "NGO Foundation",
      "campaignId": 1,
      "campaignName": "Education for All",
      "amount": 2000.00,
      "currency": "USD",
      "paymentMethod": "BANK_TRANSFER",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440004",
      "donationType": "ONE_TIME",
      "anonymous": false,
      "message": null,
      "receiptSent": true,
      "createdAt": "2026-05-13T09:30:00",
      "updatedAt": "2026-05-15T05:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 2,
  "first": true,
  "last": true,
  "numberOfElements": 2
}
```

---

### Get Donations by Status Response
**Endpoint:** `GET /api/donations/status/COMPLETED?page=0&size=20`

**Status:** `200 OK`

```json
{
  "content": [
    {
      "id": 5,
      "donorId": 1,
      "donorName": "Jane Smith",
      "campaignId": 1,
      "campaignName": "Education for All",
      "amount": 1000.00,
      "currency": "USD",
      "paymentMethod": "CREDIT_CARD",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440001",
      "donationType": "ONE_TIME",
      "anonymous": false,
      "message": "Keep up the good work!",
      "receiptSent": true,
      "createdAt": "2026-05-14T15:20:00",
      "updatedAt": "2026-05-15T08:00:00"
    },
    {
      "id": 6,
      "donorId": 2,
      "donorName": "Anonymous",
      "campaignId": 2,
      "campaignName": "Healthcare Initiative",
      "amount": 5000.00,
      "currency": "USD",
      "paymentMethod": "BANK_TRANSFER",
      "paymentStatus": "COMPLETED",
      "transactionId": "550e8400-e29b-41d4-a716-446655440002",
      "donationType": "ONE_TIME",
      "anonymous": true,
      "message": null,
      "receiptSent": true,
      "createdAt": "2026-05-14T10:15:00",
      "updatedAt": "2026-05-15T07:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": []
  },
  "totalPages": 1,
  "totalElements": 5,
  "first": true,
  "last": true,
  "numberOfElements": 2
}
```

---

## 5. Dashboard & Analytics

### Get Dashboard Stats Response
**Endpoint:** `GET /api/dashboard/stats`

**Status:** `200 OK`

```json
{
  "totalDonations": 32500.00,
  "totalDonors": 3,
  "activeCampaigns": 2,
  "completedCampaigns": 0,
  "averageDonation": 1000.00,
  "totalDonationCount": 5
}
```

---

### Get Campaign Report Response
**Endpoint:** `GET /api/dashboard/report/campaign/1`

**Status:** `200 OK`

```json
{
  "totalDonations": 25000.00,
  "totalDonationCount": 50,
  "averageDonation": 500.00
}
```

---

### Get Donor Report Response
**Endpoint:** `GET /api/dashboard/report/donor/1`

**Status:** `200 OK`

```json
{
  "totalDonations": 2500.00,
  "totalDonationCount": 5,
  "averageDonation": 500.00
}
```

---

## 6. Error Responses

### 400 Bad Request - Validation Error
**Status:** `400 Bad Request`

```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "Request validation failed",
  "errors": {
    "firstName": "First name is required",
    "email": "Email should be valid",
    "amount": "Donation amount must be positive"
  },
  "timestamp": "2026-05-15T10:35:20"
}
```

---

### 401 Unauthorized - Invalid Credentials
**Status:** `401 Unauthorized`

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid username or password",
  "timestamp": "2026-05-15T10:35:20"
}
```

---

### 404 Not Found - Resource Not Found
**Status:** `404 Not Found`

```json
{
  "status": 404,
  "error": "Resource Not Found",
  "message": "Donor not found with id: 999",
  "timestamp": "2026-05-15T10:35:20"
}
```

---

### 409 Conflict - Duplicate Resource
**Status:** `409 Conflict`

```json
{
  "status": 409,
  "error": "Duplicate Resource",
  "message": "Email is already registered",
  "timestamp": "2026-05-15T10:35:20"
}
```

---

### 403 Forbidden - Insufficient Permissions
**Status:** `403 Forbidden`

```json
{
  "status": 403,
  "error": "Access Denied",
  "message": "You don't have permission to access this resource",
  "timestamp": "2026-05-15T10:35:20"
}
```

---

### 500 Internal Server Error
**Status:** `500 Internal Server Error`

```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "timestamp": "2026-05-15T10:35:20"
}
```

---

## 7. Success Messages

### Update/Delete Operations
**Status:** `200 OK`

```json
{
  "message": "Donor deactivated successfully"
}
```

Or:

```json
{
  "message": "Campaign status updated successfully"
}
```

Or:

```json
{
  "message": "Payment status updated successfully"
}
```

---

## 📊 Response Summary

| Endpoint | Status | Response Type |
|----------|--------|---------------|
| Registration | 201 | User + JWT Token |
| Login | 200 | User + JWT Token |
| Create Resource | 201 | Created Resource Object |
| List Resources | 200 | Paginated Array |
| Get Single Resource | 200 | Single Resource Object |
| Update Resource | 200 | Updated Resource Object |
| Delete Resource | 200 | Success Message |
| Dashboard Stats | 200 | Statistics Object |
| Report | 200 | Report Object |
| Validation Error | 400 | Error Details |
| Auth Error | 401 | Error Message |
| Not Found | 404 | Error Message |
| Duplicate | 409 | Error Message |
| Server Error | 500 | Error Message |

---

## 🧪 Testing with cURL

### Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "Password@123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password"
  }'
```

### Get All Donors
```bash
curl -X GET "http://localhost:8080/api/donors?page=0&size=20" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Create Donation
```bash
curl -X POST http://localhost:8080/api/donations \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "donorId": 1,
    "campaignId": 1,
    "amount": 500,
    "currency": "USD",
    "paymentMethod": "CREDIT_CARD",
    "donationType": "ONE_TIME",
    "anonymous": false,
    "message": "Keep up the good work!"
  }'
```

---

**All responses are JSON formatted with proper HTTP status codes!** ✨

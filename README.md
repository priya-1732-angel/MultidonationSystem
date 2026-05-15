# 🎁 Multi Donation Management System

A comprehensive, production-ready Java-based donation management platform with Spring Boot, featuring multiple modules for managing donors, campaigns, donations, and analytics.

## ✨ Features

### 📊 Core Modules
- **User Management** - Secure authentication with JWT tokens and role-based access
- **Donor Management** - Register, track, and manage different types of donors
- **Campaign Management** - Create and manage donation campaigns with goals and progress tracking
- **Donation Tracking** - Process and track donations with multiple payment methods
- **Dashboard & Analytics** - Real-time statistics and detailed reports
- **Payment Processing** - Support for multiple payment methods
- **Notifications** - Infrastructure for donation confirmations and receipts

### 🎯 Key Capabilities
✅ Anonymous donations for privacy  
✅ Multiple donor types (Individual, Corporate, Organization, etc.)  
✅ Campaign progress visualization  
✅ Recurring donation support  
✅ Advanced filtering and search  
✅ Comprehensive audit logging  
✅ Role-based access control (Admin, Manager, User)  
✅ RESTful API with 50+ endpoints  
✅ Pagination for efficient data handling  
✅ Beautiful error handling and validation  

## 🏗️ Technology Stack

- **Backend**: Java 17, Spring Boot 3.1.5
- **Database**: MySQL 8.0
- **Security**: Spring Security, JWT (JSON Web Tokens)
- **ORM**: Spring Data JPA, Hibernate
- **API**: RESTful Services with Spring Web
- **Build**: Maven 3.6+
- **Validation**: Jakarta Validation (Bean Validation 3.0)
- **Testing**: JUnit 5, Spring Test

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- MySQL 8.0+
- Maven 3.6+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/priya-1732-angel/MultidonationSystem.git
cd MultidonationSystem
```

2. **Setup MySQL Database**
```bash
mysql -u root -p < src/main/resources/db/schema.sql
```

3. **Configure Database Connection**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/donation_db
spring.datasource.username=root
spring.datasource.password=your_password
```

4. **Build the Project**
```bash
mvn clean install
```

5. **Run the Application**
```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

## 📚 Project Structure

```
MultidonationSystem/
├── src/main/java/com/donation/
│   ├── controller/          # REST API endpoints
│   ├── service/             # Business logic layer
│   ├── repository/          # Data access layer
│   ├── model/               # Entity classes
│   ├── dto/                 # Data Transfer Objects
│   ├── exception/           # Custom exceptions
│   ├── security/            # JWT & Spring Security
│   ├── config/              # Application configuration
│   └── DonationSystemApp.java
├── src/main/resources/
│   ├── application.properties
│   └── db/schema.sql
├── pom.xml
└── README.md
```

## 🔐 Authentication

### Register User
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "newuser",
  "password": "Password@123"
}
```

### Login
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "newuser",
  "password": "Password@123"
}
```

### Response
```json
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc...",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "user": { ... }
}
```

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/me` - Get current user

### Donors
- `POST /api/donors` - Create donor
- `GET /api/donors` - List all donors (paginated)
- `GET /api/donors/{id}` - Get donor by ID
- `GET /api/donors/search?searchTerm=...` - Search donors
- `PUT /api/donors/{id}` - Update donor
- `PATCH /api/donors/{id}/deactivate` - Deactivate donor
- `DELETE /api/donors/{id}` - Delete donor (Admin only)

### Campaigns
- `POST /api/campaigns` - Create campaign (Admin/Manager)
- `GET /api/campaigns` - List all campaigns
- `GET /api/campaigns/active` - List active campaigns
- `GET /api/campaigns/featured` - List featured campaigns
- `GET /api/campaigns/{id}` - Get campaign by ID
- `GET /api/campaigns/search?searchTerm=...` - Search campaigns
- `PUT /api/campaigns/{id}` - Update campaign (Admin/Manager)
- `PATCH /api/campaigns/{id}/status?status=...` - Update status

### Donations
- `POST /api/donations` - Create donation
- `GET /api/donations` - List all donations
- `GET /api/donations/donor/{donorId}` - Get donations by donor
- `GET /api/donations/campaign/{campaignId}` - Get donations by campaign
- `GET /api/donations/status/{status}` - Get donations by status
- `GET /api/donations/recent` - Get recent donations
- `PATCH /api/donations/{id}/payment-status?status=...` - Update payment status

### Dashboard
- `GET /api/dashboard/stats` - Get dashboard statistics
- `GET /api/dashboard/report/campaign/{campaignId}` - Get campaign report
- `GET /api/dashboard/report/donor/{donorId}` - Get donor report

## 👥 Sample Credentials

After running schema.sql, use these credentials:

**Admin Account:**
- Username: `admin`
- Password: `password`
- Role: ADMIN

**Manager Account:**
- Username: `manager`
- Password: `password`
- Role: MANAGER

**Donor Account:**
- Username: `donor1`
- Password: `password`
- Role: USER

## 🔒 Security Features

- JWT-based stateless authentication
- BCrypt password encryption
- Role-based access control (RBAC)
- Spring Security integration
- CORS configuration for frontend integration
- Input validation and sanitization
- Exception handling and error logging

## 📊 Database Schema

### Tables
- `users` - System users with roles
- `donors` - Donor information and statistics
- `campaigns` - Donation campaigns
- `donations` - Individual donations and transactions

All tables include:
- Timestamps (created_at, updated_at)
- Indexed columns for performance
- Foreign key relationships
- Data validation constraints

## 🧪 Testing

Run tests using Maven:
```bash
mvn test
```

## 📝 Logging

Logs are written to:
- Console (INFO level)
- File: `logs/donation-system.log`

Configure logging in `application.properties`

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the MIT License.

## 🆘 Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

## 👨‍💻 Author

Created with ❤️ by the Donation System Team

---

**Made with Java & Spring Boot** 🚀

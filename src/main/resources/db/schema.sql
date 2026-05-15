-- Create Database
CREATE DATABASE IF NOT EXISTS donation_db;
USE donation_db;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role)
);

-- Donors Table
CREATE TABLE IF NOT EXISTS donors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    donor_type VARCHAR(50) NOT NULL,
    total_donated DECIMAL(15, 2) NOT NULL DEFAULT 0,
    donation_count INT NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_donor_type (donor_type),
    INDEX idx_country (country),
    INDEX idx_is_active (is_active)
);

-- Campaigns Table
CREATE TABLE IF NOT EXISTS campaigns (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(50) NOT NULL,
    goal_amount DECIMAL(15, 2) NOT NULL,
    raised_amount DECIMAL(15, 2) NOT NULL DEFAULT 0,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    image_url VARCHAR(500),
    organization_name VARCHAR(255),
    is_featured BOOLEAN NOT NULL DEFAULT FALSE,
    donation_count INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_status (status),
    INDEX idx_category (category),
    INDEX idx_is_featured (is_featured),
    INDEX idx_created_at (created_at)
);

-- Donations Table
CREATE TABLE IF NOT EXISTS donations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    donor_id BIGINT NOT NULL,
    campaign_id BIGINT NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL DEFAULT 'USD',
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    transaction_id VARCHAR(100) UNIQUE,
    donation_type VARCHAR(50) NOT NULL DEFAULT 'ONE_TIME',
    anonymous BOOLEAN NOT NULL DEFAULT FALSE,
    message TEXT,
    receipt_sent BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (donor_id) REFERENCES donors(id),
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id),
    INDEX idx_donor_id (donor_id),
    INDEX idx_campaign_id (campaign_id),
    INDEX idx_payment_status (payment_status),
    INDEX idx_created_at (created_at)
);

-- Insert Sample Data

-- Sample Users
INSERT INTO users (username, email, password, first_name, last_name, role) VALUES
('admin', 'admin@donation.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy6PDsm', 'Admin', 'User', 'ADMIN'),
('manager', 'manager@donation.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy6PDsm', 'Manager', 'User', 'MANAGER'),
('donor1', 'donor1@example.com', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy6PDsm', 'John', 'Doe', 'USER');

-- Sample Donors
INSERT INTO donors (first_name, last_name, email, phone, address, city, country, donor_type) VALUES
('Jane', 'Smith', 'jane@example.com', '+1234567890', '123 Main St', 'New York', 'USA', 'INDIVIDUAL'),
('ABC Corporation', 'Corp', 'contact@abccorp.com', '+1987654321', '456 Business Ave', 'Los Angeles', 'USA', 'CORPORATE'),
('NGO Foundation', 'NGO', 'info@ngofoundation.org', '+1555555555', '789 Charity Lane', 'Chicago', 'USA', 'ORGANIZATION');

-- Sample Campaigns
INSERT INTO campaigns (name, description, category, goal_amount, start_date, end_date, status, is_featured) VALUES
('Education for All', 'Support education for underprivileged children', 'EDUCATION', 100000, '2026-05-15 00:00:00', '2026-08-15 00:00:00', 'ACTIVE', TRUE),
('Healthcare Initiative', 'Provide free healthcare to remote areas', 'HEALTH', 150000, '2026-06-01 00:00:00', '2026-12-31 00:00:00', 'ACTIVE', TRUE),
('Environmental Protection', 'Protect and restore natural ecosystems', 'ENVIRONMENT', 80000, '2026-05-20 00:00:00', '2026-09-20 00:00:00', 'DRAFT', FALSE);


CREATE DATABASE stepstyleShop_db;

CREATE USER 'userUTP2025'@'localhost' IDENTIFIED BY 'contraUTP2025';
GRANT ALL PRIVILEGES ON stepstyleShop_db.* TO 'userUTP2025'@'localhost';
FLUSH PRIVILEGES;
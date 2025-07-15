
DROP DATABASE `stepstyleShop_db`;
-- =================================================================================================
-- PASO 1: CREACIÓN DE LA BASE DE DATOS
-- =================================================================================================
CREATE DATABASE IF NOT EXISTS `stepstyleShop_db`;

USE `stepstyleShop_db`;

-- =================================================================================================
-- PASO 2: CREACIÓN DE LAS TABLAS
-- =================================================================================================
CREATE TABLE `usuarios` (
  `id_usuario` INT AUTO_INCREMENT PRIMARY KEY,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `rol` VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
  `fecha_registro` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_rol CHECK (`rol` IN ('ROLE_ADMIN', 'ROLE_USER'))
);

CREATE TABLE `productos` (
  `id_producto` INT AUTO_INCREMENT PRIMARY KEY,
  `nombre` VARCHAR(200) NOT NULL UNIQUE,
  `descripcion` TEXT,
  `precio` DECIMAL(10, 2) NOT NULL,
  `stock` INT NOT NULL,
  `imagen_url` VARCHAR(255),
  CONSTRAINT chk_precio CHECK (`precio` > 0),
  CONSTRAINT chk_stock CHECK (`stock` >= 0)
);

CREATE TABLE `ventas` (
  `id_venta` INT AUTO_INCREMENT PRIMARY KEY,
  `id_usuario` INT NOT NULL,
  `fecha_venta` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios`(`id_usuario`) ON DELETE RESTRICT 
);

CREATE TABLE `detalle_venta` (
  `id_detalle_venta` INT AUTO_INCREMENT PRIMARY KEY,
  `id_venta` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio_unitario` DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (`id_venta`) REFERENCES `ventas`(`id_venta`) ON DELETE CASCADE,
  FOREIGN KEY (`id_producto`) REFERENCES `productos`(`id_producto`) ON DELETE RESTRICT,
  CONSTRAINT chk_cantidad CHECK (`cantidad` >= 1)
);

-- =================================================================================================
-- PASO 3: INSERCIÓN DE DATOS CON CONTRASEÑAS CORRECTAS
-- =================================================================================================

-- Contraseña para admin: "adminpass"
-- Contraseña para user: "password"
INSERT INTO `usuarios` (`nombre`, `apellido`, `correo`, `password`, `direccion`, `telefono`, `rol`) VALUES
('Admin', 'StepStyle', 'admin@example.com', '$2a$10$z6kkAr2chlruqT3B7PMZr.lHKN0fWfhmqc.FE61i7YaHK0JoRak.S', 'Av. Siempre Viva 742', '987654321', 'ROLE_ADMIN'),
('Usuario', 'Prueba', 'user@example.com', '$2a$10$tXSs19NRg.k3Znv2aEYM/..EWjCuXchgL6r1Xq/Xzoay.1QcmiYma', 'Calle Falsa 123', '123456789', 'ROLE_USER');

-- Insertar productos de ejemplo
INSERT INTO `productos` (`nombre`, `descripcion`, `precio`, `stock`, `imagen_url`) VALUES
('Zapatillas Deportivas Nike Air', 'Zapatillas ideales para correr y hacer ejercicio. Urbanas blanco court royale.', 250.00, 50, '/img/shop_01.jpg'),
('Zapato Casual de Cuero', 'Zapatos de cuero genuino, perfectos para un look casual y elegante.', 180.50, 30, '/img/shop_02.jpg');

-- Insertar una venta de ejemplo
INSERT INTO `ventas` (`id_usuario`, `total`) VALUES (2, 430.50);
SET @id_venta1 = LAST_INSERT_ID();
INSERT INTO `detalle_venta` (`id_venta`, `id_producto`, `cantidad`, `precio_unitario`) VALUES
(@id_venta1, 1, 1, 250.00),
(@id_venta1, 2, 1, 180.50);

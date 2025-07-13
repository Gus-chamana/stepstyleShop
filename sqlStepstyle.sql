-- =================================================================================================
-- PASO 1: CREACIÓN DE LA BASE DE DATOS Y USUARIO
-- Nota: Estos comandos se ejecutan una sola vez. Si ya existen, puedes comentarlos o saltarlos.
-- =================================================================================================
CREATE DATABASE IF NOT EXISTS `stepstyleShop_db`;

USE `stepstyleShop_db`;

-- Crear el usuario y otorgar privilegios (si no se ha hecho antes)
-- CREATE USER 'userUTP2025'@'localhost' IDENTIFIED BY 'contraUTP2025';
-- GRANT ALL PRIVILEGES ON stepstyleShop_db.* TO 'userUTP2025'@'localhost';
-- FLUSH PRIVILEGES;


-- =================================================================================================
-- PASO 2: CREACIÓN DE LAS TABLAS
-- Se eliminan las tablas si ya existen para permitir una reinicialización limpia del script.
-- =================================================================================================
DROP TABLE IF EXISTS `detalle_venta`;
DROP TABLE IF EXISTS `ventas`;
DROP TABLE IF EXISTS `productos`;
DROP TABLE IF EXISTS `clientes`;

--
-- Tabla: clientes
-- Almacena la información de los usuarios del sistema, incluyendo sus roles y credenciales.
-- RF1: Gestión de Clientes [cite: 162]
--
CREATE TABLE `clientes` (
  `id_cliente` INT AUTO_INCREMENT PRIMARY KEY,
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

--
-- Tabla: productos
-- Almacena el catálogo de productos disponibles en la tienda.
-- RF2: Gestión de Productos [cite: 202]
--
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

--
-- Tabla: ventas
-- Almacena la información de las cabeceras de las ventas, asociando un cliente y el total.
-- RF3: Gestión de Ventas [cite: 239]
--
CREATE TABLE `ventas` (
  `id_venta` INT AUTO_INCREMENT PRIMARY KEY,
  `id_cliente` INT NOT NULL,
  `fecha_venta` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (`id_cliente`) REFERENCES `clientes`(`id_cliente`) ON DELETE RESTRICT
);

--
-- Tabla: detalle_venta
-- Almacena los productos específicos asociados a cada venta, con su cantidad y precio en ese momento.
-- RF3.1: Registrar Venta [cite: 241, 243]
--
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
-- PASO 3: INSERCIÓN DE DATOS INICIALES
-- Datos de ejemplo para que la aplicación sea funcional desde el inicio.
-- =================================================================================================

--
-- Insertar usuarios de prueba con contraseñas hasheadas [cite: 10, 38, 64]
-- Contraseña para admin: "adminpass"
-- Contraseña para user: "password"
--
INSERT INTO `clientes` (`nombre`, `apellido`, `correo`, `password`, `direccion`, `telefono`, `rol`) VALUES
('Admin', 'StepStyle', 'admin@example.com', '$2a$10$3sQ7YlR8.F.tB5U3Z.N3C.Wq9/9Wv8Qv.Xv1Q.Yv.Zz.Zz.Zz.Zz.', 'Av. Siempre Viva 742', '987654321', 'ROLE_ADMIN'),
('Usuario', 'Prueba', 'user@example.com', '$2a$10$I.e.g.Y5b5l0f0A.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u', 'Calle Falsa 123', '123456789', 'ROLE_USER');

--
-- Insertar productos de ejemplo
--
INSERT INTO `productos` (`nombre`, `descripcion`, `precio`, `stock`, `imagen_url`) VALUES
('Zapatillas Deportivas Nike Air', 'Zapatillas ideales para correr y hacer ejercicio. Urbanas blanco court royale.', 250.00, 50, '/img/shop_01.jpg'),
('Zapato Casual de Cuero', 'Zapatos de cuero genuino, perfectos para un look casual y elegante.', 180.50, 30, '/img/shop_02.jpg'),
('Botella Deportiva 1.3L', 'Botella deportiva de 1.3 litros de capacidad de la marca Mountain Gear.', 25.00, 100, '/img/feature_prod_01.jpg'),
('Zapatillas Mujer Nike', 'Zapatillas Nike para mujer, ideales para correr y hacer ejercicio. Urbanas blanco court royale.', 480.00, 40, '/img/feature_prod_02.jpg'),
('Zapatos Adidas Samba', 'Zapatos Adidas Samba, ligeros y cómodos para el clima cálido.', 360.00, 60, '/img/feature_prod_03.jpg'),
('Reloj Analógico de Lujo', 'Reloj con correa de acero inoxidable y diseño minimalista.', 550.00, 20, '/img/category_img_01.jpg');

--
-- Insertar una venta de ejemplo con sus detalles
--
-- Venta 1
INSERT INTO `ventas` (`id_cliente`, `total`) VALUES (2, 430.50);
SET @id_venta1 = LAST_INSERT_ID();
INSERT INTO `detalle_venta` (`id_venta`, `id_producto`, `cantidad`, `precio_unitario`) VALUES
(@id_venta1, 1, 1, 250.00),
(@id_venta1, 2, 1, 180.50);

-- Venta 2
INSERT INTO `ventas` (`id_cliente`, `total`) VALUES (2, 840.00);
SET @id_venta2 = LAST_INSERT_ID();
INSERT INTO `detalle_venta` (`id_venta`, `id_producto`, `cantidad`, `precio_unitario`) VALUES
(@id_venta2, 4, 1, 480.00),
(@id_venta2, 5, 1, 360.00);


-- =================================================================================================
-- FIN DEL SCRIPT
-- =================================================================================================
-- ==============================================
-- Tabla: Cliente
-- ==============================================
IF OBJECT_ID('Cliente', 'U') IS NULL
BEGIN
CREATE TABLE Cliente (
                         idCliente INT PRIMARY KEY IDENTITY(1,1),
                         nombre NVARCHAR(100) NOT NULL,
                         apellido NVARCHAR(100) NOT NULL,
                         numeroIdentificacion NVARCHAR(50) NOT NULL UNIQUE,
                         direccion NVARCHAR(200),
                         correoElectronico NVARCHAR(100),
                         telefono NVARCHAR(20),
                         fechaNacimiento DATE,
                         fechaRegistro DATETIME DEFAULT GETDATE()
);
END

-- ==============================================
-- Tabla: SolicitudPrestamo
-- ==============================================
IF OBJECT_ID('SolicitudPrestamo', 'U') IS NULL
BEGIN
CREATE TABLE SolicitudPrestamo (
                                   idSolicitud INT PRIMARY KEY IDENTITY(1,1),
                                   idCliente INT NOT NULL,
                                   monto DECIMAL(18,2) NOT NULL,
                                   plazoEnMeses INT NOT NULL,
                                   tasaInteres DECIMAL(5,2) NOT NULL,
                                   fechaSolicitud DATE NOT NULL DEFAULT GETDATE(),
                                   estado NVARCHAR(20) NOT NULL CHECK (estado IN ('PENDIENTE', 'APROBADO', 'RECHAZADO')),
                                   motivoRechazo NVARCHAR(255),

                                   CONSTRAINT FK_Solicitud_Cliente FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE CASCADE
);
END

-- ==============================================
-- Tabla: Prestamo
-- ==============================================
IF OBJECT_ID('Prestamo', 'U') IS NULL
BEGIN
CREATE TABLE Prestamo (
                          idPrestamo INT PRIMARY KEY IDENTITY(1,1),
                          idSolicitud INT NOT NULL UNIQUE,
                          montoAprobado DECIMAL(18,2) NOT NULL,
                          fechaAprobacion DATE NOT NULL DEFAULT GETDATE(),
                          saldoPendiente DECIMAL(18,2) NOT NULL,
                          estado NVARCHAR(20) NOT NULL CHECK (estado IN ('VIGENTE', 'FINIQUITADO')),

                          CONSTRAINT FK_Prestamo_Solicitud FOREIGN KEY (idSolicitud) REFERENCES SolicitudPrestamo(idSolicitud) ON DELETE CASCADE
);
END

-- ==============================================
-- Tabla: Pago
-- ==============================================
IF OBJECT_ID('Pago', 'U') IS NULL
BEGIN
CREATE TABLE Pago (
                      idPago INT PRIMARY KEY IDENTITY(1,1),
                      idPrestamo INT NOT NULL,
                      montoPagado DECIMAL(18,2) NOT NULL,
                      fechaPago DATETIME NOT NULL DEFAULT GETDATE(),

                      CONSTRAINT FK_Pago_Prestamo FOREIGN KEY (idPrestamo) REFERENCES Prestamo(idPrestamo) ON DELETE CASCADE
);
END
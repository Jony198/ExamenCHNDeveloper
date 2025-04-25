-- ==============================================
-- Tabla: Cliente
-- ==============================================
IF OBJECT_ID('Cliente', 'U') IS NULL
BEGIN
CREATE TABLE Cliente (
                         idCliente INT IDENTITY(1,1) PRIMARY KEY,
                         nombre NVARCHAR(100) NOT NULL,
                         apellido NVARCHAR(100) NOT NULL,
                         numeroIdentificacion NVARCHAR(20) NOT NULL UNIQUE,
                         fechaNacimiento DATE NOT NULL,
                         direccion NVARCHAR(255),
                         correoElectronico NVARCHAR(100),
                         telefono NVARCHAR(20),
                         fechaRegistro DATETIME DEFAULT GETDATE()
);
END

-- ==============================================
-- Tabla: Cuenta
-- ==============================================
IF OBJECT_ID('Cuenta', 'U') IS NULL
BEGIN
CREATE TABLE Cuenta (
                        idCuenta INT IDENTITY(1,1) PRIMARY KEY,
                        idCliente INT NOT NULL,
                        tipoCuenta NVARCHAR(20) NOT NULL CHECK (tipoCuenta IN ('ahorro', 'monetaria')),
                        montoApertura DECIMAL(18, 2) NOT NULL,
                        estadoCuenta NVARCHAR(20) NOT NULL DEFAULT 'activa',
                        motivoEstadoCuenta NVARCHAR(255) NULL,
                        fechaCreacion DATETIME DEFAULT GETDATE(),
                        FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);
END

-- ==============================================
-- Tabla: Chequera
-- ==============================================
IF OBJECT_ID('Chequera', 'U') IS NULL
BEGIN
CREATE TABLE Chequera (
                          idChequera INT IDENTITY(1,1) PRIMARY KEY,
                          idCuenta INT NOT NULL,
                          cantidadCheques INT NOT NULL,
                          estadoChequera NVARCHAR(20) NOT NULL DEFAULT 'activa',
                          motivoEstadoChequera NVARCHAR(255) NULL,
                          fechaCreacion DATETIME DEFAULT GETDATE(),
                          FOREIGN KEY (idCuenta) REFERENCES Cuenta(idCuenta)
);
END

-- ==============================================
-- Tabla: Cheque
-- ==============================================
IF OBJECT_ID('Cheque', 'U') IS NULL
BEGIN
CREATE TABLE Cheque (
                        idCheque INT IDENTITY(1,1) PRIMARY KEY,
                        idChequera INT NOT NULL,
                        numeroCheque INT NOT NULL,
                        estadoCheque NVARCHAR(20) NOT NULL DEFAULT 'emitido',
                        motivoEstadoCheque NVARCHAR(255) NULL,
                        monto DECIMAL(18,2) NULL,
                        fechaMovimiento DATETIME NULL,
                        FOREIGN KEY (idChequera) REFERENCES Chequera(idChequera)
);
END

-- ==============================================
-- Índices para mejorar rendimiento
-- ==============================================
IF NOT EXISTS (SELECT name FROM sys.indexes WHERE name = 'idx_Cuenta_Cliente')
CREATE INDEX idx_Cuenta_Cliente ON Cuenta(idCliente);

IF NOT EXISTS (SELECT name FROM sys.indexes WHERE name = 'idx_Chequera_Cuenta')
CREATE INDEX idx_Chequera_Cuenta ON Chequera(idCuenta);

IF NOT EXISTS (SELECT name FROM sys.indexes WHERE name = 'idx_Cheque_Chequera')
CREATE INDEX idx_Cheque_Chequera ON Cheque(idChequera);
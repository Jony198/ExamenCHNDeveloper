# 🏦 CHN - Sistema Web de Cuentas y Chequeras

Proyecto práctico para la administración de clientes, cuentas, chequeras y cheques, desarrollado en Java 17 con Spring Boot y SQL Server.

## 📦 Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA
- SQL Server (2019)
- Docker + Docker Compose
- Lombok
- JUnit + Mockito

---

## ⚙️ Requisitos

- Docker y Docker Compose
- Java 17
- Maven 3.8+

---

## 🚀 Ejecución rápida con Docker

### 1. Clonar el repositorio
```bash
git clone https://github.com/Jony198/ExamenCHNDeveloper.git
cd chn-backend
```

### 2. Levantar todo el entorno
```bash
docker-compose up --build
```

Esto levanta:
- **SQL Server** en `localhost:1433`
- **Backend Spring Boot** en `http://localhost:8080`

---

## 🔧 Endpoints disponibles

| Método | URL                          | Descripción                        |
|--------|------------------------------|------------------------------------|
| GET    | `/api/clientes`             | Listar clientes                    |
| POST   | `/api/clientes`             | Crear cliente                      |
| PUT    | `/api/clientes/{id}`        | Actualizar cliente                 |
| DELETE | `/api/clientes/{id}`        | Eliminar cliente                   |
| GET    | `/api/cuentas/cliente/{id}` | Listar cuentas por cliente         |
| POST   | `/api/cuentas`              | Crear cuenta                       |
| PUT    | `/api/cuentas/{id}`         | Actualizar cuenta                  |
| DELETE | `/api/cuentas/{id}`         | Eliminar cuenta                    |
| GET    | `/api/chequeras/cuenta/{id}`| Listar chequeras por cuenta        |
| POST   | `/api/chequeras`            | Crear chequera                     |
| PUT    | `/api/chequeras/{id}`       | Actualizar chequera                |
| DELETE | `/api/chequeras/{id}`       | Eliminar chequera                  |
| GET    | `/api/cheques/chequera/{id}`| Listar cheques por chequera        |
| POST   | `/api/cheques`              | Crear cheque                       |
| PUT    | `/api/cheques/{id}`         | Actualizar cheque                  |
| DELETE | `/api/cheques/{id}`         | Eliminar cheque                    |

---

## 🧪 Pruebas Unitarias

```bash
mvn test
```

Incluye pruebas para:
- ClienteService
- CuentaService
- ChequeraService
- ChequeService

---

## 📁 Estructura del Proyecto

```
src/
├── main/java/com/examen/demo
│   ├── controller
│   ├── entity          # ErrorEntity, ErrorList, AbstractResponse
│   ├── model           # Cliente, Cuenta, Chequera, Cheque
│   ├── repository
│   ├── response        # ClienteResponse, CuentaResponse, etc.
│   └── service
├── resources/
│   └── application.properties
└── test/
    └── java/com/examen/demo/service/
```

---

## 🧠 Notas

- Usuario de SQL Server: `sa`
- Contraseña: `Password12345`
- Base de datos: `BancoCHN`

---



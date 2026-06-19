# ProyectoMedico - Sistema de Microservicios

Integrantes: Apolo V. y Christian C.

## Descripcion

Sistema de backend medico basado en arquitectura de microservicios desarrollado con Spring Boot y Java 17. El sistema permite gestionar pacientes, productos, pedidos, pagos, envios, recetas, auditoria, inventario, proveedores y autenticacion.

## Microservicios

- ms-auth
- ms-pacientes
- ms-productos
- ms-inventario
- ms-pedidos
- ms-recetas
- ms-proveedores
- ms-pagos
- ms-envios
- ms-auditoria
- ms-gateway

## Uso

Abrir una terminal por cada microservicio y ejecutar:

```powershell
& "C:\maven\bin\mvn.cmd" test
```

Orden recomendado de inicio:
1. ms-auth
2. ms-pacientes
3. ms-productos
4. ms-inventario
5. ms-pedidos
6. ms-recetas
7. ms-proveedores
8. ms-pagos
9. ms-envios
10. ms-auditoria
11. ms-gateway

## Docker

En la raiz del proyecto:

```powershell
docker compose up --build
```

Esto levanta todos los ms y sus bd;

Servicios expuestos:
- Gateway: http://localhost:8080
- Auth: http://localhost:8081
- Productos: http://localhost:8082
- Pacientes: http://localhost:8083
- Inventario: http://localhost:8084
- Pedidos: http://localhost:8085
- Recetas: http://localhost:8086
- Proveedores: http://localhost:8087
- Pagos: http://localhost:8088
- Envíos: http://localhost:8089
- Auditoria: http://localhost:8090

## Rutas del Gateway

- /api/auth/** -> ms-auth
- /api/pacientes/** -> ms-pacientes
- /api/productos/** -> ms-productos
- /api/inventario/** -> ms-inventario
- /api/pedidos/** -> ms-pedidos
- /api/recetas/** -> ms-recetas
- /api/proveedores/** -> ms-proveedores
- /api/pagos/** -> ms-pagos
- /api/envios/** -> ms-envios
- /api/auditoria/** -> ms-auditoria

## Swagger / OpenAPI

Rutas Swagger;
- http://localhost:8081/swagger-ui/index.html
- http://localhost:8082/swagger-ui/index.html
- http://localhost:8083/swagger-ui/index.html
- http://localhost:8084/swagger-ui/index.html
- http://localhost:8085/swagger-ui/index.html
- http://localhost:8086/swagger-ui/index.html
- http://localhost:8087/swagger-ui/index.html
- http://localhost:8088/swagger-ui/index.html
- http://localhost:8089/swagger-ui/index.html
- http://localhost:8090/swagger-ui/index.html

## Funcionalidades implementadas

- CRUD completo en los 10 microservicios
- Autenticacion con JWT
- Validaciones con Bean Validation en todos los DTOs
- Manejo global de excepciones con GlobalExceptionHandler
- Comunicacion entre microservicios: ms-pedidos consulta ms-pacientes via WebClient
- Relacion OneToMany entre Paciente y Direccion
- Logs con Lombok en todas las capas
- Base de datos independiente por cada microservicio
- Configuracion con Docker
- Tests unitarios con JUnit y Mockito

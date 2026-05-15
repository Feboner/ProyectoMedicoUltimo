# ProyectoMedico - Sistema de Microservicios

Integrantes: Apolo V. y Christian C.

## Descripcion

Sistema de backend medico basado en arquitectura de microservicios desarrollado con Spring Boot y Java 17. El sistema permite gestionar pacientes, productos, pedidos, pagos y envios de insumos y medicamentos.

## Como ejecutar

Cada microservicio crea su base de datos automaticamente.

Abrir una terminal por cada microservicio y ejecutar:



en este orden:
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

## Funcionalidades implementadas

- CRUD completo en los 10 microservicios
- Autenticacion con JWT
- Validaciones con Bean Validation en todos los DTOs
- Manejo global de excepciones con GlobalExceptionHandler
- Comunicacion entre microservicios: ms-pedidos consulta ms-pacientes via WebClient
- Relacion OneToMany entre Paciente y Direccion
- Logs con Lombok en todas las capas
- Base de datos independiente por cada microservicio

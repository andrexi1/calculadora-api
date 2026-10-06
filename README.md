\# Calculadora API - Sistema Distribuido



Sistema distribuido con Docker Compose, PostgreSQL, NFS y 10 instancias de Spring Boot con balanceador nginx.



\## Arquitectura



\- \*\*Máquina A (10.35.115.96):\*\* Balanceador Nginx (puerto 8020)

\- \*\*Máquina B (10.172.14.70):\*\* PostgreSQL, NFS, 10 contenedores Spring Boot (puertos 8081-8090)



\## Levantar localmente



```bash

docker-compose up -d

```



\## Endpoints



\- `GET /api/personas/count` - Total de registros

\- `GET /api/personas/paginado?page=0\&pageSize=10` - Listar paginado

\- `POST /api/personas/actualizar` - Actualizar registro



\## CI/CD



GitHub Actions compila automáticamente en cada push a `main`.


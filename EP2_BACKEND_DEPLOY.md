# EP2 - CI/CD Backend (Despachos & Ventas)

## Qué hace
Se agregaron workflows para que al hacer push en la rama `deploy` se construyan y publiquen imágenes Docker en DockerHub:
- `ev2back-despachos` usando `back-Despachos_SpringBoot`
- `ev2back-ventas` usando `back-Ventas_SpringBoot`

## Requisitos
En GitHub Secrets del repo:
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

## Cómo probar
1. Confirma que existe la rama `deploy`.
2. Haz commit/push desde cualquiera de estas carpetas (Despachos o Ventas) hacia `deploy`.
3. Revisa GitHub Actions:
   - workflow “Build and Push to DockerHub (Despachos)”
   - workflow “Build and Push to DockerHub (Ventas)”


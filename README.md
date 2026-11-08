# Proyecto final de Programación

Aplicación de escritorio Java con interfaz gráfica, modelos y utilidades de correo, QR y cifrado.

## Configuración local

Define `ADMIN_EMAIL`, `ADMIN_PASSWORD`, `SMTP_USERNAME`, `SMTP_APP_PASSWORD` y `APP_ENCRYPTION_KEY` en el entorno antes de ejecutar. La clave de cifrado debe ser Base64 y representar 16, 24 o 32 bytes. No guardes credenciales en el código ni en Git.

El cifrado de la copia preparada usa AES-GCM. Los datos cifrados con la versión académica anterior no son compatibles con este formato.

## Abrir

Importa el proyecto en Eclipse. Las bibliotecas de correo y QR están en `libs/`.
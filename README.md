LINK al repositorio: https://github.com/dxn1l/HistorialMedico.git


# Historial Médico

## Descripción

**Historial Médico** es una aplicación móvil que permite gestionar los registros médicos de los usuarios. A través de esta aplicación, los usuarios pueden agregar, ver y editar su historial médico de forma sencilla y segura. La aplicación también permite gestionar información como enfermedades, cirugías previas, medicamentos, alergias, entre otros.

## Características

- **Inicio de sesión y registro**: Los usuarios pueden crear una cuenta o iniciar sesión utilizando su correo electrónico y contraseña mediante Firebase Authentication.
- **Agregar historial médico**: Los usuarios pueden registrar su historial médico, incluyendo información personal y médica como enfermedades, cirugías, medicamentos, etc.
- **Ver historial médico**: Los usuarios pueden consultar sus historiales médicos previamente guardados.
- **Edición de historial**: Los usuarios pueden editar los historiales médicos ya guardados.
- **Validación de datos**: Si el historial médico ya existe, la aplicación no permitirá agregarlo nuevamente y mostrará un mensaje de error.

## Tecnologías utilizadas

- **Jetpack Compose**: Para construir la interfaz de usuario de forma declarativa.
- **Firebase**: Para el manejo de autenticación de usuarios y almacenamiento de datos en tiempo real.
- **Kotlin**: El lenguaje de programación utilizado para desarrollar la aplicación.
- **Navigation Compose**: Para la navegación entre pantallas de forma sencilla y eficiente.
- **Material 3**: Para el diseño y los componentes visuales de la aplicación.

## Estructura de la aplicación

1. **Pantallas**:
   - **LoginScreen**: Pantalla de inicio de sesión y registro de usuarios.
   - **MenuScreen**: Pantalla principal donde el usuario puede elegir entre agregar un historial médico o ver el historial.
   - **AddHistorialScreen**: Pantalla para agregar un nuevo historial médico.
   - **ViewHistorialScreen**: Pantalla para visualizar los historiales médicos guardados.
   - **EditHistorialScreen**: Pantalla para editar un historial médico existente.

2. **Firebase**:
   - **Firebase Authentication**: Para el inicio de sesión y registro de usuarios.
   - **Firebase Firestore**: Para almacenar los historiales médicos de los usuarios.

## Instalación

Para instalar y ejecutar esta aplicación, sigue los pasos a continuación:

### Requisitos previos

- **Android Studio**: Asegúrate de tener instalada la última versión de Android Studio.
- **Firebase Project**: Crea un proyecto en [Firebase Console](https://console.firebase.google.com/) y configura la autenticación y Firestore.

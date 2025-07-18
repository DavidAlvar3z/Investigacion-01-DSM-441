# Investigacion-01-DSM-441

Este es un proyecto Android sencillo desarrollado como parte de la materia DSM-441. La aplicación permite gestionar tareas personales con una interfaz amigable. Entre sus funciones básicas se incluyen:

- Agregar nuevas tareas
- Marcar tareas como completadas
- Visualizar tareas activas y completadas con estilos diferenciados
- Cancelar una edición en proceso
- Eliminar tareas
- Persistencia de datos local

> **Nota:** El desarrollo se encuentra en la rama `proyecto`. Asegúrate de cambiar a esta rama al clonar el repositorio.

---

## Tecnologías utilizadas

- **Lenguaje:** Kotlin
- **Entorno de desarrollo:** Android Studio
- **Diseño de interfaz:** XML
- **Almacenamiento local:** SharedPreferences

---

## Clonación del repositorio

Puedes clonar el proyecto ejecutando:

```bash
git clone https://github.com/DavidAlvar3z/Investigacion-01-DSM-441.git
cd Investigacion-01-DSM-441
git checkout proyecto
```

---

## Estructura del proyecto

| Archivo/Carpeta             | Descripción                                         |
|----------------------------|-----------------------------------------------------|
| `app/src/main/java/...`    | Código fuente en Kotlin                             |
| `app/src/main/res/layout`  | Archivos de diseño XML para las interfaces de usuario |
| `app/src/main/res/values`  | Archivos de recursos como strings y colores         |
| `app/src/main/AndroidManifest.xml` | Configuración principal de la app         |
| `MainActivity.kt`          | Actividad principal que gestiona la lógica de tareas|

---

## Cómo usar la aplicación

1. Abre el proyecto en Android Studio.
2. Conecta un emulador o un dispositivo Android.
3. Ejecuta la aplicación (`Run > Run app`).
4. En la pantalla principal:
   - Escribe una tarea en el campo de texto.
   - Pulsa "Agregar tarea" para incluirla en la lista.
   - Marca como completada usando la casilla de verificación (una vez marcada no se puede desmarcar).
   - Tareas completadas se mostrarán en color gris con una apariencia desactivada.
   - Las tareas activas permiten edición; las completadas solo se pueden eliminar.
   - Si estás editando una tarea, puedes cancelar la edición con el botón "Cancelar edición".

---

| Requisito         | Detalle                                |
|-------------------|----------------------------------------|
| Android mínimo     | Android 7.0 Nougat (API nivel 24)      |
| IDE                | Android Studio Hedgehog o superior     |
| Lenguaje           | Kotlin                                 |
| Sistema de persistencia | SharedPreferences                  |

---

## Estado del proyecto

Este proyecto está activo para prácticas educativas. No se recomienda su uso en producción sin modificaciones y validaciones adicionales.

3. Construir y ejecutar la aplicación en un emulador o dispositivo físico con Android 5.0 (Lollipop) o superior.

4. Interactuar con la aplicación para agregar, completar, editar y eliminar tareas.

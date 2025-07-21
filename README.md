Aquí tienes el README completo listo para copiar y pegar en formato de consola:

```markdown
# 📱 ToDoOrbit - Gestor de Tareas Android

![App Screenshot](https://via.placeholder.com/300x600?text=ToDoOrbit+Screenshot)  
*Aplicación de gestión de tareas con persistencia local y diseño moderno*

## 📌 Descripción

ToDoOrbit es una aplicación Android para gestión de tareas personales desarrollada en Kotlin como proyecto educativo para la materia DSM-441. Ofrece:

- ✅ Creación y edición de tareas
- 🎨 Interfaz intuitiva con animaciones
- 💾 Persistencia de datos usando SharedPreferences y JSON
- ⏱ Pantalla de inicio (Splash Screen) animada
- 📅 Soporte para fechas y categorías (en desarrollo)

## 🛠 Tecnologías utilizadas

| Categoría         | Tecnologías                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| **Lenguaje**      | Kotlin 100%                                                                 |
| **Persistencia**  | SharedPreferences + GSON (Serialización JSON)                               |
| **UI/UX**        | XML layouts, CardView, Animaciones, Material Design                        |
| **Arquitectura**  | MVC (Model-View-Controller)                                                |
| **Dependencias**  | [GSON](https://github.com/google/gson) para serialización                  |

## 🚀 Cómo comenzar

### Prerrequisitos
- Android Studio Hedgehog o superior
- Dispositivo/emulador con Android 7.0+ (API 24)

### Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/DavidAlvar3z/Investigacion-01-DSM-441.git
   cd Investigacion-01-DSM-441
   git checkout proyecto
   ```
2. Abrir en Android Studio
3. Ejecutar en emulador/dispositivo (⌘ + R / Ctrl + R)

## 🏗 Estructura del proyecto

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/investigacion01_ejerciciosoloxml/
│   │   │   ├── MainActivity.kt         # Lógica principal
│   │   │   ├── SplashActivity.kt       # Pantalla de inicio animada
│   │   │   ├── Tarea.kt                # Modelo de datos
│   │   │   └── TareaAdapter.kt         # Adaptador personalizado
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml   # Vista principal
│   │   │   │   ├── activity_splash.xml # Vista splash
│   │   │   │   └── item_tarea.xml      # Item de lista personalizado
│   │   │   └── anim/                   # Animaciones
│   │   └── AndroidManifest.xml
```

## 🎨 Características clave

### Funcionalidades implementadas
- **CRUD completo** de tareas
- **Persistencia local** usando SharedPreferences + JSON
- **Diseño responsive** con CardView y estilos personalizados
- **Splash Screen** con animaciones de rebote y fade-in
- **Validaciones** para evitar tareas vacías
- **Diferenciación visual** entre tareas completadas/activas

### Ejemplo de código (Persistencia)
```kotlin
private fun guardarTareas() {
    val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    val editor = prefs.edit()
    val gson = Gson()
    val json = gson.toJson(tareas)
    editor.putString(tareasKey, json)
    editor.apply()
}
```

## 📝 Roadmap y mejoras pendientes

| Mejora                     | Estado      | Prioridad |
|----------------------------|-------------|-----------|
| Implementar categorías      | Pendiente   | Alta      |
| Añadir fechas de vencimiento| En progreso | Media     |
| Mejorar marcado de tareas   | Pendiente   | Baja      |
| Sincronización con backend  | Futuro      | Baja      |

## 🤔 ¿Cómo contribuir?

1. Haz fork del proyecto
2. Crea una rama (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Haz push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Distribuido bajo la licencia MIT. Ver `LICENSE` para más información.

## ✒️ Autores

- **Ashley Gabriela Valdez Gonzalez** (VG240979)
- **Caleb Alejandro Peñate Deras** (PD230166)  
- **David Alejandro Alvarez Moreira** - [@DavidAlvar3z](https://github.com/DavidAlvar3z) (AM240104)
- **Waldo José Pérez Aguillon** (PA230265)
- **Camila Elizabeth Castillo Joya (PD230166)

---

> **Nota educativa**: Este proyecto fue desarrollado con fines académicos. No está recomendado para uso en producción sin las debidas revisiones de seguridad y optimización.
```

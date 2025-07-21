package com.example.investigacion01_ejerciciosoloxml

// Modelo de datos para una tarea
data class Tarea(
    var descripcion: String,
    var completada: Boolean = false,
    var fechaVencimiento: String = "",
    var categoria: String = ""
)


package com.example.investigacion01_ejerciciosoloxml

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var buttonDeleteTask: Button
    private lateinit var listViewTasks: ListView
    private lateinit var buttonCancelEdit: Button

    private lateinit var tareas: ArrayList<Tarea>
    private lateinit var adapter: TareaAdapter
    private var tareaEnEdicionIndex: Int? = null

    companion object {
        private const val prefsName = "MisTareasPrefs"
        private const val tareasKey ="tareas"
        }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        buttonDeleteTask = findViewById(R.id.buttonDeleteTask)
        listViewTasks = findViewById(R.id.listViewTasks)
        buttonCancelEdit = findViewById(R.id.buttonCancelEdit)

       // Cargar las tareas guardadas
        tareas = cargarTareas()

        adapter = TareaAdapter(this, tareas)
        listViewTasks.adapter = adapter

        buttonDeleteTask.visibility = View.GONE

        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            val tarea = tareas[position]
            tarea.completada = !tarea.completada
            guardarTareas()
            adapter.notifyDataSetChanged()
        }

        // Mostrar opciones con click largo
        listViewTasks.setOnItemLongClickListener { _, view, position, _ ->
            val tarea = tareas[position]

            val popup = PopupMenu(this, view)
            if (!tarea.completada) {
                popup.menu.add("Editar")
            }
            popup.menu.add("Eliminar")

            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "Editar" -> {
                        editTextTask.setText(tarea.descripcion)
                        tareaEnEdicionIndex = position
                        buttonAddTask.text = "Actualizar Tarea"
                        buttonCancelEdit.visibility = View.VISIBLE
                    }
                    "Eliminar" -> {
                        tareas.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }
                }
                true
            }

            popup.show()
            true
        }

        buttonAddTask.setOnClickListener {
            val texto = editTextTask.text.toString().trim()
            if (texto.isNotEmpty()) {
                if (tareaEnEdicionIndex != null) {
                    tareas[tareaEnEdicionIndex!!].descripcion = texto
                    tareaEnEdicionIndex = null
                    buttonAddTask.text = "Agregar Tarea"
                    buttonCancelEdit.visibility = View.GONE
                } else {
                    tareas.add(Tarea(texto, false))
                }
                editTextTask.text.clear()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Escribe una tarea", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancelEdit.setOnClickListener {
            editTextTask.text.clear()
            tareaEnEdicionIndex = null
            buttonAddTask.text = "Agregar Tarea"
            buttonCancelEdit.visibility = View.GONE
        }
    }

           
    // Guardar tareas en SharedPreferences
        private fun guardarTareas() {
            val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(tareas)
            editor.putString(tareasKey, json)
            editor.apply()
        }

        // 📦 Cargar tareas desde SharedPreferences
    private fun cargarTareas(): ArrayList<Tarea> {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val json = prefs.getString(tareasKey, null)
        val gson = Gson()

        val type = object : TypeToken<ArrayList<Tarea>>() {}.type
        val listaCargada: ArrayList<Tarea>? = if (json != null) gson.fromJson(json, type) else null

        //TAREAS AÑADIDAS CON JSON * SHAREDPREFERENCES (GUARDAR LOCALMENTE LAS TAREAS)

        return if (listaCargada != null && listaCargada.isNotEmpty()) {
            listaCargada
        } else {
            // Primer uso o lista vacía → crear las tareas por defecto
            val tareasIniciales = arrayListOf(
                Tarea("Estudiar para el examen", false),
                Tarea("Lavar la ropa", true),
                Tarea("Hacer tarea de Android", false),
                Tarea("Implementar JSON + sharedpreferences", false),


                //TAREA AGREGADA Y PUNTOS PUNTOS QUE SE TIENEN QUE MEJORAR

                Tarea("Revisar la función de marcado como listo", false),

                //1- Se puede marcar una tarea como tachada pero no se marca el botón (mejora adicional)

                Tarea("Establecer fechas de vencimiento", false)

                //2- Establecer lo de las fechas de vencimiento o agrupar por categorias,
                //pienso es más fácil que lo de por categorias :)



                //DATO: tareas que se agregan en código, no se muestran en la app al volverla a correr ya que
                //al cargar la aplicación se guardan solo las que lleve el código, luego, solo se van a guardar
                //localmente las tareas que se añadan desde la aplicación al igual de como va marcada :) -Caleb

            )
            // Guardarlas para que no desaparezcan luego
            tareas = tareasIniciales
            guardarTareas()
            tareasIniciales
        }
    }
}

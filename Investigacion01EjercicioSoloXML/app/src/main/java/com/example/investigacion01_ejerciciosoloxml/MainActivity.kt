package com.example.investigacion01_ejerciciosoloxml

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

    private lateinit var tareas: ArrayList<Tarea>
    private lateinit var adapter: TareaAdapter
    private var tareaEnEdicionIndex: Int? = null
    private lateinit var buttonCancelEdit: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        buttonDeleteTask = findViewById(R.id.buttonDeleteTask)
        listViewTasks = findViewById(R.id.listViewTasks)
        buttonCancelEdit = findViewById(R.id.buttonCancelEdit)

        tareas = arrayListOf(
            Tarea("Estudiar para el examen", false),
            Tarea("Lavar la ropa", true),
            Tarea("Hacer tarea de Android", false)
        )

        adapter = TareaAdapter(this, tareas)
        listViewTasks.adapter = adapter

        // Ocultar botón eliminar por defecto
        buttonDeleteTask.visibility = View.GONE

        // Marcar como completada con click corto
        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            val tarea = tareas[position]
            tarea.completada = !tarea.completada
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
}

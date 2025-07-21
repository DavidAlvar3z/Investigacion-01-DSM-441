package com.example.investigacion01_ejerciciosoloxml

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var editTextFecha: EditText
    private lateinit var spinnerCategoria: Spinner
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView
    private lateinit var buttonCancelEdit: Button

    private lateinit var tareas: ArrayList<Tarea>
    private lateinit var adapter: TareaAdapter
    private var tareaEnEdicionIndex: Int? = null

    companion object {
        private const val prefsName = "MisTareasPrefs"
        private const val tareasKey = "tareas"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVistas()

        val categorias = arrayOf("Sin categoría", "Personal", "Trabajo", "Estudio")
        val adapterCategorias = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)
        spinnerCategoria.adapter = adapterCategorias

        tareas = cargarTareas()
        adapter = TareaAdapter(this, tareas,
            onEditarClick = { position ->
                val tarea = tareas[position]
                editTextTask.setText(tarea.descripcion)
                editTextFecha.setText(tarea.fechaVencimiento)
                val indexCategoria = adapterCategorias.getPosition(tarea.categoria)
                spinnerCategoria.setSelection(if (indexCategoria >= 0) indexCategoria else 0)

                tareaEnEdicionIndex = position
                buttonAddTask.text = "Actualizar Tarea"
                buttonCancelEdit.visibility = View.VISIBLE
            },
            onEliminarClick = { position ->
                tareas.removeAt(position)
                guardarTareas()
                adapter.notifyDataSetChanged()
            }
        )
        listViewTasks.adapter = adapter

        configurarDatePicker()
        configurarBotones()
    }

    private fun inicializarVistas() {
        editTextTask = findViewById(R.id.editTextTask)
        editTextFecha = findViewById(R.id.editTextFecha)
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)
        buttonCancelEdit = findViewById(R.id.buttonCancelEdit)
    }

    private fun configurarDatePicker() {
        editTextFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                    editTextFecha.setText(fechaSeleccionada)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()
        }
    }

    private fun configurarBotones() {
        buttonAddTask.setOnClickListener {
            val descripcion = editTextTask.text.toString().trim()
            val fecha = editTextFecha.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem?.toString() ?: ""

            if (descripcion.isEmpty()) {
                Toast.makeText(this, "La descripción no puede estar vacía", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (fecha.isEmpty()) {
                Toast.makeText(this, "Selecciona una fecha de vencimiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!validarFecha(fecha)) {
                Toast.makeText(this, "Fecha inválida o anterior a hoy. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tareaEnEdicionIndex != null) {
                val tarea = tareas[tareaEnEdicionIndex!!]
                tarea.descripcion = descripcion
                tarea.fechaVencimiento = fecha
                tarea.categoria = categoria
                tareaEnEdicionIndex = null
                buttonAddTask.text = "Agregar Tarea"
                buttonCancelEdit.visibility = View.GONE
            } else {
                tareas.add(Tarea(descripcion, false, fecha, categoria))
            }

            limpiarCampos()
            guardarTareas()
            adapter.notifyDataSetChanged()
        }

        buttonCancelEdit.setOnClickListener {
            limpiarCampos()
            tareaEnEdicionIndex = null
            buttonAddTask.text = "Agregar Tarea"
            buttonCancelEdit.visibility = View.GONE
        }
    }

    private fun validarFecha(fecha: String): Boolean {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formatoFecha.isLenient = false

        val fechaHoy = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        return try {
            val fechaIngresada = formatoFecha.parse(fecha) ?: return false
            !fechaIngresada.before(fechaHoy)
        } catch (e: Exception) {
            false
        }
    }

    private fun limpiarCampos() {
        editTextTask.text.clear()
        editTextFecha.text.clear()
        spinnerCategoria.setSelection(0)
    }

    private fun guardarTareas() {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(tareas)
        editor.putString(tareasKey, json)
        editor.apply()
    }

    private fun cargarTareas(): ArrayList<Tarea> {
        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val json = prefs.getString(tareasKey, null)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Tarea>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

}
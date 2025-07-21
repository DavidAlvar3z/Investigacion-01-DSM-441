package com.example.investigacion01_ejerciciosoloxml

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class TareaAdapter(
    context: Context,
    private val tareas: List<Tarea>,
    private val onEditarClick: (position: Int) -> Unit,
    private val onEliminarClick: (position: Int) -> Unit
) : ArrayAdapter<Tarea>(context, 0, tareas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val tarea = tareas[position]
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_tarea, parent, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBoxCompleta)
        val textViewDescripcion = view.findViewById<TextView>(R.id.textViewDescripcion)
        val textViewFecha = view.findViewById<TextView>(R.id.textViewFecha)
        val textViewCategoria = view.findViewById<TextView>(R.id.textViewCategoria)
        val buttonEditar = view.findViewById<ImageButton>(R.id.buttonEditar)
        val buttonEliminar = view.findViewById<ImageButton>(R.id.buttonEliminar)

        textViewDescripcion.text = tarea.descripcion
        textViewFecha.text = "Vence: ${if (tarea.fechaVencimiento.isEmpty()) "Sin fecha" else tarea.fechaVencimiento}"
        textViewCategoria.text = "Categoría: ${if (tarea.categoria.isEmpty()) "Sin categoría" else tarea.categoria}"

        if (tarea.completada) {
            textViewDescripcion.setTextColor(Color.GRAY)
            textViewDescripcion.paintFlags = textViewDescripcion.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textViewDescripcion.setTextColor(Color.BLACK)
            textViewDescripcion.paintFlags = textViewDescripcion.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = tarea.completada

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && !tarea.completada) {
                tarea.completada = true
                notifyDataSetChanged()
            } else if (!isChecked && tarea.completada) {
                buttonView.isChecked = true
            }
        }

        buttonEditar.setOnClickListener {
            onEditarClick(position)
        }

        buttonEliminar.setOnClickListener {
            onEliminarClick(position)
        }

        return view
    }
}
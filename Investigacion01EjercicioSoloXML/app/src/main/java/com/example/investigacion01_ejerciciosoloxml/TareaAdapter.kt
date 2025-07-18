package com.example.investigacion01_ejerciciosoloxml

import android.content.Context
import androidx.core.widget.CompoundButtonCompat
import android.content.res.ColorStateList
import android.widget.CompoundButton
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class TareaAdapter(context: Context, private val tareas: List<Tarea>) :
    ArrayAdapter<Tarea>(context, 0, tareas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val tarea = tareas[position]
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_tarea, parent, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBoxCompleta)
        val textView = view.findViewById<TextView>(R.id.textViewDescripcion)

        textView.text = tarea.descripcion

        // Quitar listener antes de cambiar estado para evitar loops
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = tarea.completada

        // Cambiar estilo del texto según completada o no
        if (tarea.completada) {
            textView.setTextColor(Color.GRAY)
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.setTextColor(Color.BLACK)
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        // Listener para controlar que no se pueda desmarcar una tarea completada
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && !tarea.completada) {
                // Marcar tarea como completada
                tarea.completada = true
                notifyDataSetChanged()
            } else if (!isChecked && tarea.completada) {
                // Evitar que se desmarque la tarea completada, revertir el cambio
                buttonView.isChecked = true
            }
        }

        return view
    }
}
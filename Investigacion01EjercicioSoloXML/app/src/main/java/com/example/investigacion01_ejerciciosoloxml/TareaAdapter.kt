package com.example.investigacion01_ejerciciosoloxml

import android.content.Context
import android.content.res.ColorStateList
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
        checkBox.isChecked = tarea.completada

        // Cambiar colores segun completada o no
        if (tarea.completada) {
            textView.setTextColor(Color.GRAY)
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            // Checkbox verde
            checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50")) // verde
        } else {
            textView.setTextColor(Color.BLACK)
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            // Checkbox rojo
            checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#F44336")) // rojo
        }

        checkBox.setOnCheckedChangeListener(null)

        // Solo permitir marcar, no desmarcar
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !tarea.completada) {
                tarea.completada = true
                notifyDataSetChanged()
            } else if (!isChecked && tarea.completada) {
                // Evitar desmarcar tarea completada
                checkBox.isChecked = true
            }
        }

        return view
    }
}
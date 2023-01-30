package com.example.practicaarrayadapter.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.practicaarrayadapter.R
import com.example.practicaarrayadapter.model.Persona

class PersonaAdapter(private val mContext: Context, private var listaPersona:List<Persona>) : ArrayAdapter<Persona>(mContext,
    R.layout.item_persona,listaPersona){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mContext)
        val convertView = inflater.inflate(R.layout.item_persona, null)
        val txtId = convertView.findViewById<TextView>(R.id.txtId)
        val txtNombre = convertView.findViewById<TextView>(R.id.txtNombre)
        val txtApellido = convertView.findViewById<TextView>(R.id.txtApellido)

        txtId.text=listaPersona[position]._id.toString()
        txtNombre.text=listaPersona[position].nombre
        txtApellido.text=listaPersona[position].apellido

        return convertView
    }
}
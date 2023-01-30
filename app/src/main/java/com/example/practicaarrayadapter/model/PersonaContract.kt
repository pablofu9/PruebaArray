package com.example.practicaarrayadapter.model

import android.provider.BaseColumns

object PersonaContract: BaseColumns {
    const val TABLE_NAME="persona"
    const val ID="_id"
    const val NOMBRE="nombre"
    const val APELLIDO="apellido"
    const val EDAD="edad"
    const val ALTURA="altura"
}
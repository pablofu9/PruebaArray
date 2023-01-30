package com.example.practicaarrayadapter.controller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practicaarrayadapter.model.Persona
import com.example.practicaarrayadapter.model.PersonaContract


class SqliteHelper(context:Context?) : SQLiteOpenHelper(context,NAME,null,VERSION) {
    companion object{
        private const val NAME="personas.db"
        private const val VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE "+
                    PersonaContract.TABLE_NAME + " ( "
                    +PersonaContract.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +PersonaContract.NOMBRE + " TEXT, "
                    +PersonaContract.APELLIDO + " TEXT, "
                    +PersonaContract.ALTURA + " INTEGER, "
                    +PersonaContract.EDAD +  " INTEGER);"
        )
        db?.execSQL(
            "INSERT INTO " + PersonaContract.TABLE_NAME + " (nombre,apellido,altura,edad) VALUES" +
                    "('Pedro','Lopez', 178, 22)," +
                    "('Maria','Perez', 150, 33)," +
                    "('Juan','Agundez', 190, 18)," +
                    "('Marta','Viña', 163, 26);"

        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun leer():Cursor{
        val db = readableDatabase
        val sql = "SELECT * FROM "+PersonaContract.TABLE_NAME
        return db.rawQuery(sql, null)
    }

    fun insertar(p:Persona){
            val db=writableDatabase
            val values = ContentValues()
            values.put(PersonaContract.NOMBRE,p.nombre)
            values.put(PersonaContract.APELLIDO,p.apellido)
            values.put(PersonaContract.ALTURA,p.altura)
            values.put(PersonaContract.EDAD,p.edad)
            db.insert(PersonaContract.TABLE_NAME,null,values)

    }

    fun leerEdad(e1:Int, e2:Int):Cursor{
        val db = readableDatabase
        val sql="SELECT * FROM "+PersonaContract.TABLE_NAME+" WHERE "+PersonaContract.EDAD+" >'"+e1+"'"+" AND "+PersonaContract.EDAD+" <'"+e2+"'"
        return db.rawQuery(sql,null)
    }
    fun eliminar(p:Persona){
        val db = writableDatabase
        val sql = "DELETE FROM "+PersonaContract.TABLE_NAME+" WHERE "+PersonaContract.ID+" ='"+p._id+"'"
        db.execSQL(sql)
    }

    fun modificar(p:Persona){
        val db = writableDatabase
        val values = ContentValues()
        values.put(PersonaContract.NOMBRE, p.nombre)
        values.put(PersonaContract.APELLIDO, p.apellido)
        values.put(PersonaContract.ALTURA, p.altura)
        values.put(PersonaContract.EDAD, p.edad)
        db.update(PersonaContract.TABLE_NAME, values, PersonaContract.ID+" =?", arrayOf(p._id.toString()))
    }
}
package com.example.practicaarrayadapter.activities

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import com.example.practicaarrayadapter.R
import com.example.practicaarrayadapter.controller.PersonaAdapter
import com.example.practicaarrayadapter.controller.SqliteHelper
import com.example.practicaarrayadapter.model.Persona
import com.example.practicaarrayadapter.model.PersonaContract

class FiltroEdad : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    private lateinit var edadMin: SeekBar
    private lateinit var edadMax: SeekBar
    private lateinit var txtEdadMin: TextView
    private lateinit var txtEdadMax: TextView
    private lateinit var listaEdad : ListView
    private lateinit var arrayList : ArrayList<Persona>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtro_edad)
        edadMin=findViewById(R.id.edadMin)
        edadMax=findViewById(R.id.edadMax)
        txtEdadMax=findViewById(R.id.txtEdadMax)
        txtEdadMin=findViewById(R.id.txtEdadMin)
        listaEdad=findViewById(R.id.listaEdad)

        edadMax.setOnSeekBarChangeListener(this)
        edadMin.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        txtEdadMin.text=edadMin.progress.toString()
        txtEdadMax.text=edadMax.progress.toString()

        cargar(edadMin.progress,edadMax.progress)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
    fun cargar(e1:Int, e2:Int){
        val helper=SqliteHelper(this)
        val c:Cursor=helper.leerEdad(e1,e2)
        arrayList= ArrayList<Persona>()
        while (c.moveToNext()){
            arrayList.add(
                Persona(
                    c.getInt(c.getColumnIndexOrThrow(PersonaContract.ID)),
                    c.getString(c.getColumnIndexOrThrow(PersonaContract.NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(PersonaContract.APELLIDO)),
                    c.getInt(c.getColumnIndexOrThrow(PersonaContract.ALTURA)),
                    c.getInt(c.getColumnIndexOrThrow(PersonaContract.EDAD))
                )
            )
        }
        c.close()
        val adapter = PersonaAdapter(this, arrayList)
        listaEdad.adapter=adapter
    }
}
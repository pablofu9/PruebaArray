package com.example.practicaarrayadapter.activities

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import com.example.practicaarrayadapter.R
import com.example.practicaarrayadapter.controller.PersonaAdapter
import com.example.practicaarrayadapter.controller.SqliteHelper
import com.example.practicaarrayadapter.model.Persona
import com.example.practicaarrayadapter.model.PersonaContract

class Ver : AppCompatActivity() {
    private lateinit var lista:ListView
    private lateinit var arrayList: ArrayList<Persona>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver)
        lista=findViewById(R.id.lista)
        onResume()

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo
        val helper= SqliteHelper(this)
        when(item.itemId){
            R.id.menuEliminar->{
                val p  = lista.adapter.getItem(info.position) as Persona
                helper.eliminar(p)
                onResume()
            }
            R.id.menuModificar->{
                val p = lista.adapter.getItem(info.position) as Persona
                var intent = Intent(this, Insertar::class.java)

                intent.putExtra("p",p)
                startActivity(intent)
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        arrayList=ArrayList<Persona>()
        val helper=SqliteHelper(this)
        val c : Cursor=helper.leer()
        registerForContextMenu(lista)
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
        lista.adapter=adapter
    }
}
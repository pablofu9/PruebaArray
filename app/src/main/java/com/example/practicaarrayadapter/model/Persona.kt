package com.example.practicaarrayadapter.model
class Persona: java.io.Serializable {
    var _id:Int
    var nombre:String
    var apellido:String
    var altura:Int
    var edad:Int

    constructor(nombre: String, apellido: String, altura: Int, edad: Int) {
        this._id=0
        this.nombre = nombre
        this.apellido = apellido
        this.altura = altura
        this.edad = edad
    }

    constructor(_id: Int, nombre: String, apellido: String, altura: Int, edad: Int) {
        this._id=_id
        this.nombre = nombre
        this.apellido = apellido
        this.altura = altura
        this.edad = edad
    }
    constructor(){
        _id=0
        nombre=""
        apellido=""
        altura=0
        edad=0
    }

    override fun toString(): String {
        return "com.example.practicacursoradapter.model.com.example.practicaarrayadapter.model.Persona(id=$_id, nombre='$nombre', apellido='$apellido', altura=$altura, edad=$edad)"
    }


}
package com.example.kotlincompose.usuarios

data class Usuario(var id:Int, var nombre: String, var contrasena: String){
    constructor() :this(0,"","")
}
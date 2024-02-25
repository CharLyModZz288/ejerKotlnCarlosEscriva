package com.example.kotlincompose.usuarios

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kotlincompose.usuarios.Usuario


class MiBDOpenHelper(contex: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(contex, DATABASE_NAME, factory, DATABASE_VERSION) {

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "usuarios.db"
        val T_USUARIOS = "usuarios"
        val USUARIO_INSERCION_ID = "id_insercion_usuario"
        val USUARIO_ID = "id_usuario"
        val USUARIO_NOMBRE = "nombre"
        val USUARIO_CONTRASENA = "contrasena"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        var crearTablaUsuarios = "CREATE TABLE $T_USUARIOS " +
                "($USUARIO_INSERCION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USUARIO_ID INTEGER, " +
                "$USUARIO_NOMBRE TEXT," +
                "$USUARIO_CONTRASENA TEXT)"
        var insercion_usuario_prueba =
            "INSERT INTO $T_USUARIOS ($USUARIO_ID,$USUARIO_NOMBRE,$USUARIO_CONTRASENA) " +
                    "VALUES (0,'Prueba','Hola');"
        db!!.execSQL(crearTablaUsuarios)
        db!!.execSQL(insercion_usuario_prueba)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("$TAG (onUpgrade)", "Pendiente de realizar")
    }


    fun anadirUsuario(u: Usuario) {
        val db = this.writableDatabase
        val data = ContentValues()
        data.put(USUARIO_ID, u.id)
        data.put(USUARIO_NOMBRE, u.nombre)
        data.put(USUARIO_CONTRASENA, u.contrasena)

        db.insert(T_USUARIOS, null, data)
        db.close()
    }
    fun comprobarUsuario(us: Usuario): Boolean {
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM $T_USUARIOS", null)
        var existe = false
        try {
            if (cursor.moveToFirst()) {
                do {
                    var u = Usuario()
                    u.id = cursor.getInt(1)
                    u.nombre = cursor.getString(2)
                    u.contrasena= cursor.getString(3)
                    if(us.nombre==u.nombre){
                        if(us.contrasena==u.contrasena){
                            existe = true
                        }
                    }
                    else{
                        existe = false
                    }
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error comprobando el usuario")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return existe
    }


}
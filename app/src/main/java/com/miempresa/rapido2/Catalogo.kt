package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_catalogo.*

class Catalogo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);


        //obtenemos lista de datos guardados en sugarORM
        var usuariorepo = ProductoRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorProducto(listaUsuarios as ArrayList<Producto>)
        lista.adapter = adapter


        //El boton Agregar envia la actividad a la interfaz RegistroUsuarios
        btnAgregar.setOnClickListener {
            val lanzar = Intent(this, Registro_producto::class.java)
            startActivity(lanzar)
        }


    }

    override fun onRestart() {
        super.onRestart()
        var usuariorepo = ProductoRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorProducto(listaUsuarios as ArrayList<Producto>)
        lista.adapter = adapter
    }
}

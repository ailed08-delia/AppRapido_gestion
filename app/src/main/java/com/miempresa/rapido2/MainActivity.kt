package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Boton vendedor
        val botonRegistrarse = findViewById<Button>(R.id.btnSoy_vendedor)
        botonRegistrarse.setOnClickListener{
            val lanzar = Intent(this,Inicio_vendedor::class.java)
            startActivity(lanzar)
        }

        //Boton cliente
        val botonIniciarSesion = findViewById<Button>(R.id.btnSoy_cliente)
        botonIniciarSesion.setOnClickListener{
            val lanzar = Intent(this,Auxiliar::class.java)
            startActivity(lanzar)
        }
    }
}


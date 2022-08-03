package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Inicio_vendedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_vendedor)
        //Boton crear cuenta vendedor
        val botonVendedor = findViewById<Button>(R.id.btnCrear_cuenta_vendedor)
        botonVendedor.setOnClickListener{
            val lanzar = Intent(this,Registro_vendedor::class.java)
            startActivity(lanzar)
        }

        //Boton iniciar Sesion
        val botonIniciarSesion = findViewById<Button>(R.id.btnIniciar_sesion)
        botonIniciarSesion.setOnClickListener{
            val lanzar = Intent(this,Iniciar_sesion::class.java)
            startActivity(lanzar)
        }
    }
}
package com.miempresa.rapido2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro_producto.*

class Registro_producto : AppCompatActivity() {
    var edita: Boolean = false
    var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_producto)

        btnRegistrarProducto2.setOnClickListener() {
            var producto = edtProducto2.text.toString()
            var precio = edtPrecio2.text.toString()
            var disponible= edtDisponible2.text.toString()
            var observaciones = edtObservacion2.text.toString()

            if (producto.isEmpty() || precio.isEmpty() || disponible.isEmpty() || observaciones.isEmpty()) {
                Toast.makeText(this, "Algunos campos están vacíos", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }
            if (edita) {
                var usuariorepo = ProductoRepositorio()
                usuariorepo.actualizar(id, producto, precio, disponible,observaciones)
            } else {
                var usuariorepo = ProductoRepositorio()
                usuariorepo.crear(producto, precio, disponible,observaciones)
                finish()

                //var datoGuardado = usuariorepo.listar().size
                //Toast.makeText(this, "Datos guardados:\n"+datoGuardado,Toast.LENGTH_LONG).show()
            }
        }

        var recibidos: Bundle? = intent.extras
        if (recibidos != null) {
            var usuario = recibidos?.get("producto") as Producto
            edita = true
            id = usuario.id!!
            edtProducto2.setText(usuario.producto)
            edtPrecio2.setText(usuario.precio)
            edtDisponible2.setText(usuario.disponible)
            edtObservacion2.setText(usuario.observaciones)
        }
    }
}
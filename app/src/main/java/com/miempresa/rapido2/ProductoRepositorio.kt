package com.miempresa.rapido2

import com.orm.SugarApp
import com.orm.SugarRecord

class ProductoRepositorio{
    fun crear(producto:String,precio:String,disponible:String,observaciones:String){
        var usuario = Producto(producto,precio,disponible,observaciones)
        SugarRecord.save(usuario)
    }

    fun listar():List<Producto>{
        var usuarios = SugarRecord.listAll(Producto::class.java)
        return usuarios
    }

    //funcion para eliminar
    fun borrar(id:Long){
        var usuario:Producto = SugarRecord.findById(Producto::class.java,id)
        SugarRecord.delete(usuario)
    }

    //función que permite obtener los datos
    fun leer(id:Long):Producto{
        var usuario:Producto=SugarRecord.findById(Producto::class.java,id)
        return usuario

    }

    //funcion que permite actualizar los datos
    fun actualizar(id: Long, producto: String, precio: String, disponible: String, observaciones: String){
        var usuario:Producto = SugarRecord.findById(Producto::class.java,id)
        usuario.producto=producto
        usuario.precio=precio
        usuario.disponible=disponible
        usuario.observaciones=observaciones
        SugarRecord.save(usuario)
    }
}
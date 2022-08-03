package com.miempresa.rapido2

import com.orm.dsl.Table
import java.io.Serializable

@Table
data class Producto (
    var id:Long? = null,
    var producto: String? = null,
    var precio: String? = null,
    var disponible: String? = null,
    var observaciones: String? = null):Serializable{

    constructor(producto: String?,precio: String?,disponible: String?,observaciones: String?):this(){
        this.producto = producto
        this.precio = precio
        this.disponible = disponible
        this.observaciones = observaciones
    }
}
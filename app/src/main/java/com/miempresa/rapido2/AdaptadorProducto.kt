package com.miempresa.rapido2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class AdaptadorProducto(val ListaUsuarios:ArrayList<Producto>):RecyclerView.Adapter<AdaptadorProducto.ViewHolder>() {

    var contexto:Context?=null

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //v
        val fNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fPrecio = itemView.findViewById<TextView>(R.id.lblPrecio)
        val fDisponible = itemView.findViewById<TextView>(R.id.lblDisponible)
        val fObservaciones = itemView.findViewById<TextView>(R.id.lblObservaciones)

        //Muestra los botones de editar y eliminar
        val fEditar = itemView.findViewById<ImageButton>(R.id.btnEditar)
        val fEliminar =itemView.findViewById<ImageButton>(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.vista_producto,parent,false);
        contexto=parent?.context
        return ViewHolder(v);

    }

    override fun onBindViewHolder(holder: AdaptadorProducto.ViewHolder, position: Int) {

        var user = this.ListaUsuarios.get(position)
        holder?.fNombre?.text = ListaUsuarios[position].producto
        holder?.fPrecio?.text = ListaUsuarios[position].precio
        holder?.fDisponible?.text = ListaUsuarios[position].disponible
        holder?.fObservaciones?.text = ListaUsuarios[position].observaciones

        holder?.fEliminar.setOnClickListener() {
            var dialogBuilder = AlertDialog.Builder(contexto)
            dialogBuilder.setTitle("Confirmar eliminacion")
            dialogBuilder.setMessage("¿Desea eliminar usuario?")
            dialogBuilder.setPositiveButton("Eliminar",{dialogInterface:DialogInterface,i:Int ->
                var userrepo = ProductoRepositorio()
                userrepo.borrar(user.id!!)
                this.ListaUsuarios.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, itemCount)
            })
            dialogBuilder.setNegativeButton("Cancelar",{dialogInterface:DialogInterface,i:Int->})
            dialogBuilder.show()
        }


        //SIRVE PARA EDITAR

        holder?.fEditar.setOnClickListener(){
            var userrepo=ProductoRepositorio()
            var usuario = userrepo.leer(user.id!!)

            //editar usuario

            var editarUsuario=Intent(holder?.itemView.context,Registro_producto::class.java)
            editarUsuario.putExtra("usuario",usuario as Serializable)
            holder?.itemView.context.startActivity(editarUsuario)
        }
    }

    override fun getItemCount(): Int {
        return ListaUsuarios.size
    }
}
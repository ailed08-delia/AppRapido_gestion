package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.miempresa.rapido2.databinding.ActivityEditarPerfilVendedorBinding
import com.miempresa.rapido2.databinding.ActivityPrincipalVendedorBinding
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.*
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtApellido
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtCelular
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtContraseña
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtCorreo
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtNegocio
import kotlinx.android.synthetic.main.activity_editar_perfil_vendedor.edtNombre
import kotlinx.android.synthetic.main.activity_registro_vendedor.*

class Editar_perfil_vendedor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityEditarPerfilVendedorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("vendedor").document(uid).get().addOnSuccessListener { document ->
            if (document.exists()) {
                binding.edtNombre.setText(document.get("nombre") as String)
                binding.edtApellido.setText(document.get("apellido") as String)
                binding.edtCorreo.setText(document.get("correo") as String)
                binding.edtContraseA.setText(document.get("contrase") as String)
                binding.edtNegocio.setText(document.get("nombreNegocio") as String)
                binding.edtCelular.setText(document.get("celular") as String)
            } else {

            }
        }
        binding.btnEditarPerfil.setOnClickListener {
            EditarPerfil(uid)
        }
    }

    private fun EditarPerfil(uid: String){
        val nombre = edtNombre.text.toString()
        val apellido = edtApellido.text.toString()
        val correo = edtCorreo.text.toString()
        val contrase = edtContraseña.text.toString()
        val nombreNegocio = edtNegocio.text.toString()
        val celular = edtCelular.text.toString()
        val map = hashMapOf(
            "nombre" to nombre,
            "apellido" to apellido,
            "correo" to correo,
            "contrase" to contrase,
            "nombreNegocio" to nombreNegocio,
            "celular" to celular
        )

        val db = Firebase.firestore

        db.collection("vendedor").document(uid).set(map).addOnSuccessListener {
            infoUser()
            Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al guardar la informacion",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    private fun infoUser() {
        val infoUserIntent = Intent(this, Principal_vendedor::class.java)
        startActivity(infoUserIntent)

    }

}
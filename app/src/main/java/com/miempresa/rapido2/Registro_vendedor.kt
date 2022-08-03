package com.miempresa.rapido2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.miempresa.rapido2.databinding.ActivityRegistroVendedorBinding
import kotlinx.android.synthetic.main.activity_registro_vendedor.*
import java.util.regex.Pattern

class Registro_vendedor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityRegistroVendedorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        btnRegistrarse.setOnClickListener {


        }

        binding.btnRegistrarse.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val apellido = edtApellido.text.toString()
            val correo = edtCorreo.text.toString()
            val contrase = edtContraseña.text.toString()
            val nombreNegocio = edtNegocio.text.toString()
            val celular = edtCelular.text.toString()


            if (nombre.isNotEmpty() && apellido.isNotEmpty() && correo.isNotEmpty() && contrase.isNotEmpty() && nombreNegocio.isNotEmpty() && celular.isNotEmpty()) {
                val mEmail = binding.edtCorreo.text.toString()
                val mPassword = binding.edtContraseA.text.toString()
                val passwordRegex = Pattern.compile("^" +
                        ".{6,}" +                // Al menos 4 caracteres
                        "$")

                if(mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                    Toast.makeText(this, "Ingrese un email valido.",
                        Toast.LENGTH_SHORT).show()
                } else if (mPassword.isEmpty() || !passwordRegex.matcher(mPassword).matches()){
                    Toast.makeText(this, "La contraseña es debil.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    createAccount(mEmail, mPassword)
                }
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

        txtIniciarSesión.setOnClickListener{
            val lanzar = Intent(this,Iniciar_sesion::class.java)
            startActivity(lanzar)
        }
    }

    private fun createAccount(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Toast.makeText(baseContext,"Registro exitoso.", Toast.LENGTH_SHORT).show()
                    val nombre = edtNombre.text.toString()
                    val apellido = edtApellido.text.toString()
                    val correo = edtCorreo.text.toString()
                    val contrase = edtContraseña.text.toString()
                    val nombreNegocio = edtNegocio.text.toString()
                    val celular = edtCelular.text.toString()
                    registrarUsuario(nombre,
                        apellido,
                        correo,
                        contrase,
                        nombreNegocio,
                        celular)
                }else{
                    Log.w("TAG","CreateUserWithEmail:failure",task.exception)
                    Toast.makeText(baseContext,"Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun registrarUsuario(
        nombre: String,
        apellido: String,
        correo: String,
        contrase: String,
        nombreNegocio: String,
        celular: String,
    ) {


                    val user = auth.currentUser

                    val uid = user!!.uid

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
                        Toast.makeText(this, "Vendedor Registrado", Toast.LENGTH_SHORT).show()
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
        val infoUserIntent = Intent(this, Iniciar_sesion::class.java)
        startActivity(infoUserIntent)

    }

}
package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.miempresa.rapido2.databinding.ActivityIniciarSesionBinding
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*


class Iniciar_sesion : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnIniciarSesion.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()
            when {
                mEmail.isEmpty() || mPassword.isEmpty() ->{
                    Toast.makeText(baseContext,"Correo o clave incorrecta.",Toast.LENGTH_SHORT).show()
                } else -> {
                SignIn(mEmail,mPassword)
            }
            }
        }
        //link de Registrarse
        txtRegistrate.setOnClickListener{
            val intent = Intent(this,Registro_vendedor::class.java)
            this.startActivity(intent)
        }

        //link de olvidar contraseña
        txtOlvideContra.setOnClickListener{
            val intent = Intent(this,Olvide_contrasena::class.java)
            this.startActivity(intent)
        }

    }

    private fun SignIn(email: String, password : String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Log.d("TAG","signInWithEmail:success")
                    reload()
                }else {
                    Log.w("TAG","signInWithEmail:failure",task.exception)
                    Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload(){
        val intent = Intent(this,Principal_vendedor::class.java)
        this.startActivity(intent)
    }


}
package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.miempresa.rapido2.databinding.ActivityOlvideContrasenaBinding
import kotlinx.android.synthetic.main.activity_olvide_contrasena.*

class Olvide_contrasena : AppCompatActivity() {

    private lateinit var binding: ActivityOlvideContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOlvideContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviarCodigo.setOnClickListener {
            val emailAddres = binding.edtCorreo.text.toString()
            Firebase.auth.sendPasswordResetEmail(emailAddres)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        reload()
                    }else{
                        Toast.makeText(this,"Ingrese un email valido.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun reload(){
        Toast.makeText(this,"Se envió el enlace de cambio de contraseña a su correo.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,Iniciar_sesion::class.java)
        this.startActivity(intent)
    }


}
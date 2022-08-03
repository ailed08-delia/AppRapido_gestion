package com.miempresa.rapido2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.miempresa.rapido2.databinding.ActivityIniciarSesionBinding
import com.miempresa.rapido2.databinding.ActivityOlvideContrasenaBinding
import com.miempresa.rapido2.databinding.ActivityPrincipalVendedorBinding

class Principal_vendedor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityPrincipalVendedorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("vendedor").document(uid).get().addOnSuccessListener { document ->
            if (document.exists()) {
                binding.textNombrePerfil.setText(document.get("nombre") as String)
                binding.textCorreoPerfil.setText(document.get("correo") as String)
            }
        }

        /*binding.btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, Editar_perfil_vendedor::class.java)
            this.startActivity(intent)
        }*/

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_inicio-> {
                    val llamaractividad = Intent(applicationContext, Principal_vendedor::class.java)
                    startActivity(llamaractividad)
                }
                R.id.nav_catalogo-> {
                    val llamaractividad = Intent(applicationContext, Productos::class.java)
                    startActivity(llamaractividad)
                }
                R.id.nav_contacto-> {
                    val llamaractividad = Intent(applicationContext, Redes_vendedor::class.java)
                    startActivity(llamaractividad)
                }
                R.id.nav_perfil-> {
                    val llamaractividad = Intent(applicationContext, Editar_perfil_vendedor::class.java)
                    startActivity(llamaractividad)
                }
            }
            true
        }

    }
}
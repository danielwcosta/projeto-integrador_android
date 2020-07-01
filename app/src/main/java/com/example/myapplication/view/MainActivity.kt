package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.data.DatabaseBuilder.getAppDatabase
import com.example.myapplication.util.Helper
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var buttonRegras: Button? = null
    var buttonJogar: Button? = null
    var buttonTimesFavoritos: Button? = null
    var activity: Activity = this
    var txtUsuario: TextView? = null
    var callbackManager = CallbackManager.Factory.create()
    var firebaseAuth = FirebaseAuth.getInstance()

    private val accessUsuario by lazy {
        DatabaseBuilder.getAppDatabase(this).accessUsuario()
    }

    lateinit var database : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        buttonRegras!!.setOnClickListener {
            val regrasIntent = Intent(activity, RegrasActivity::class.java)
            startActivity(regrasIntent)
        }
        buttonJogar!!.setOnClickListener {
            val perguntaIntent = Intent(activity, PerguntaActivity::class.java)
            startActivity(perguntaIntent)
        }
        buttonTimesFavoritos!!.setOnClickListener {
            val listaFavoritosIntent = Intent(activity, ListaFavoritosActivity::class.java)
            startActivity(listaFavoritosIntent)
        }
//        val userID = firebaseAuth.currentUser!!.uid
//        accessUsuario.procuraUserId(userID).run {
//            txtUsuario?.setText(user)
//        }
    }

    private fun initViews() {
        buttonRegras = findViewById(R.id.MainActivity_buttonRegras)
        buttonJogar = findViewById(R.id.MainActivity_buttonJogar)
        buttonTimesFavoritos = findViewById(R.id.MainActivity_buttonTimesFavoritos)
        txtUsuario = findViewById(R.id.MainActivity_textViewUsuario)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Helper.logout(activity)
        Toast.makeText(activity, "Usuario deslogado.", Toast.LENGTH_LONG).show()
    }

    private fun logOffFaceBook() {
        LoginManager.getInstance().logOut()
    }
}
package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import base.ActBase
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelFirebaseLogin
import com.example.myapplication.ViewModel.ViewModelUsuarioRoom
import com.example.myapplication.custom.CircleImageView
import com.example.myapplication.custom.setImageFromURL
import com.example.myapplication.custom.viewModel
import com.example.myapplication.util.Helper
import com.example.myapplication.util.getIdUsuario
import com.example.myapplication.util.logout
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class MainActivity : ActBase() {
    lateinit var buttonRegras: Button
    lateinit var buttonJogar: Button
    lateinit var buttonTimesFavoritos: Button
    lateinit var imgAvatar: CircleImageView
    lateinit var txtUsuario: TextView

    var activity: Activity = this

    private var USER_DEFAULT = "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png"


    private var zuckerberg = "4"
    var callbackManager = CallbackManager.Factory.create()
    private val accessToken: AccessToken? get() = AccessToken.getCurrentAccessToken()
    private val userID get() = accessToken?.userId ?: zuckerberg

    var firebaseAuth = FirebaseAuth.getInstance()


    private val viewModelUsuario: ViewModelUsuarioRoom by viewModel()


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
        if(userID.equals("4") ){
            val userID = firebaseAuth.currentUser?.email!!
            val usuarioRoom = viewModelUsuario.searchUsuarioByEmail(activity, userID)

           imgAvatar.setImageFromURL(firebaseAuth.currentUser?.photoUrl ?: USER_DEFAULT)
           txtUsuario.setText(firebaseAuth.currentUser?.displayName ?:usuarioRoom.nomeCompleto)
        }else{
            imgAvatar.setImageFromURL(urlFotoFace(userID))
            txtUsuario.setText("Daniel Wong Costa")
        }

    }

    private fun initViews() {
        buttonRegras = findViewById(R.id.MainActivity_buttonRegras)
        buttonJogar = findViewById(R.id.MainActivity_buttonJogar)
        buttonTimesFavoritos = findViewById(R.id.MainActivity_buttonTimesFavoritos)
        txtUsuario = findViewById(R.id.MainActivity_textViewUsuario)
        imgAvatar = findViewById(R.id.MainActivity_imageAvatar)
    }

    private fun urlFotoFace(id: String) = "https://graph.facebook.com/$id/picture?type=large"


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout(activity)
        Toast.makeText(activity, "Usuario deslogado.", Toast.LENGTH_LONG).show()
    }

}
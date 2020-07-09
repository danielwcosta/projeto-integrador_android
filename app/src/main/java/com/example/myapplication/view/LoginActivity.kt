package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import base.ActBase
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelFirebaseLogin
import com.example.myapplication.ViewModel.ViewModelUsuarioRoom
import com.example.myapplication.custom.dp
import com.example.myapplication.custom.toast
import com.example.myapplication.util.*
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ActBase() {
    lateinit var email: EditText
    lateinit var senha: EditText
    lateinit var cadastro: TextView
    lateinit var btnLogin: Button
    lateinit var btnLoginFaceBook: LoginButton
    lateinit var btnLoginGoogle: SignInButton
    private val activity: Activity = this
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val loginCode = 300

    private val dp16 = 16.dp
    private var zuckerberg = "4"
    private val callbackManager = CallbackManager.Factory.create()
    private val accessToken: AccessToken? get() = AccessToken.getCurrentAccessToken()
    private val userID get() = accessToken?.userId ?: zuckerberg

    private val viewModel: ViewModelFirebaseLogin by lazy {
        ViewModelProviders.of(this).get(ViewModelFirebaseLogin::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deslogaLogados(activity)
        viewModel.notifyUI = { mensagem, vaiLogar -> atualizarTela(mensagem, vaiLogar) }
        initView()
        initClickListeners()
    }

    private fun atualizarTela(mensagem: String, vaiLogar: Boolean) {
        toast(mensagem)
        if (vaiLogar) {
            val mainIntent = Intent(activity, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            loginCode -> viewModel.logIn(data)
        }
    }

    private fun initClickListeners() {

        cadastro!!.setOnClickListener { v: View? ->
            val cadastroIntent = Intent(activity, CadastroActivity::class.java)
            startActivity(cadastroIntent)
        }
        btnLogin!!.setOnClickListener {
            if (editTextIsEmpty(email!!, senha!!)) {
                editTextEmptyError(email!!,senha!!)
            } else {
                loginFirebase(email!!, senha!!)
            }
        }
        btnLoginGoogle!!.setOnClickListener {
            loginGoogle(loginCode)
        }
        btnLoginFaceBook =  LoginButton(this).apply {
            loginFacebook()
        }
    }

    private fun loginFirebase(email:TextView, senha:TextView){

        val userUid = firebaseAuth?.currentUser?.uid.toString()
        // authenticate the user
        firebaseAuth?.signInWithEmailAndPassword(email.text.toString(), senha.text.toString())?.addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->

            if (task.isSuccessful) {
                irParaMain(userUid)

            } else {
                toast("Erro !! " + task.exception!!.message)
            }
        })
    }

    private fun loginGoogle(loginCode:Int){
     val loginIntent by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        ).signInIntent
    }
        startActivityForResult(loginIntent, loginCode)
    }

    private fun LoginButton.loginFacebook() {
        val facebookCallback = object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {
                toast("Conectado pelo Facebook")
                irParaMain(userID)
                //fotinha.setImageFromURL(urlFotoFace(userID))
            }

            override fun onCancel() {
                toast("CANCELÔ =(")
            }

            override fun onError(error: FacebookException?) {
                toast("Ocorreu um erro ao conectar no Facebook")
            }
        }
        registerCallback(callbackManager, facebookCallback)
    }

    private fun irParaMain(uiid: String) {
        salvarIdUsuario(applicationContext, uiid)
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    private fun initView() {
        setContentView(R.layout.activity_login)
        cadastro = findViewById(R.id.login_cadastro_id)
        email = findViewById(R.id.login_text_input_usuario_id)
        senha = findViewById(R.id.login_text_input_senha_id)
        btnLogin = findViewById(R.id.login_button_login_id)
        btnLoginFaceBook = findViewById(R.id.login_button_facebook_id)
        btnLoginGoogle = findViewById(R.id.login_button_google_id)
    }

}
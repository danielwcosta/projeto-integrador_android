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
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelFirebaseLogin
import com.example.myapplication.custom.dp
import com.example.myapplication.custom.toast
import com.example.myapplication.util.Helper
import com.example.myapplication.util.editTextEmptyError
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

class LoginActivity : AppCompatActivity() {
    private var email: EditText? = null
    private var senha: EditText? = null
    private var cadastro: TextView? = null
    private var btnLogin: Button? = null
    private var btnLoginFaceBook: LoginButton? = null
    private var btnLoginGoogle: SignInButton? = null
    private val activity: Activity = this
    private val loginCode = 300
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


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
        viewModel.notifyUI = { mensagem, vaiLogar -> atualizarTela(mensagem, vaiLogar) }
        initView()
        initClickListeners()
    }

    private val loginIntent by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        ).signInIntent
    }

    private fun atualizarTela(mensagem: String, vaiLogar: Boolean) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
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
            startActivityForResult(loginIntent, loginCode)

        }

        btnLoginFaceBook =  LoginButton(this).apply {
            setPadding(dp16, dp16, dp16, dp16)
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
                Toast.makeText(this@LoginActivity, "Error ! " + task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun LoginButton.loginFacebook() {
        registerCallback(callbackManager, facebookCallback)
    }

    private val facebookCallback = object : FacebookCallback<LoginResult> {

        override fun onSuccess(result: LoginResult?) {
            toast("SUCESSO!")
            irParaMain(userID)
            //fotinha.setImageFromURL(urlFotoFace(userID))
        }

        override fun onCancel() {
            toast("CANCELÔ =(")
        }

        override fun onError(error: FacebookException?) {
            toast("EROU!")
        }
    }


    private fun irParaMain(uiid: String) {
        Helper.salvarIdUsuario(applicationContext, uiid)
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


    companion object {
        fun editTextIsEmpty(vararg editTexts: EditText): Boolean {
            for (editText in editTexts) {
                if (editText.text.toString().isEmpty()) {
                    return true
                }
            }
            return false
        }
    }
}
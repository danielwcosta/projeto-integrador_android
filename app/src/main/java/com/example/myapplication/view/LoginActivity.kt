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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var usuario: EditText? = null
    private var senha: EditText? = null
    private var cadastro: TextView? = null
    private var buttonLogin: Button? = null
    private var buttonLoginFaceBook: Button? = null
    private var buttonLoginGoogle: Button? = null
    private var buttonLogOffGoogle: Button? = null
    private val activity: Activity = this
    private val loginCode = 300

    private val viewModel: ViewModelFirebaseLogin by lazy {
        ViewModelProviders.of(this).get(ViewModelFirebaseLogin::class.java)
    }


    private val loginIntent by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        ).signInIntent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.notifyUI = {mensagem,vaiLogar -> atualizarTela(mensagem,vaiLogar)}
        initView()
        initClickListeners()
    }

    private fun atualizarTela(mensagem: String, vaiLogar: Boolean) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
        if (vaiLogar){
            val mainIntent = Intent(activity, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
        buttonLogin!!.setOnClickListener { v: View? ->
            if (editTextIsEmpty(usuario!!, senha!!)) {
                Toast.makeText(activity, "Falta preencher usuario e senha", Toast.LENGTH_LONG).show()
            } else {
                val mainIntent = Intent(activity, MainActivity::class.java)
                startActivity(mainIntent)
            }
        }
        buttonLoginFaceBook!!.setOnClickListener { v: View? ->
            val mainIntent = Intent(activity, MainActivity::class.java)
            startActivity(mainIntent)
        }
        buttonLoginGoogle!!.setOnClickListener { startActivityForResult(loginIntent, loginCode)
        buttonLogOffGoogle!!.setOnClickListener{(viewModel::logOff)}
        }
    }

    private fun initView() {
        setContentView(R.layout.activity_login)
        cadastro = findViewById(R.id.login_cadastro_id)
        usuario = findViewById(R.id.login_text_input_usuario_id)
        senha = findViewById(R.id.login_text_input_senha_id)
        buttonLogin = findViewById(R.id.login_button_login_id)
        buttonLoginFaceBook = findViewById(R.id.login_button_facebook_id)
        buttonLoginGoogle = findViewById(R.id.login_button_google_id)
        buttonLogOffGoogle = findViewById(R.id.login_button_logoff_google_id)
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
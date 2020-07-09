package com.example.myapplication.view

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import base.ActBind
import com.example.myapplication.ViewModel.ViewModelUsuarioRoom
import com.example.myapplication.custom.viewModel
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.databinding.ActivityCadastroBinding
import com.example.myapplication.model.Usuario
import com.example.myapplication.util.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : ActBind<ActivityCadastroBinding>() {

    override val binding by lazy { bind(ActivityCadastroBinding::class) }

    private val viewModelUsuario: ViewModelUsuarioRoom by viewModel()

    private val accessUsuario by lazy {
        DatabaseBuilder.getAppDatabase(this).accessUsuario()
    }
    private val usuarios: MutableList<Usuario> = mutableListOf()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val fStore = FirebaseFirestore.getInstance()
    lateinit var userID: String


    override fun ActivityCadastroBinding.onBoundView() {
        cadastroButtonCadastrarId.setOnClickListener(onCreateUsuario())
    }
    private fun ActivityCadastroBinding.onCreateUsuario() = View.OnClickListener {
        val checkEmail = saoIguais(cadastroEmailId, cadastroConrfimaEmailId)
        val checkSenha = saoIguais(cadastroSenhaId, cadastroConrfimaSenhaId)
        val emailValido = emailValido(cadastroEmailId)
        val tamanhoSenha = minCaracteres(cadastroSenhaId)

        if (editTextIsEmpty(cadastroNomeId, cadastroUsuarioId, cadastroEmailId,
                        cadastroConrfimaEmailId, cadastroSenhaId, cadastroConrfimaSenhaId)) {
            editTextEmptyError(cadastroNomeId, cadastroUsuarioId, cadastroEmailId,
                    cadastroConrfimaEmailId, cadastroSenhaId, cadastroConrfimaSenhaId)

        } else if (!checkEmail || !checkSenha || tamanhoSenha || !emailValido) {

            if(!checkEmail){ cadastroEmailId.setError("Email e confirmar email não conferem")
                cadastroConrfimaEmailId.setError("Email e confirmar email não conferem")}

            if(!checkSenha){ cadastroSenhaId.setError("Senha e confirmar senha não conferem")
                cadastroConrfimaSenhaId.setError("Senha e confirmar senha não conferem")}

            if(!emailValido){
                cadastroEmailId.setError("Este não é um e-mail valido.")
            }
            if(tamanhoSenha) {
                cadastroSenhaId.setError("Campo com menos de 6 caracteres")
            }


        }else {
            cadastrarFirebase(cadastroEmailId,cadastroSenhaId)

            userID = firebaseAuth.currentUser?.getUid().toString()

            val usuario = Usuario(
                    0,
                    userID,
                    cadastroNomeId.text.toString(),
                    cadastroUsuarioId.text.toString(),
                    cadastroEmailId.text.toString(),
                    cadastroSenhaId.text.toString()
            )

            viewModelUsuario.addUsuario(this@CadastroActivity,usuario)
            usuario.apply { toast("Usuário: $nomeCompleto\n$user\n$email\n Cadastrado com sucesso!! ") }

            viewModelUsuario.updateUsuario(this@CadastroActivity,usuario)

        }

    }
    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun cadastrarFirebase(email : EditText,senha : EditText){

        firebaseAuth.createUserWithEmailAndPassword(email.text.toString(), senha.text.toString()).addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
            if (task.isSuccessful) {

                userID = firebaseAuth.currentUser?.getUid().toString()

                startActivity(Intent(getApplicationContext(), MainActivity::class.java))
            } else {
                Toast.makeText(this@CadastroActivity, "Erro ! " + task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}




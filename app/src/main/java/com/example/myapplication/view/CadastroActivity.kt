package com.example.myapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import base.ActBind
import com.example.myapplication.R
import com.example.myapplication.custom.hideKeyBoard
import com.example.myapplication.custom.update
import com.example.myapplication.databinding.ActivityCadastroBinding
import com.example.myapplication.model.DatabaseBuilder
import com.example.myapplication.model.Usuario
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : ActBind<ActivityCadastroBinding>() {

    override val binding by lazy { bind(ActivityCadastroBinding::class) }

    private val accessUsuario by lazy {
        DatabaseBuilder.getAppDatabase(this).accessUsuario()
    }
    private val usuarios: MutableList<Usuario> = mutableListOf()

    override fun ActivityCadastroBinding.onBoundView() {
        cadastroButtonCadastrarId.setOnClickListener(onCreateUsuario())
    }
    private fun ActivityCadastroBinding.onCreateUsuario() = View.OnClickListener {

        val checkEmail = saoIguais(cadastroEmailId, cadastroConrfimaEmailId)
        val checkSenha = saoIguais(cadastroSenhaId, cadastroConrfimaSenhaId)
        if (editTextIsEmpty(cadastroNomeId, cadastroUsuarioId, cadastroEmailId,
                        cadastroConrfimaEmailId, cadastroSenhaId, cadastroConrfimaSenhaId)) {
            Toast.makeText(activity, "Falta preencher campos.", Toast.LENGTH_LONG).show()
        } else if (!checkEmail) {
            Toast.makeText(activity, "Confirmar e-mail invalido.", Toast.LENGTH_LONG).show()
        } else if (!checkSenha) {
            Toast.makeText(activity, "Confirmar senha invalido.", Toast.LENGTH_LONG).show()
        } else {
            val usuario = Usuario(
                    0,
                    cadastroNomeId.text.toString(),
                    cadastroUsuarioId.text.toString(),
                    cadastroEmailId.text.toString(),
                    cadastroSenhaId.text.toString()
            )
            accessUsuario.inserir(usuario)
            usuario.apply { toast("CREATE\n\n$nomeCompleto\n$user\n$email ") }
            usuarios.update(accessUsuario.puxaTodaLista())
            val loginIntent = Intent(activity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }
    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

private fun editTextIsEmpty(vararg editTexts: EditText): Boolean {
    for (editText in editTexts) {
        if (editText.text.toString().isEmpty()) {
            return true
        }
    }
    return false
}
private fun saoIguais(editText1: EditText, editText2: EditText): Boolean {
    return editText1.text.toString() == editText2.text.toString()
}

package com.example.myapplication.ViewModel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class ViewModelFirebaseLogin : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val user get() = auth.currentUser
    lateinit var notifyUI: (String,Boolean) -> Unit // observem que não tem atribuição aqui


    fun logIn(data: Intent?) = try {
        GoogleSignIn.getSignedInAccountFromIntent(data).run {
            val credential = GoogleAuthProvider.getCredential(result?.idToken, null)
            auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) onLoginSuccess() else onLoginFail()
                    }
        }
    } catch (exception: Exception) {
        onLoginFail()
    }


    fun logOff() {
        val message: String
        if (user != null) {
            message = "${user?.displayName} saiu!"
            auth.signOut()
        } else {
            message = "Já tá deslogado ow!"
        }
        notifyUI(message,false)
    }
    val onLoginSuccess = { notifyUI("${user?.displayName} logou com sucesso!",true) }
    val onLoginFail = { notifyUI("Falha ao tentar login",false) }

}
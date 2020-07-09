package com.example.myapplication.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.view.LoginActivity
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import java.io.FileNotFoundException


fun editTextIsEmpty(vararg editTexts: EditText): Boolean {
    for (editText in editTexts) {
        if (editText.text.toString().isEmpty()) {
            return true
        }
    }
    return false
}

fun editTextEmptyError(vararg editTexts: EditText): Unit {
    for (editText in editTexts) {
        if (editText.text.toString().isEmpty()) {
            return editText.setError("Falta preencher esse campo.")
        }
    }
}

fun saoIguais(editText1: EditText, editText2: EditText): Boolean {
    return editText1.text.toString().equals(editText2.text.toString())
}

fun minCaracteres(editText: EditText): Boolean {
    if (editText.text.toString().length < 6){
        return true
    }
    return false
}

fun emailValido(email: EditText): Boolean {
    return email.text.toString().contains("@") && email.text.toString().contains(".com")
}

fun estaConectado(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (cm != null) {
        val ni = cm.activeNetworkInfo
        return ni != null && ni.isConnected
    }
    return false
}

 fun deslogar() {
    FirebaseAuth.getInstance().signOut()
    LoginManager.getInstance().logOut() //facebook
}

fun deslogaLogados(context: Context?) {
    val googleSignInClient = context?.let { google(it) }
    if (googleSignInClient != null) {
        googleSignInClient.signOut().addOnCompleteListener { task: Task<Void?>? -> }
    }
    deslogar()
}

fun salvarIdUsuario(context: Context, uiid: String?) {
    val preferences = context.getSharedPreferences("CHAVE_APP", Context.MODE_PRIVATE)
    preferences.edit().putString("UIID", uiid).apply()
}

fun getIdUsuario(context: Context): String? {
    val preferences = context.getSharedPreferences("CHAVE_APP", Context.MODE_PRIVATE)
    return preferences.getString("UIID", "")
}

fun logout(context: Context) {
    val googleSignInClient = google(context)
    if (googleSignInClient != null) {
        googleSignInClient.signOut().addOnCompleteListener { task: Task<Void?>? -> }
    }
    deslogar()
    val intent = Intent(context, LoginActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
}

fun google(context: Context): GoogleSignInClient? {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .build()
    return GoogleSignIn.getClient(context, gso)
}

// Method that deocde uri into bitmap. This method is necessary to decode
// large size images to load over imageview
@Throws(FileNotFoundException::class)
fun decodeUri(context: Context, uri: Uri?,
              requiredSize: Int): Bitmap? {
    val o = BitmapFactory.Options()
    o.inJustDecodeBounds = true
    BitmapFactory.decodeStream(uri?.let {
        context.contentResolver
                .openInputStream(it)
    }, null, o)
    var width_tmp = o.outWidth
    var height_tmp = o.outHeight
    var scale = 1
    while (true) {
        if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize) break
        width_tmp /= 2
        height_tmp /= 2
        scale *= 2
    }
    val o2 = BitmapFactory.Options()
    o2.inSampleSize = scale
    return BitmapFactory.decodeStream(uri?.let {
        context.contentResolver
                .openInputStream(it)
    }, null, o2)
}
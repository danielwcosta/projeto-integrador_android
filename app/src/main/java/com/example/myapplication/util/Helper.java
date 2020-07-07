package com.example.myapplication.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.view.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;


public class Helper {
    public static boolean usuarioValido(String usuario) {
        return (usuario.contains("@") && usuario.contains(".com"));
    }

    public static boolean isEmptyString(String valor) {
        return valor == null || valor.trim().equals("");
    }

    public static boolean senhaValida(String senha) {
        if (senha.length() < 6)
            return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;

        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9')
                achouNumero = true;
            else if (c >= 'A' && c <= 'Z')
                achouMaiuscula = true;
            else if (c >= 'a' && c <= 'z')
                achouMinuscula = true;
            else
                achouSimbolo = true;
        }
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    public static boolean nomeValido(String nomeCompleto) {
        return (nomeCompleto.contains(" "));
    }

    public static boolean senhaIguais(String senha, String confirmacaoSenha) {
        return (confirmacaoSenha.equals(senha));
    }

    public static boolean verificaConexaoComInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() &&
                    (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                            || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        }
        return false;
    }


    private static void deslogar() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut(); //facebook
    }

    public static String getString(TextInputLayout viewName) {
        return viewName.getEditText().getText().toString();
    }

    public static String getIdUsuario(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("CHAVE_APP", Context.MODE_PRIVATE);
        return preferences.getString("UIID", "");
    }

    public static void salvarIdUsuario(Context context, String uiid) {
        SharedPreferences preferences = context.getSharedPreferences("CHAVE_APP", Context.MODE_PRIVATE);
        preferences.edit().putString("UIID", uiid).apply();
    }


    public static GoogleSignInClient google(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);
        return googleSignInClient;
    }

    public static void logout(Context context){
        GoogleSignInClient googleSignInClient = google(context);
        googleSignInClient.signOut().addOnCompleteListener(task -> {
        });
        Helper.deslogar();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
    public static void deslogaLogados(Context context){
        GoogleSignInClient googleSignInClient = google(context);
        googleSignInClient.signOut().addOnCompleteListener(task -> {
        });
        Helper.deslogar();
    }

    public static void notificacao(Context contexto, String sMensagem) {
        Toast toast = Toast.makeText(contexto, sMensagem, Toast.LENGTH_LONG);
        toast.show();
    }
}
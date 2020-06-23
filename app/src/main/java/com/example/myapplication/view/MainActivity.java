package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    Button buttonRegras, buttonJogar, buttonTimesFavoritos;
    Activity activity = this;
    CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegras = findViewById(R.id.MainActivity_buttonRegras);
        buttonRegras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regrasIntent = new Intent(activity, RegrasActivity.class);
                startActivity(regrasIntent);
            }
        });

        buttonJogar = findViewById(R.id.MainActivity_buttonJogar);
        buttonJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perguntaIntent = new Intent(activity, PerguntaActivity.class);
                startActivity(perguntaIntent);
            }
        });

        buttonTimesFavoritos = findViewById(R.id.MainActivity_buttonTimesFavoritos);
        buttonTimesFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaFavoritosIntent = new Intent(activity, ListaFavoritosActivity.class);
                startActivity(listaFavoritosIntent);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginManager.getInstance().logOut();
        Toast.makeText(activity, "Deslogou do facebook", Toast.LENGTH_LONG).show();

    }
}

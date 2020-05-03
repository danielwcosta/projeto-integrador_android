package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario,senha;
    private Button buttonCadastro, buttonLogin, buttonLoginFaceBook, buttonLoginGoogle;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        buttonCadastro = findViewById(R.id.cadastro);
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroIntent = new Intent(activity, CadastroActivity.class);
                startActivity(cadastroIntent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextIsEmpty(usuario,senha)){
                    Toast.makeText(activity, "Falta preencher usuario e senha", Toast.LENGTH_LONG).show();
                }else{
                    Intent mainIntent = new Intent(activity, MainActivity.class);
                    startActivity(mainIntent);}
            }
        });

        buttonLoginFaceBook = findViewById(R.id.buttonLoginFacebook);
        buttonLoginFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        buttonLoginGoogle = findViewById(R.id.buttonLoginGoogle);
        buttonLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity, MainActivity.class);
                startActivity(mainIntent);}
        });
    }

    public static boolean editTextIsEmpty(EditText... editTexts){
        for (EditText editText : editTexts) {
            if(editText.getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void initView(){
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);
        buttonLogin = findViewById(R.id.buttonLogin);

    }
}

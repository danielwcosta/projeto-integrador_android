package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario,senha;
    private TextView cadastro;
    private Button  buttonLogin, buttonLoginFaceBook, buttonLoginGoogle;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        cadastro = findViewById(R.id.login_cadastro_id);
        cadastro.setOnClickListener(new View.OnClickListener() {
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

        buttonLoginFaceBook = findViewById(R.id.login_button_facebook_id);
        buttonLoginFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        buttonLoginGoogle = findViewById(R.id.login_button_google_id);
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

        usuario = findViewById(R.id.login_text_input_usuario_id);
        senha = findViewById(R.id.login_text_input_senha_id);
        buttonLogin = findViewById(R.id.login_button_login_id);

    }
}

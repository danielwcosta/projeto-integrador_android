package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome,usuario,email,confirmarEmail,senha,confirmarSenha;
    private Button btnCadastrar;

    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextIsEmpty(nome,usuario,email,confirmarEmail,senha,confirmarSenha)){
                    Toast.makeText(activity, "Falta preencher campos", Toast.LENGTH_LONG).show();
                }else{
                    Intent loginIntent = new Intent(activity, LoginActivity.class);
                    startActivity(loginIntent);}
            }
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
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.cadastro_nome_id);
        usuario = findViewById(R.id.cadastro_usuario_id);
        email = findViewById(R.id.cadastro_email_id);
        confirmarEmail = findViewById(R.id.cadastro_conrfimaEmail_id);
        senha = findViewById(R.id.cadastro_senha_id);
        confirmarSenha = findViewById(R.id.cadastro_conrfimaSenha_id);
        btnCadastrar = findViewById(R.id.cadastro_buttonCadastrar_id);

    }
}

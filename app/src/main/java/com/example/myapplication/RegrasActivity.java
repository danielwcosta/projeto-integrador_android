package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RegrasActivity extends AppCompatActivity {

    private ImageView setaVoltar;
    Button buttonJogar;
    private Activity activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);

        setaVoltar = findViewById(R.id.lista_favoritos_seta_voltar_id);

        setaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity,MainActivity.class);
                startActivity(mainIntent);
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
    }
}

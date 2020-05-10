package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Time;

public class TimeFavoritoActivity extends AppCompatActivity {

    private TextView nomeTime;
    private ImageView imgEscudo,setaVoltar;

    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_favorito);

        nomeTime = findViewById(R.id.time_favorito_nome_id);
        imgEscudo = findViewById(R.id.time_favorito_escudo_img_id);
        setaVoltar = findViewById(R.id.time_favorito_seta_voltar_id);

        if(getIntent()!=null){
            Time time = getIntent().getExtras().getParcelable("time");
            nomeTime.setText(time.getNomeTime());
            imgEscudo.setImageResource(time.getImgEscudo());
        }

        setaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaFavIntent = new Intent(activity,ListaFavoritosActivity.class);
                startActivity(listaFavIntent);
            }
        });


    }
}

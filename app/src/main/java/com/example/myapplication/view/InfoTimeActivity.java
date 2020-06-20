package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Team;
import com.squareup.picasso.Picasso;

import base.ActBase;

public class InfoTimeActivity extends ActBase  {

    private TextView nomeTime,nomePais,nomeCidade,nomeEstadio,capacidadeEstadio,anoFundacao,webSite,infoTime;
    private ImageView setaVoltar,imgEscudo, imgCamisa,imgEstadio;
    private Button addFavoritos;

    private Activity activity = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_time);
        geraViews();


        if(getIntent()!=null){
            Team time = getIntent().getExtras().getParcelable("time");
            nomeTime.setText(time.getStrTeam());
            Picasso.get().load(time.getStrTeamBadge()).into(imgEscudo);
            nomePais.setText(time.getStrCountry());
            nomeCidade.setText(time.getStrStadiumLocation());
            nomeEstadio.setText(time.getStrStadium());
            capacidadeEstadio.setText(time.getIntStadiumCapacity());
            anoFundacao.setText(time.getIntFormedYear());
            webSite.setText(time.getStrWebsite());
            Picasso.get().load(time.getStrTeamJersey()).into(imgCamisa);
            Picasso.get().load(time.getStrStadiumThumb()).into(imgEstadio);

            if(time.getStrDescriptionPT() != null){infoTime.setText(time.getStrDescriptionPT());}
            else{infoTime.setText("Não há informações disponíveis.");
            }

        }

        setaVoltar.setOnClickListener(v -> {
            Intent procFavIntent = new Intent(activity,ProcurarFavoritosActivity.class);
            startActivity(procFavIntent);
        });

        addFavoritos.setOnClickListener(v -> Toast.makeText(activity, "Time adicionado aos favoritos", Toast.LENGTH_LONG).show());
    }

    private void geraViews() {
        // TextViews
        nomeTime = findViewById(R.id.info_time_nome_id);
        nomePais = findViewById(R.id.info_time_pais_id);
        nomeCidade = findViewById(R.id.info_time_cidade_id);
        nomeEstadio = findViewById(R.id.info_time_estadio_id);
        capacidadeEstadio = findViewById(R.id.info_time_capacidadeestadio_id);
        anoFundacao = findViewById(R.id.info_time_anofundacao_id);
        webSite = findViewById(R.id.info_time_website_id);
        infoTime = findViewById(R.id.info_time_info2_id);
//        ImageViews
        setaVoltar = findViewById(R.id.info_time_seta_voltar_id);
        imgEscudo = findViewById(R.id.info_time_escudo_img_id);
        imgCamisa = findViewById(R.id.info_time_camisa_img_id);
        imgEstadio = findViewById(R.id.info_time_estadio_img_id);
        addFavoritos = findViewById(R.id.info_time_addfavorito_btn_id);
    }
}
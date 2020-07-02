package com.example.myapplication.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import base.ActBase
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelTeam
import com.example.myapplication.custom.toast
import com.example.myapplication.custom.viewModel
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.model.Team
import com.squareup.picasso.Picasso

class InfoTimeActivity : ActBase() {

    lateinit var nomeTime: TextView
    lateinit var nomePais: TextView
    lateinit var nomeCidade: TextView
    lateinit var capacidadeEstadio: TextView
    lateinit var nomeEstadio: TextView
    lateinit var anoFundacao: TextView
    lateinit var webSite: TextView
    lateinit var infoTime: TextView
    lateinit var setaVoltar: ImageView
    lateinit var imgEscudo: ImageView
    lateinit var imgCamisa: ImageView
    lateinit var imgEstadio: ImageView
    lateinit var addFavoritos: Button
    private val activity: Activity = this

    private val viewModelTeam: ViewModelTeam by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_time)
        geraViews()
        if (intent != null) {
            val time = intent.extras!!.getParcelable<Team>("time")
            nomeTime.text = time!!.strTeam
            Picasso.get().load(time.strTeamBadge).into(imgEscudo)
            nomePais.text = time.strCountry
            nomeCidade.text = time.strStadiumLocation
            nomeEstadio.text = time.strStadium
            capacidadeEstadio.text = time.intStadiumCapacity
            anoFundacao.text = time.intFormedYear
            webSite.text = time.strWebsite
            Picasso.get().load(time.strTeamJersey).into(imgCamisa)
            Picasso.get().load(time.strStadiumThumb).into(imgEstadio)
            if (time.strDescriptionPT != null) {
                infoTime!!.text = time.strDescriptionPT
            } else {
                infoTime!!.text = "Não há informações disponíveis."
            }
        }
        setaVoltar!!.setOnClickListener {
            val procFavIntent = Intent(activity, ProcurarFavoritosActivity::class.java)
            startActivity(procFavIntent)
        }
        addFavoritos!!.setOnClickListener { v: View? ->
            if (intent != null) {
                val time = intent.extras!!.getParcelable<Team>("time")
                val team = Team(0, time!!.strTeam, time.strCountry, time.intStadiumCapacity, time.strStadiumLocation, time.strStadium, time.strStadiumThumb, time.strTeamBadge, time.strTeamJersey, "", time.intFormedYear, "", "", time.strWebsite, "", "", "", infoTime?.text.toString(), "")

                var estaNoFavorito = time!!.strTeam?.let { viewModelTeam.searchTimeByName(this, it) }

                if(!estaNoFavorito!!) {
                    viewModelTeam.putTimesFavorito(this, team)
                    toast("Time adicionado aos favoritos")
                    startActivity(Intent(activity, ListaFavoritosActivity::class.java))
                }else{toast("${time.strTeam} já foi adicionado anteriormente.")}

            }else{toast("Erro! não foi possivel adicionar aos favoritos")}
        }
    }

    private fun geraViews() {
        // TextViews
        nomeTime = findViewById(R.id.info_time_nome_id)
        nomePais = findViewById(R.id.info_time_pais_id)
        nomeCidade = findViewById(R.id.info_time_cidade_id)
        nomeEstadio = findViewById(R.id.info_time_estadio_id)
        capacidadeEstadio = findViewById(R.id.info_time_capacidadeestadio_id)
        anoFundacao = findViewById(R.id.info_time_anofundacao_id)
        webSite = findViewById(R.id.info_time_website_id)
        infoTime = findViewById(R.id.info_time_info2_id)
        // ImageViews
        setaVoltar = findViewById(R.id.info_time_seta_voltar_id)
        imgEscudo = findViewById(R.id.info_time_escudo_img_id)
        imgCamisa = findViewById(R.id.info_time_camisa_img_id)
        imgEstadio = findViewById(R.id.info_time_estadio_img_id)
        addFavoritos = findViewById(R.id.info_time_addfavorito_btn_id)
    }
}
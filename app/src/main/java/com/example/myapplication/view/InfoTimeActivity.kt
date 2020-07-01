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
    private var nomeTime: TextView? = null
    private var nomePais: TextView? = null
    private var nomeCidade: TextView? = null
    private var nomeEstadio: TextView? = null
    private var capacidadeEstadio: TextView? = null
    private var anoFundacao: TextView? = null
    private var webSite: TextView? = null
    private var infoTime: TextView? = null
    private var setaVoltar: ImageView? = null
    private var imgEscudo: ImageView? = null
    private var imgCamisa: ImageView? = null
    private var imgEstadio: ImageView? = null
    private var addFavoritos: Button? = null
    private val activity: Activity = this


    private val viewModelTeam: ViewModelTeam by viewModel()

    private val accessTeam by lazy {
        DatabaseBuilder.getAppDatabase(this).accessTeam()
    }
    private var teamList: MutableList<Team> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_time)
        geraViews()
        if (intent != null) {
            val time = intent.extras!!.getParcelable<Team>("time")
            nomeTime!!.text = time!!.strTeam
            Picasso.get().load(time.strTeamBadge).into(imgEscudo)
            nomePais!!.text = time.strCountry
            nomeCidade!!.text = time.strStadiumLocation
            nomeEstadio!!.text = time.strStadium
            capacidadeEstadio!!.text = time.intStadiumCapacity
            anoFundacao!!.text = time.intFormedYear
            webSite!!.text = time.strWebsite
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
                val team = Team(
                        0,
                        time!!.strTeam,
                        time.strCountry,
                        time.intStadiumCapacity,
                        time.strStadiumLocation,
                        time.strStadium,
                        time.strStadiumThumb,
                        time.strTeamBadge,
                        time.strTeamJersey,
                        "",
                        time.intFormedYear,
                        "",
                        "",
                        time.strWebsite,
                        "",
                        "",
                        "",
                        infoTime?.text.toString(),
                        ""
                )
                viewModelTeam.putTimesFavorito(this, team)
                toast("Time adicionado aos favoritos")
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
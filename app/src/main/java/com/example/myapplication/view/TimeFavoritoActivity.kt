package com.example.myapplication.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelTeam
import com.example.myapplication.custom.toast
import com.example.myapplication.custom.viewModel
import com.example.myapplication.model.Team
import com.example.myapplication.model.Time
import com.squareup.picasso.Picasso

class TimeFavoritoActivity : AppCompatActivity() {
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
    lateinit var removeFavorito: Button
    private val activity: Activity = this

    private val viewModelTeam: ViewModelTeam by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_favorito)
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
                infoTime.text = time.strDescriptionPT
            } else {
                infoTime.text = "Não há informações disponíveis."
            }
        }
        setaVoltar.setOnClickListener(View.OnClickListener {
            startActivity(Intent(activity, ListaFavoritosActivity::class.java))
            finish()
        })
        removeFavorito.setOnClickListener(View.OnClickListener {
            if (intent != null) {

                val time = intent.extras!!.getParcelable<Team>("time")
                val team = time!!.id.let { it1 -> viewModelTeam.searchTimeById(this, it1) }

                viewModelTeam.removeTimesFavorito(this,team)
                toast("Time removido dos favoritos.")

                startActivity(Intent(activity, ListaFavoritosActivity::class.java))
                finish()

            }else{toast("Erro! não foi possivel remover dos favoritos")}

        })
    }
    private fun geraViews() {
        // TextViews
        nomeTime = findViewById(R.id.time_favorito_nome_id)
        nomePais = findViewById(R.id.time_favorito_pais_id)
        nomeCidade = findViewById(R.id.time_favorito_cidade_id)
        nomeEstadio = findViewById(R.id.time_favorito_estadio_id)
        capacidadeEstadio = findViewById(R.id.time_favorito_capacidadeestadio_id)
        anoFundacao = findViewById(R.id.time_favorito_anofundacao_id)
        webSite = findViewById(R.id.time_favorito_website_id)
        infoTime = findViewById(R.id.time_favorito_info2_id)
        // ImageViews
        setaVoltar = findViewById(R.id.time_favorito_seta_voltar_id)
        imgEscudo = findViewById(R.id.time_favorito_escudo_img_id)
        imgCamisa = findViewById(R.id.time_favorito_camisa_img_id)
        imgEstadio = findViewById(R.id.time_favorito_estadio_img_id)
        removeFavorito = findViewById(R.id.time_favorito_removerfav_btn_id)
    }
}
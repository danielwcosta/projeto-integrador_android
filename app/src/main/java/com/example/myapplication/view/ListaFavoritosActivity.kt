package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import base.ActBase
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelTeam
import com.example.myapplication.adapter.ListaFavoritosAdapter
import com.example.myapplication.custom.toast
import com.example.myapplication.custom.viewModel
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.model.Team
import java.util.*


class ListaFavoritosActivity : ActBase() {
    lateinit var btnAddTime: Button
    lateinit var setaVoltar: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: ListaFavoritosAdapter
    private var activity: Activity = this

    private val viewModelTeam: ViewModelTeam by viewModel()

    private val teamsList: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_favoritos)
        geraViews()

        layoutManager = GridLayoutManager(this, 2)
        adapter = ListaFavoritosAdapter(teamsList)

        viewModelTeam.getTimesFavoritos(this)
        viewModelTeam.listFavoritos.observe(this, androidx.lifecycle.Observer {
            it?.let { itTeam ->
                teamsList.addAll(itTeam)
                adapter.notifyDataSetChanged()
            }
        })

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        btnAddTime.setOnClickListener { v: View? ->
            val procurarFavoritoIntent = Intent(activity, ProcurarFavoritosActivity::class.java)
            startActivity(procurarFavoritoIntent)
        }
        setaVoltar.setOnClickListener { v: View? ->
            finish()
            onBackPressed()
        }
    }

    private fun geraViews() {
        recyclerView = findViewById(R.id.lista_favoritos_recycler_view_id)
        btnAddTime = findViewById(R.id.lista_favoritos_add_time_id)
        setaVoltar = findViewById(R.id.lista_favoritos_seta_voltar_id)
    }
}
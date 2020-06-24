package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.ViewModel.ViewModelTeam;
import com.example.myapplication.adapter.ProcurarFavoritosAdapter;
import com.example.myapplication.R;
import com.example.myapplication.fragment.PerguntaFragment;
import com.example.myapplication.model.Team;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProcurarFavoritosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FloatingActionButton btnAddTime;
    private ImageView setaVoltar;
    private Spinner spinner;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private ListaFavoritosAdapter adapter;
    private ProcurarFavoritosAdapter adapter;
    private List<Team> timeList = new ArrayList<>();

    private Activity activity=this;
    private FragmentManager fragmentManager;

    private ViewModelTeam viewModelTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_favoritos);

        geraViews();

        configuraSpinner();

        layoutManager = new GridLayoutManager(this,2);
        adapter = new ProcurarFavoritosAdapter(timeList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        viewModelTeam.getListTeam().observe(this,teams -> adapter.setUpdate(teams));

        setaVoltar.setOnClickListener(v -> onBackPressed());

    }

    private void configuraSpinner() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(activity,R.array.lista_paises,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void geraViews() {
        recyclerView = findViewById(R.id.procurar_favoritos_recycler_view_id);
        setaVoltar = findViewById(R.id.procurar_favoritos_seta_voltar_id);
        viewModelTeam = ViewModelProviders.of(this).get(ViewModelTeam.class);
        spinner =  findViewById(R.id.procurar_favoritos_spinner_pais_id);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if(text.equals("Selecione um país...")){
            adapter.clearApplications();
        }
        if(text.equals("Brasil")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4351);//Brasil Serie A

            Handler handler = new Handler();
            handler.postDelayed(() -> {
            viewModelTeam.getTeamsLeague(4404);//Brasil Serie B
            }, 2000);
        }
        if(text.equals("Inglaterra")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4328);
        }
        if(text.equals("Alemanha")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4331);
        }
        if(text.equals("França")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4334);
        }
        if(text.equals("Espanha")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4335);
        }
        if(text.equals("Itália")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4332);
        }
        if(text.equals("Argentina")){
            adapter.clearApplications();
            viewModelTeam.getTeamsLeague(4406);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        adapter.clearApplications();

    }
}
package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListaFavoritosAdapter;
import com.example.myapplication.model.Time;

import java.util.ArrayList;
import java.util.List;

public class ListaFavoritosActivity extends AppCompatActivity {

    private Button btnAddTime;
    private ImageView setaVoltar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListaFavoritosAdapter adapter;
    private List<Time> timeList = new ArrayList<>();


    private Activity activity=this;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);

        geraViews();

        layoutManager = new GridLayoutManager(this,2);
        adapter = new ListaFavoritosAdapter(addTime());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnAddTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent procurarFavoritoIntent = new Intent(activity,ProcurarFavoritosActivity.class);
                startActivity(procurarFavoritoIntent);
            }
        }));
        setaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity,MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }

    private void geraViews() {
        recyclerView = findViewById(R.id.procurar_favoritos_recycler_view_id);
        btnAddTime = findViewById(R.id.lista_favoritos_add_time_id);
        setaVoltar = findViewById(R.id.lista_favoritos_seta_voltar_id);
    }


    private static List<Time> addTime(){
        List<Time> timesList = new ArrayList<>();

        timesList.add(new Time(R.drawable.time_escudo,"São Paulo Futebol Clube"));
        timesList.add(new Time(R.drawable.time_escudo2,"Ceará"));
        timesList.add(new Time(R.drawable.time_escudo3,"Poços de Caldas"));
        timesList.add(new Time(R.drawable.time_escudo4,"Sei la o nome"));
        timesList.add(new Time(R.drawable.time_escudo5,"Chapecoense"));
        timesList.add(new Time(R.drawable.time_escudo5,"Chapecoense"));
        timesList.add(new Time(R.drawable.time_escudo5,"Chapecoense"));
        timesList.add(new Time(R.drawable.time_escudo5,"Chapecoense"));
        timesList.add(new Time(R.drawable.time_escudo5,"Chapecoense"));

        return timesList;
    }

}

package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.adapter.ListaAddFavoritosAdapter;
import com.example.myapplication.fragment.FavoritosEscudoFragment;
import com.example.myapplication.R;
import com.example.myapplication.model.Time;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProcurarFavoritosActivity extends AppCompatActivity {

    private FloatingActionButton btnAddTime;
    private ImageView setaVoltar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private ListaFavoritosAdapter adapter;
    private ListaAddFavoritosAdapter adapter;
    private List<Time> timeList = new ArrayList<>();

    private Activity activity=this;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_favoritos);

        recyclerView = findViewById(R.id.lista_add_favoritos_recycler_view_id);

        btnAddTime = findViewById(R.id.procurar_favorito_floatingActionButton_id);
        setaVoltar = findViewById(R.id.procurar_favoritos_seta_voltar_id);

        layoutManager = new GridLayoutManager(this,2);
        adapter = new ListaAddFavoritosAdapter(addTime());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnAddTime.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Time adicionado a lista de favoritos.", Toast.LENGTH_LONG).show();
                Intent listaFavoritosIntent = new Intent(activity,ListaFavoritosActivity.class);
                startActivity(listaFavoritosIntent);
            }
        }));
        setaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //carregaFragment(new FavoritosEscudoFragment());

    }

//    public void carregaFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.lista_add_favoritos_containerfrag_id, fragment);
//        //fragmentTransaction.replace(R.id.lista_favoritos_containerfrag_id, fragment);
//        fragmentTransaction.commit();
//    }

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

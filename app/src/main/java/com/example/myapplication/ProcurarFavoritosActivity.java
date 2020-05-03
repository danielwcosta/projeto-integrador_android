package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ProcurarFavoritosActivity extends AppCompatActivity {

    private ImageView setaVoltar;
    private FloatingActionButton btnAddTime;

    private Activity activity=this;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_favoritos);

        btnAddTime = findViewById(R.id.procurar_favorito_floatingActionButton_id);
        setaVoltar = findViewById(R.id.procurar_favoritos_seta_voltar_id);

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


        carregaFragment(new FavoritosEscudoFragment());

    }

    public void carregaFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.procurar_favorito_containerfrag_id, fragment);
        fragmentTransaction.commit();
    }
}

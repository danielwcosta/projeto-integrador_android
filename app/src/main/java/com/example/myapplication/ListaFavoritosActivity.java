package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ListaFavoritosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Button btnAddTime, btnRemoveTime;
    private ImageView setaVoltar;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    private Activity activity=this;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);

        carregaFragment(new TimeFavoritoFragment());

        btnAddTime = findViewById(R.id.lista_favoritos_add_time_id);
        btnRemoveTime = findViewById(R.id.lista_favoritos_remover_time_id);
        setaVoltar = findViewById(R.id.lista_favoritos_seta_voltar_id);

        spinner = findViewById(R.id.lista_favoritos_box_times_id);
        adapter = ArrayAdapter.createFromResource(activity,R.array.lista_times_favoritos,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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

    public void carregaFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lista_favoritos_containerfrag_id, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

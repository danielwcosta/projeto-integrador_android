package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.fragment.PerguntaFragment;
import com.example.myapplication.R;

public class PerguntaActivity extends AppCompatActivity {

    private Button btnSair, btnResponder;
    private Activity activity = this;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        carregaFragment(new PerguntaFragment());

        btnSair = findViewById(R.id.PerguntaActivity_buttonSair);
        btnResponder = findViewById(R.id.PerguntaActivity_buttonResponder);

        btnSair.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity, MainActivity.class);
                startActivity(mainIntent);
            }
        }));

        btnResponder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "ACERTOOO MISERAVI!!!.", Toast.LENGTH_LONG).show();
                Intent perguntaIntent = new Intent(activity,PerguntaActivity.class);
                startActivity(perguntaIntent);
            }
        }));
    }

    public void carregaFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PerguntaActivity_fragmentPergunta, fragment);
        fragmentTransaction.commit();
    }

}

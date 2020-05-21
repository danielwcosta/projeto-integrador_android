package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.fragment.PerguntaFragment;
import com.example.myapplication.R;

public class PerguntaActivity extends AppCompatActivity {

    private Button  btnResponder;
    private ImageView btnSair;
    private Activity activity = this;
    private FragmentManager fragmentManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        carregaFragment(new PerguntaFragment());

        btnSair = findViewById(R.id.PerguntaActivity_seta_voltar_id);

        startAnimation();

        btnSair.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(activity, MainActivity.class);
                startActivity(mainIntent);
            }



        }));

    }

    public void carregaFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PerguntaActivity_fragmentPergunta, fragment);
        fragmentTransaction.commit();
    }
    private void startAnimation(){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.PerguntaActivity_progress_bar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
        progressAnimator.setDuration(5000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }
}

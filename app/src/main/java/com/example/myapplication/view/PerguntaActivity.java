package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.fragment.FavoritosEscudoFragment;
import com.example.myapplication.fragment.PerguntaActivityContract;
import com.example.myapplication.fragment.PerguntaFragment;
import com.example.myapplication.R;

public class PerguntaActivity extends AppCompatActivity implements PerguntaActivityContract {

    private Button  btnResponder;
    private ImageView btnSair;
    private FragmentManager fragmentManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        carregaFragment(new PerguntaFragment());


  //      startAnimation();

        btnSair = findViewById(R.id.PerguntaActivity_seta_voltar_id);
        btnSair.setOnClickListener((v -> onBackPressed()));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // gera erro ao clicar no btnSair verificar



    public void carregaFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PerguntaActivity_fragmentPergunta, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.replace(R.id.PerguntaActivity_fragmentPergunta, fragment); //não sei pq mas só funciona com isso
        fragmentTransaction.commit();
    }
    public void endAnimation(ObjectAnimator progressAnimator){progressAnimator.end();}
    private void startAnimation(){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.PerguntaActivity_progress_bar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
        progressAnimator.setDuration(5000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        progressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                toast();
                //               removeFragment(new PerguntaFragment()); // trava app qdo sai da activity
                Handler handler = new Handler();

                handler.postDelayed(() -> {
 //                   carregaFragment(new PerguntaFragment()); // trava app qdo sai da activity
//                    progressAnimator.start();
                }, 2500);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                progressAnimator.end();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void toast() {
        Toast.makeText(this, "Acabou o tempo!!!!", Toast.LENGTH_SHORT).show();
    }
}

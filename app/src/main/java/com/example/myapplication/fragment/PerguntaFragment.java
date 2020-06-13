package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.view.PerguntaActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerguntaFragment extends Fragment {

    PerguntaActivityContract perguntaActivityContract;

    public PerguntaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_pergunta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnResposta1 = (Button) view.findViewById(R.id.PerguntaFragment_btn_resposta1);
        Button btnResposta2 = (Button) view.findViewById(R.id.PerguntaFragment_btn_resposta2);
        Button btnResposta3 = (Button) view.findViewById(R.id.PerguntaFragment_btn_resposta3);
        Button btnResposta4 = (Button) view.findViewById(R.id.PerguntaFragment_btn_resposta4);

        respondeuCerto(btnResposta1);
        respondeuCerto(btnResposta2);
        respondeuErrado(btnResposta3);
        respondeuErrado(btnResposta4);
    }

    private void respondeuCerto(Button button) {
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "ACERTOOO MISERAVI!!!.", Toast.LENGTH_SHORT).show();
 //               Intent perguntaIntent = new Intent(v.getContext(), PerguntaActivity.class);
 //               startActivity(perguntaIntent);
            }
        }));
    }
    private void respondeuErrado(Button button) {
        button.setOnClickListener((v -> {
            Toast.makeText(v.getContext(), "ERROOOUUU!!! Burrão .", Toast.LENGTH_SHORT).show();
//                Intent perguntaIntent = new Intent(v.getContext(), PerguntaActivity.class);
//                startActivity(perguntaIntent);

//            perguntaActivityContract.substituiFragment(new PerguntaFragment());
        }));
    }

}


package com.example.myapplication.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Pergunta;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.myapplication.util.UtilKt.estaConectado;


public class PerguntaFragment extends Fragment {

    private ImageView imgPergunta,imgRespondeu;
    private TextView txtPlacar, txtPergunta;
    private Button btnAlternativa1, btnAlternativa2, btnAlternativa3, btnAlternativa4;
    private List<Pergunta> listaperguntas;
    private boolean semDuploClick = true;
    private int acertos = 0, erros = 0;

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

        initViews(view);

        criaPerguntaNaTela(view);
    }

    private void initViews(@NonNull View view) {
        txtPlacar = view.findViewById(R.id.PerguntaFragment_textViewPlacar);
        imgPergunta = view.findViewById(R.id.PerguntaFragment_imageViewPergunta);
        imgRespondeu = view.findViewById(R.id.PerguntaActivity_imgRespondeu);
        txtPergunta = view.findViewById(R.id.PerguntaFragment_textViewPergunta);
        btnAlternativa1 = view.findViewById(R.id.PerguntaFragment_btn_resposta1);
        btnAlternativa2 = view.findViewById(R.id.PerguntaFragment_btn_resposta2);
        btnAlternativa3 = view.findViewById(R.id.PerguntaFragment_btn_resposta3);
        btnAlternativa4 = view.findViewById(R.id.PerguntaFragment_btn_resposta4);
    }

    @SuppressLint("SetTextI18n")
    private void criaPerguntaNaTela(@NonNull View view) {
        imgRespondeu.setImageResource(0);
        imgRespondeu.setBackgroundColor(0);
        txtPlacar.setText("Acertos " + acertos + " x " + erros + " Erros");
        semDuploClick = true;

        if(estaConectado(view.getContext())){carregaTodasPerguntas();}
        else{ carregaPerguntasOffline();}

        montaPergunta();
    }

    private void montaPergunta() {
        Random aleatorio = new Random();
        int posicaoPergunta = aleatorio.nextInt(listaperguntas.size());
        String respostaCerta = listaperguntas.get(posicaoPergunta).getResposta();

        if (!listaperguntas.get(posicaoPergunta).getImagem().isEmpty() ) {
            Picasso.get().load(listaperguntas.get(posicaoPergunta).getImagem()).into(imgPergunta);
            txtPergunta.setText(listaperguntas.get(posicaoPergunta).getPergunta());
            btnAlternativa1.setText(listaperguntas.get(posicaoPergunta).getAlternativa1());
            btnAlternativa2.setText(listaperguntas.get(posicaoPergunta).getAlternativa2());
            btnAlternativa3.setText(listaperguntas.get(posicaoPergunta).getAlternativa3());
            btnAlternativa4.setText(listaperguntas.get(posicaoPergunta).getAlternativa4());
        }else{
        imgPergunta.setImageResource(R.drawable.logo_app);
        txtPergunta.setText(listaperguntas.get(posicaoPergunta).getPergunta());
        btnAlternativa1.setText(listaperguntas.get(posicaoPergunta).getAlternativa1());
        btnAlternativa2.setText(listaperguntas.get(posicaoPergunta).getAlternativa2());
        btnAlternativa3.setText(listaperguntas.get(posicaoPergunta).getAlternativa3());
        btnAlternativa4.setText(listaperguntas.get(posicaoPergunta).getAlternativa4());
        }

        btnAlternativa1.setOnClickListener(v -> {
            if(semDuploClick){
                 if(listaperguntas.get(posicaoPergunta).getAlternativa1().equals(respostaCerta)){respondeuCerto();}
                 else{respondeuErrado();}
                }
        });
        btnAlternativa2.setOnClickListener(v -> {
            if(semDuploClick){
                if(listaperguntas.get(posicaoPergunta).getAlternativa2().equals(respostaCerta)){respondeuCerto();}
                else{respondeuErrado();}
                }
        });
        btnAlternativa3.setOnClickListener(v -> {
            if(semDuploClick){
                if(listaperguntas.get(posicaoPergunta).getAlternativa3().equals(respostaCerta)){respondeuCerto();}
                else{respondeuErrado();}
                }
        });
        btnAlternativa4.setOnClickListener(v -> {
            if(semDuploClick){
                if(listaperguntas.get(posicaoPergunta).getAlternativa4().equals(respostaCerta)){respondeuCerto();}
                else{respondeuErrado();}
                }
        });
    }

    @SuppressLint({"SetTextI18n"})
    private void respondeuCerto() {
        acertos++;
        txtPlacar.setText("Acertos " + acertos + " x " + erros + " Erros");
        imgRespondeu.setImageResource(R.drawable.gol);
        imgRespondeu.setBackgroundColor(getResources().getColor(R.color.colorAcertou));
        semDuploClick = false;
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            criaPerguntaNaTela(getView());
        }, 1000);
            }

    @SuppressLint({"SetTextI18n"})
    private void respondeuErrado() {
        erros++;
        txtPlacar.setText("Acertos " + acertos + " x " + erros + " Erros");
        imgRespondeu.setImageResource(R.drawable.defendeu);
        imgRespondeu.setBackgroundColor(getResources().getColor(R.color.colorErrou));
        semDuploClick = false;
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            criaPerguntaNaTela(getView());
        }, 1000);
        };

    private String carregaJsonDoAsset(String file) {
        String json = "";
        try {
            InputStream is = getContext().getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void carregaTodasPerguntas() {
        listaperguntas = new ArrayList<>();
        String jsonStr = carregaJsonDoAsset(getString(R.string.json));
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray perguntas = jsonObject.getJSONArray(getString(R.string.perguntas));
            for (int i = 0; i < perguntas.length(); i++) {
                JSONObject pergunta = perguntas.getJSONObject(i);

                int idString = pergunta.getInt(getString(R.string.pergunta_id));
                int idTipoString = pergunta.getInt(getString(R.string.pergunta_id_tipo));
                String tipoString = pergunta.getString(getString(R.string.pergunta_tipo));
                String imagemString = pergunta.getString(getString(R.string.pergunta_imagem));
                String perguntaString = pergunta.getString(getString(R.string.pergunta_pergunta));
                String alternativa1String = pergunta.getString(getString(R.string.pergunta_alternativa1));
                String alternativa2String = pergunta.getString(getString(R.string.pergunta_alternativa2));
                String alternativa3String = pergunta.getString(getString(R.string.pergunta_alternativa3));
                String alternativa4String = pergunta.getString(getString(R.string.pergunta_alternativa4));
                String respostaString = pergunta.getString(getString(R.string.pergunta_resposta));

                listaperguntas.add(new Pergunta(
                        idString,
                        idTipoString,
                        tipoString,
                        imagemString,
                        perguntaString,
                        alternativa1String,
                        alternativa2String,
                        alternativa3String,
                        alternativa4String,
                        respostaString
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void carregaPerguntasOffline() {
        listaperguntas = new ArrayList<>();
        String jsonStr = carregaJsonDoAsset(getString(R.string.json));

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray perguntas = jsonObject.getJSONArray(getString(R.string.perguntas));

            for (int i = 0; i < perguntas.length();) {
                JSONObject pergunta = perguntas.getJSONObject(i);

                int idString = pergunta.getInt(getString(R.string.pergunta_id));
                int idTipoString = pergunta.getInt(getString(R.string.pergunta_id_tipo));
                String tipoString = pergunta.getString(getString(R.string.pergunta_tipo));
                String imagemString = pergunta.getString(getString(R.string.pergunta_imagem));
                String perguntaString = pergunta.getString(getString(R.string.pergunta_pergunta));
                String alternativa1String = pergunta.getString(getString(R.string.pergunta_alternativa1));
                String alternativa2String = pergunta.getString(getString(R.string.pergunta_alternativa2));
                String alternativa3String = pergunta.getString(getString(R.string.pergunta_alternativa3));
                String alternativa4String = pergunta.getString(getString(R.string.pergunta_alternativa4));
                String respostaString = pergunta.getString(getString(R.string.pergunta_resposta));

            if( tipoString.equalsIgnoreCase("strStadiumThumb")
                || tipoString.equalsIgnoreCase("strTeamBadge")
                || tipoString.equalsIgnoreCase("strTeamJersey")){
                i++;
            }else{
                listaperguntas.add(new Pergunta(
                        idString,
                        idTipoString,
                        tipoString,
                        imagemString,
                        perguntaString,
                        alternativa1String,
                        alternativa2String,
                        alternativa3String,
                        alternativa4String,
                        respostaString
                ));
                i++;
            }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
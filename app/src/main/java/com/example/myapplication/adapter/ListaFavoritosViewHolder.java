package com.example.myapplication.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ListaFavoritosViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public ImageView imgEscudoFavorito;

    public ListaFavoritosViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardview_lista_fav_cardview_id);
        imgEscudoFavorito = itemView.findViewById(R.id.cardview_lista_fav_img_id);
    }
}

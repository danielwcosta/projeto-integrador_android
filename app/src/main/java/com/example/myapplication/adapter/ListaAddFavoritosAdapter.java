package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Time;
import com.example.myapplication.view.TimeFavoritoActivity;

import java.util.List;

public class ListaAddFavoritosAdapter extends RecyclerView.Adapter<ListaAddFavoritosViewHolder> {

    private List<Time> timeList;

    public ListaAddFavoritosAdapter(List<Time> timeList) {
        this.timeList = timeList;
    }

    @NonNull
    @Override
    public ListaAddFavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lista_fav_escudo,viewGroup,false);
        return new ListaAddFavoritosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAddFavoritosViewHolder holder, int position) {
        final Time time = timeList.get(position);
        holder.imgEscudoFavorito.setImageResource(time.getImgEscudo());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(context,time.getNomeTime(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, TimeFavoritoActivity.class);
                intent.putExtra("time",time);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }
}

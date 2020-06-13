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
import com.example.myapplication.model.Team;
import com.example.myapplication.view.InfoTimeActivity;
import com.example.myapplication.view.TimeFavoritoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProcurarFavoritosAdapter extends RecyclerView.Adapter<ProcurarFavoritosViewHolder> {

    private List<Team> timeList;

    public ProcurarFavoritosAdapter(List<Team> timeList) {
        this.timeList = timeList;
    }

    @NonNull
    @Override
    public ProcurarFavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_lista_fav_escudo,viewGroup,false);
        return new ProcurarFavoritosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcurarFavoritosViewHolder holder, int position) {
        final Team time = timeList.get(position);
        Picasso.get().load(time.getStrTeamBadge()).into(holder.imgEscudoFavorito);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, InfoTimeActivity.class);
                intent.putExtra("time",time);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public void setUpdate(List<Team> results) {
        if (this.timeList.isEmpty()){
            this.timeList = results;
        }else {
            this.timeList.addAll(results);
        }
        notifyDataSetChanged();
    }
    public void clearApplications() {
        int size = timeList.size();
        timeList.clear();
        notifyItemRangeRemoved(0, size);
    }


}

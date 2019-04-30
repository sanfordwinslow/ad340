package com.san.agster;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.squareup.picasso.Picasso;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);
    }

    private String[][] movies;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;
        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    public MovieRecyclerAdapter(String[][] movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    @Override
    public MovieRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recyclerview, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CardView cardView = holder.layout;
        TextView title = (TextView)cardView.findViewById(R.id.title);
        TextView year = (TextView)cardView.findViewById(R.id.year);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);
        Context context = cardView.getContext();
        String[] movie = movies[position];
        String url = movie[3];
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
        title.setText(movie[0]);
        year.setText(movie[1]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }



}

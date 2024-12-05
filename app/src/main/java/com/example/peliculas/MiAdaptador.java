package com.example.peliculas;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {

    TextView textoTitulo;
    ArrayList<Pelicula> peliculas;
    int selectedPos = RecyclerView.NO_POSITION;

    public MiAdaptador(ArrayList<Pelicula> peliculas, TextView textViewP) {
        textoTitulo = textViewP;
        this.peliculas = peliculas;
    }


    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int nuevaPos) {
        if (nuevaPos == this.selectedPos) {
            this.selectedPos = RecyclerView.NO_POSITION;
            notifyItemChanged(nuevaPos);
        } else {
            if (this.selectedPos >= 0) {
                notifyItemChanged(this.selectedPos);
            }
            this.selectedPos = nuevaPos;
            notifyItemChanged(nuevaPos);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda,
                parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento);
        return mvh;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pelicula peli = this.peliculas.get(position);
        holder.getTitulo().setText(peli.getTitulo());
        holder.getDirector().setText(peli.getDirector());
        holder.getLogo().setImageResource(peli.getPortada());
        holder.getClasificacion().setImageResource(peli.getClasi());


        if (selectedPos == position) {
            holder.itemView.setBackgroundResource(R.color.red);
        } else {
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fondoPelicula;
        private TextView titulo;
        private TextView director;
        ImageView clasificacion;

        public MyViewHolder(View viewElemento) {
            super(viewElemento);

            this.fondoPelicula = viewElemento.findViewById(R.id.ImgPelicula);
            this.titulo = viewElemento.findViewById(R.id.Titulo);
            this.director = viewElemento.findViewById(R.id.Director);
            this.clasificacion = viewElemento.findViewById(R.id.clasificacion);

            viewElemento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posPulsada = getAdapterPosition();
                    setSelectedPos(posPulsada);

                    Log.i(TAG, selectedPos+"");

                    if (selectedPos > RecyclerView.NO_POSITION) {
                        textoTitulo.setText(peliculas.get(selectedPos).getTitulo());
                    }else{
                        textoTitulo.setText("");
                    }
                }
            });

        }

        public TextView getTitulo() {
            return titulo;
        }

        public TextView getDirector() {
            return director;
        }

        public ImageView getLogo() {
            return fondoPelicula;
        }

        public ImageView getClasificacion() {
            return clasificacion;
        }
    }
}

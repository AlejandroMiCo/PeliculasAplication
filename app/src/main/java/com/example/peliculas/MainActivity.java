package com.example.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Pelicula> peliculas;
    RecyclerView rv;
    MiAdaptador miAdaptador;
    Toolbar toolbar;
    TextView txtnombre;
    boolean oneRow = true;
    boolean isLikedPress = false;
    ArrayList<Pelicula> pelisFiltradas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Datos datos = Datos.getInstance();

        if (!datos.hasExtra("peliculas")){
            datos.putExtra("peliculas", datos.rellenaPeliculas());
        }
        peliculas = (ArrayList<Pelicula>) datos.getExtra("peliculas");

        Toolbar barraDeHerramientas = findViewById(R.id.toolbar);

        setSupportActionBar(barraDeHerramientas);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Peliculas");
        actionBar.setSubtitle(peliculas.size() + "");


        txtnombre = findViewById(R.id.textView);

        miAdaptador = new MiAdaptador(peliculas, txtnombre);
        rv = findViewById(R.id.rv);
        GridLayoutManager miLayoutManager = new GridLayoutManager(this, 1);


        rv.setLayoutManager(miLayoutManager);

        rv.setAdapter(miAdaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pelis) {
            Toast.makeText(this, "pelis", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.like) {
            Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivityListView.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.add) {
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
            Intent intentAdd = new Intent(MainActivity.this, ActivityAdd.class);
            startActivity(intentAdd);

            return true;
        } else if (id == R.id.colums) {
            if (oneRow) {
                rv.setLayoutManager(new GridLayoutManager(this, 2));
                item.setIcon(R.drawable.two);
                oneRow = false;
            } else {
                rv.setLayoutManager(new GridLayoutManager(this, 1));
                item.setIcon(R.drawable.one);
                oneRow = true;
            }
            return true;
        } else if (id == R.id.likeFilter) {
            isLikedPress = !isLikedPress;

            if (isLikedPress){
                pelisFiltradas = new ArrayList<>();
                for (int i = 0; i < peliculas.size(); i++) {
                    if (peliculas.get(i).getFavorita()){
                        pelisFiltradas.add(peliculas.get(i));
                    }
                }

                miAdaptador = new MiAdaptador(pelisFiltradas, txtnombre);
                rv.setAdapter(miAdaptador);

            }else{
                miAdaptador = new MiAdaptador(peliculas, txtnombre);
                rv.setAdapter(miAdaptador);

            }

            Toast.makeText(this, "filter", Toast.LENGTH_SHORT).show();
            return true;
        }
        ;
        return super.onOptionsItemSelected(item);
    }
}

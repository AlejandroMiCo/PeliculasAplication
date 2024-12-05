package com.example.peliculas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivityListView extends AppCompatActivity {

    ArrayAdapter<Pelicula> adaptador;
    ListView lw;
    ArrayList<Pelicula> peliculas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_list_view);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        lw = findViewById(R.id.listView);
        lw.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        peliculas = Datos.getInstance().getPelis("peliculas");

        adaptador = new ArrayAdapter<Pelicula>(this, android.R.layout.simple_list_item_checked, peliculas);

        lw.setAdapter(adaptador);

        for (int i = 0; i < peliculas.size(); i++) {
            lw.setItemChecked(i, peliculas.get(i).getFavorita());
        }


        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnGuardar) {
            for (int i = 0; i < lw.getCount(); i++) {
                peliculas.get(i).setFavorita(lw.isItemChecked(i));
            }

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
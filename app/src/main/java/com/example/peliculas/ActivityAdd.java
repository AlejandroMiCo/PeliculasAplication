package com.example.peliculas;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.HashMap;

public class ActivityAdd extends AppCompatActivity {

    Toolbar tlbNuevaPelicula;
    String[] salas;
    Spinner spinner;
    boolean muestra;
    Pelicula nuevaPelicula;
    EditText txtTitulo;
    EditText txtDirector;
    EditText txtDuracion;
    RadioGroup rdg;
    CalendarView cld;
    HashMap<Integer,Integer> clasis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nuevaPelicula = new Pelicula();

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Nueva pelicula");

        actionBar.setDisplayHomeAsUpEnabled(true);


        txtTitulo = findViewById(R.id.editTitulo);
        txtDirector = findViewById(R.id.editDirector);
        txtDuracion = findViewById(R.id.editTextText3);
        rdg = findViewById(R.id.radioGroup);
        cld = findViewById(R.id.calendarView);

        muestra = true;


        final String[] salas = getResources().getStringArray(R.array.salas);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, salas);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!muestra) muestra = true;
                else {
                    nuevaPelicula.setSala(adapterView.getItemAtPosition(i).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clasis = new HashMap<>();
        clasis.put(R.id.rdG, R.drawable.g);
        clasis.put(R.id.rdPg, R.drawable.pg);
        clasis.put(R.id.rdR, R.drawable.r);
        clasis.put(R.id.rdPg13, R.drawable.pg13);
        clasis.put(R.id.rdNc17, R.drawable.nc17);
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
            if (!txtTitulo.getText().toString().isEmpty() && !txtDirector.getText().toString().isEmpty() && !txtDuracion.getText().toString().isEmpty()) {
                nuevaPelicula.setTitulo(txtTitulo.getText().toString());
                nuevaPelicula.setDirector(txtDirector.getText().toString());
                nuevaPelicula.setDuracion(Integer.parseInt(txtDuracion.getText().toString()));
                nuevaPelicula.setClasi(clasis.get(rdg.getCheckedRadioButtonId()));
                nuevaPelicula.setFecha(new Date(cld.getDate()));
                Datos.getInstance().getPelis("peliculas").add(nuevaPelicula);
                finish();
            } else {
                Toast.makeText(this, "Introduce todos los datos", Toast.LENGTH_LONG).show();
            }

            finish();
        }

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}
package com.example.semana12sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.semana12sqlite.Controlador.DatabaseController;
import com.example.semana12sqlite.R;

public class listarActivity extends AppCompatActivity {
    private DatabaseController dbController;
    private ListView listViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        dbController = new DatabaseController(this);
        listViewDatos = findViewById(R.id.listView); // Ajusta el ID según tu diseño

        // Llena el ListView con todos los datos al iniciar la actividad
        llenarListViewConDatos();
    }

    private void llenarListViewConDatos() {
        // Obtiene todos los datos desde el DatabaseController
        Cursor cursor = dbController.getAllData();

        // Configura el adaptador para el ListView
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2, // Puedes ajustar el diseño del item de la lista
                cursor,
                new String[]{"nombre", "correo"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);

        // Asigna el adaptador al ListView
        listViewDatos.setAdapter(adapter);
    }
}
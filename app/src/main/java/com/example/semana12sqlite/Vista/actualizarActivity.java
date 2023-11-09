package com.example.semana12sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.semana12sqlite.Controlador.DatabaseController;
import com.example.semana12sqlite.R;

import java.util.List;

public class actualizarActivity extends AppCompatActivity {
    private DatabaseController dbController;
    private Spinner spinnerIds;
    private EditText etNombre, etCorreo;
    private int idSpinner;
    private Button btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        dbController = new DatabaseController(this);
        spinnerIds = findViewById(R.id.spinner);
        etNombre = findViewById(R.id.nombre);
        etCorreo = findViewById(R.id.correo);
        btnActualizar = findViewById(R.id.modificarUsuario);
        btnEliminar = findViewById(R.id.eliminarUsuario);

        // Llena el Spinner con las IDs al iniciar la actividad
        llenarSpinnerConIds();

        spinnerIds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idSpinner = Integer.parseInt(spinnerIds.getSelectedItem().toString());
                mostrarDetalles(idSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aNombre = etNombre.getText().toString();
                String aCorreo = etCorreo.getText().toString();

                dbController.updateData(idSpinner, aNombre, aCorreo);
                Toast.makeText(actualizarActivity.this, "El registro ha sido actualizado", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbController.deleteData(idSpinner);
            }
        });

    }

    private void llenarSpinnerConIds(){
        List<Integer> ids = dbController.obtenerIDs();

        // Crea un ArrayAdapter para el Spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ids);

        // Especifica el diseño del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asigna el ArrayAdapter al Spinner
        spinnerIds.setAdapter(adapter);
    }

    private void mostrarDetalles(int selectedId) {
        // Realiza una consulta a la base de datos para obtener los detalles
        // Puedes adaptar esta lógica según la estructura de tu base de datos
        Cursor cursor = dbController.getDataById(selectedId);

        if (cursor != null && cursor.moveToFirst()) {
            // Supongamos que "nombre" y "otroDato" son columnas de tu tabla
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range") String correo = cursor.getString(cursor.getColumnIndex("correo"));

            // Actualiza los EditText con los datos obtenidos
            etNombre.setText(nombre);
            etCorreo.setText(correo);
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}
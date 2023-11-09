package com.example.semana12sqlite.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12sqlite.Controlador.DatabaseController;
import com.example.semana12sqlite.R;

public class registrarActivity extends AppCompatActivity {

    private DatabaseController dbController;
    private EditText etNombre, etCorreo;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        dbController = new DatabaseController(this);
        etNombre = findViewById(R.id.nombre);
        etCorreo = findViewById(R.id.correo);
        btnRegistrar = findViewById(R.id.registrarUsuario);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String correo = etCorreo.getText().toString();

                if(!TextUtils.isEmpty(nombre) || !TextUtils.isEmpty(correo)){
                    dbController.insertData(nombre, correo);
                    Toast.makeText(registrarActivity.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registrarActivity.this, "Ingresa un nombre o correo valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
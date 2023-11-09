package com.example.semana12sqlite.Controlador;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.semana12sqlite.Modelo.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private DatabaseHelper dbHelper;

    public DatabaseController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Método para insertar datos en la base de datos
    public void insertData(String nombre, String correo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("correo", correo);
        db.insert("Usuarios", null, values);
        db.close();
    }

    // Método para consultar todos los datos de la base de datos
    public Cursor getAllData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"_id", "nombre", "correo"};
        return db.query("Usuarios", projection, null, null, null, null, null);
    }

    // Método para actualizar datos en la base de datos
    public void updateData(int id, String nuevoNombre, String nuevoCorreo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nuevoNombre);
        values.put("correo", nuevoCorreo);
        db.update("Usuarios", values, "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para eliminar datos de la base de datos
    public void deleteData(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Usuarios", "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Integer> obtenerIDs() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"_id"};
        Cursor cursor = db.query("Usuarios", projection, null, null, null, null, null);

        List<Integer> idList = new ArrayList<>();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("_id"));
            idList.add(id);
        }

        cursor.close();
        db.close();

        return idList;
    }

    public Cursor getDataById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define las columnas que deseas recuperar
        String[] projection = {"nombre", "correo"}; // Ajusta según las columnas de tu tabla

        // Especifica la cláusula WHERE para la consulta
        String selection = "_id=?";
        String[] selectionArgs = {String.valueOf(id)};

        // Realiza la consulta
        return db.query("Usuarios", projection, selection, selectionArgs, null, null, null);
    }
}

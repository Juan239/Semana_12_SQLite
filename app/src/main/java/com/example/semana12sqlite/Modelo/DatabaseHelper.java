package com.example.semana12sqlite.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "baseDeDatosSQLite.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crea tus tablas aquí
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, correo TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Maneja la actualización de la base de datos aquí
        db.execSQL("DROP TABLE IF EXISTS MiTabla;");
        onCreate(db);
    }
}

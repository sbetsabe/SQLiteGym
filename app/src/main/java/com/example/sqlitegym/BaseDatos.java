package com.example.sqlitegym;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    String tablaAlumnos = "CREATE TABLE Alumno (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Edad INTEGER, Correo TEXT)";
    String tablaHistorico = "CREATE TABLE Historico (Id INTEGER PRIMARY KEY AUTOINCREMENT, Fecha DATE, Nombre TEXT, Grasa INTEGER, MasaMuscular INTEGER, Peso INTEGER, EdadMetabolica INTEGER)";

    public BaseDatos (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaAlumnos);
        db.execSQL(tablaHistorico);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Alumno");
        db.execSQL("DROP TABLE IF EXISTS Historico");

        //Se crea la nueva versión de la tabla
        db.execSQL(tablaAlumnos);
        db.execSQL(tablaHistorico);
    }
}

package com.example.sqlitegym;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class VerAlumno extends AppCompatActivity {

    Button btnAgregarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veralumno);

        btnAgregarPerfil = (Button) findViewById(R.id.btnAgregarPerfil);

        btnAgregarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(VerAlumno.this, Alumno.class));
            }
        });

        cargarAlumnos();
    }

    private void cargarAlumnos() {
        BaseDatos datos = new BaseDatos(this, "DEMODB", null, 1); //creacion del puntero
        SQLiteDatabase db = datos.getWritableDatabase();

        if (db != null){
            Cursor c = db.rawQuery("select * from Alumno", null);
            int cantidad = c.getCount();
            int i = 0;
            String[] arreglo = new String[cantidad];
            if (c.moveToFirst()) {
                do {
                    String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getInt(2) + " " + c.getString(3);
                    ;
                    arreglo[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
            ListView lista = (ListView) findViewById(R.id.mostrarAlumnos);
            lista.setAdapter(adapter);
        }
    }
}

package com.example.sqlitegym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlumno;
    Button btnHistorico;

    Button btnVerAlumno;
    Button btnVerHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlumno = (Button) findViewById(R.id.btnAlumno);
        btnHistorico = (Button) findViewById(R.id.btnHistorico);
        btnVerAlumno = (Button) findViewById(R.id.btnVerAlumno);
        btnVerHistorico = (Button) findViewById(R.id.btnVerHistorico);

        btnAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(MainActivity.this, Alumno.class));
            }
        });;

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Historico.class));
            }
        });;

        btnVerAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View M) {
                startActivity(new Intent(MainActivity.this, VerAlumno.class));
            }
        });;

        btnVerHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View N) {
                startActivity(new Intent(MainActivity.this, VerHistorico.class));
            }
        });;
    }
}

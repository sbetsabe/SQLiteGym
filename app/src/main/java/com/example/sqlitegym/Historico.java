package com.example.sqlitegym;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Historico extends AppCompatActivity {

    private EditText editAlumno;
    private EditText editFecha;
    private EditText editGrasa;
    private EditText editMasa;
    private EditText editPeso;
    private EditText editEdadMetabolica;

    private static final String TAG = "Historico";;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button btnVer;
    Button Menuprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        editAlumno = (EditText) findViewById(R.id.editAlumno);
        editFecha = (EditText) findViewById(R.id.editFecha);
        editGrasa = (EditText) findViewById(R.id.editGrasa);
        editMasa = (EditText) findViewById(R.id.editMasa);
        editPeso = (EditText) findViewById(R.id.editPeso);
        editEdadMetabolica = (EditText) findViewById(R.id.editEdadMetabolica);

        btnVer = (Button) findViewById(R.id.btnVer);
        Menuprincipal = (Button) findViewById(R.id.Menuprincipal);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(Historico.this, VerHistorico.class));
            }
        });

        Menuprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View L) {
                startActivity(new Intent(Historico.this, MainActivity.class));
            }
        });

        editFecha = (EditText) findViewById(R.id.editFecha);

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View dat) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Historico.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG, "onDateSet: dd/mm/yy:" + dayOfMonth + "/" + month + "/" + year);

                String date = dayOfMonth + "/" + month + "/" + year;
                editFecha.setText(date);
            }
        };
    }

    public void GuardarHistorico(View view) {
        String alumno = editAlumno.getText().toString();
        String fecha = editFecha.getText().toString();
        String grasa = editGrasa.getText().toString();
        String masa = editMasa.getText().toString();
        String peso = editPeso.getText().toString();
        String edadMetabolica = editEdadMetabolica.getText().toString();

        BaseDatos dt = new BaseDatos(this, "DEMODB", null, 1);

        SQLiteDatabase db = dt.getWritableDatabase();
        if (db != null) {
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Fecha", fecha);
            registronuevo.put("Nombre", alumno);
            registronuevo.put("Grasa", grasa);
            registronuevo.put("MasaMuscular", masa);
            registronuevo.put("Peso", peso);
            registronuevo.put("EdadMetabolica", edadMetabolica);

            db.insert("Historico", null, registronuevo);
            Toast.makeText(this, "Datos Almacenados", Toast.LENGTH_SHORT).show();
        }
    }
}

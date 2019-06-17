package com.example.sqlitegym;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alumno extends AppCompatActivity {

    private EditText editNombre;
    private EditText editEdad;
    private EditText editCorreo;
    private EditText editArchivo;

    Button btnExaminar;
    Button btnMostrar;
    Button btnListarHistoricos;

    private final int PICKER  =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editEdad = (EditText) findViewById(R.id.editEdad);
        editCorreo = (EditText) findViewById(R.id.editCorreo);

        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnExaminar = (Button) findViewById(R.id.btnExaminar);
        btnListarHistoricos = (Button) findViewById(R.id.btnListaHistoricos);

        btnExaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pickfile();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(Alumno.this, VerAlumno.class));
            }
        });

        btnListarHistoricos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View M) {
                startActivity(new Intent(Alumno.this, VerHistorico.class));
            }
        });
    }

    private void Pickfile(){
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
        intent. setType("file /*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try{
            startActivityForResult(
                    Intent.createChooser(intent,"Seleccione un archivo para subir"),PICKER
            );

        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this,"Por favor, instale un administrador de archivos.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult (int requestCode , int resultCode, Intent data){
        switch (requestCode) {
            case PICKER:
                if (resultCode== RESULT_OK){
                    String FilePath = data. getData().getPath();
                    editArchivo.setText(FilePath);
                }
                break;

        }
    }

    public void GuardarDatos(View view) {
        String nombre = editNombre.getText().toString();
        String edad = editEdad.getText().toString();
        String correo = editCorreo.getText().toString();

        BaseDatos dat = new BaseDatos(this, "DEMODB", null, 1);

        SQLiteDatabase db = dat.getWritableDatabase();

        if(nombre.length() > 0 && edad.length() > 0 && correo.length() > 0){
            if (db != null) {
                ContentValues registronuevo = new ContentValues();
                registronuevo.put("Nombre", nombre);
                registronuevo.put("Edad", edad);
                registronuevo.put("Correo",correo);

                db.insert("Alumno", null, registronuevo);
                Toast.makeText(this, "Datos Almacenados", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese todos los datos solicitados", Toast.LENGTH_SHORT).show();
        }

        editNombre.setText("");
        editEdad.setText("");
        editCorreo.setText("");
    }
}

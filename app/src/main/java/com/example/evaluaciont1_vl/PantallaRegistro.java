package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.evaluaciont1_vl.datos.FileManager;
import com.example.evaluaciont1_vl.datos.NotasAlumnoAsig;


import java.sql.Time;

public class PantallaRegistro extends AppCompatActivity implements View.OnClickListener {
    private static final String CLAVE_ALUMNO_SELECCIONADO = "alumno_seleccionado";
    private static final String CLAVE_ASIGNATURA_SELECCIONADA = "asignatura_seleccionada";
    private static final String CLAVE_NOTA_EXAMEN ="nota_examen" ;
    private static final String CLAVE_NOTA_ACT = "nota_actividades";
    private static final String CLAVE_NOTA_FINAL = "nota_final" ;


    Button btnSeleccionarAlumno, btnSeleccionarAsignatura, btnCalcularNota, btnGuardarDatos, btnLimpiarDatos;
    EditText etAlumno, etAsignatura, etNotaExamen, etNotaActividades, etNotaFinal;

    private String SUCCESSFULL_SAVE = "Se han guardado los datos correctamente";
    private String NO_DATA = "Se han de introducir todos los datos";
    private String BAD_VALUES = "Se han de insertar valores no superiores a 10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);

        //BUTTONS
        btnSeleccionarAlumno = findViewById(R.id.btnAlumno);
        btnSeleccionarAsignatura = findViewById(R.id.btnAsignatura);
        btnCalcularNota = findViewById(R.id.btnCalcular);
        btnGuardarDatos = findViewById(R.id.btnGuardar);
        btnLimpiarDatos = findViewById(R.id.btnLimpiar);

        btnSeleccionarAlumno.setOnClickListener(this);
        btnSeleccionarAsignatura.setOnClickListener(this);
        btnCalcularNota.setOnClickListener(this);
        btnGuardarDatos.setOnClickListener(this);
        btnLimpiarDatos.setOnClickListener(this);

        //EDIT TEXT
        etAlumno = findViewById(R.id.etAlumno);
        etAsignatura = findViewById(R.id.etAsignatura);
        etNotaExamen = findViewById(R.id.etNotaExamen);
        etNotaActividades = findViewById(R.id.etNotaActividades);
        etNotaFinal = findViewById(R.id.etCalcular);




        if (savedInstanceState != null) {
            // Restaurar el estado si hay datos guardados
            etAlumno.setText(savedInstanceState.getString(CLAVE_ALUMNO_SELECCIONADO));
            etAsignatura.setText(savedInstanceState.getString(CLAVE_ASIGNATURA_SELECCIONADA));
            etNotaExamen.setText(savedInstanceState.getString(CLAVE_NOTA_EXAMEN));
            etNotaActividades.setText(savedInstanceState.getString(CLAVE_NOTA_ACT));;
            etNotaFinal.setText(savedInstanceState.getString(CLAVE_NOTA_FINAL));

        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardar el estado actual
        outState.putString(CLAVE_ALUMNO_SELECCIONADO, etAlumno.getText().toString());
        outState.putString(CLAVE_ASIGNATURA_SELECCIONADA, etAsignatura.getText().toString());
        outState.putString(CLAVE_NOTA_EXAMEN,etNotaExamen.getText().toString());
        outState.putString(CLAVE_NOTA_ACT, etNotaActividades.getText().toString());
        outState.putString(CLAVE_NOTA_FINAL,etNotaFinal.getText().toString());

    }


    @Override
    public void onClick(View v) {
        Intent i;
        if(v.getId() == R.id.btnAlumno){
            i = new Intent(this, PantallaSeleccionAlumno.class);
            startActivityForResult(i, 1);
        }else if(v.getId() == R.id.btnAsignatura){
            i = new Intent(this, PantallaSeleccionAsignatura.class);
            startActivityForResult(i, 2);
        }else if(v.getId() == R.id.btnCalcular){
            if(etNotaExamen.getText().toString().isEmpty() || etNotaActividades.getText().toString().isEmpty()){
                Toast.makeText(this, NO_DATA, Toast.LENGTH_LONG).show();
            }else{

                int notaExamen = Integer.parseInt(etNotaExamen.getText().toString()),
                        notaActividades = Integer.parseInt(etNotaActividades.getText().toString());

                if((notaExamen <= 10 && notaExamen > 0) && (notaActividades <= 10 && notaActividades > 0)) {
                    if (notaExamen >= 4 && notaActividades >= 7) {
                        etNotaFinal.setText("" + ((notaExamen * 0.6) + (notaActividades * 0.4)));
                    } else if (notaExamen < 4) {
                        etNotaFinal.setText("" + notaExamen);
                    } else if (notaActividades < 7) {
                        etNotaFinal.setText("" + ((notaExamen * 0.7) + (notaActividades * 0.3)));
                    }

                    btnCalcularNota.setEnabled(false);
                }else{
                    Toast.makeText(this, BAD_VALUES, Toast.LENGTH_LONG).show();
                }
            }




        }else if(v.getId() == R.id.btnGuardar) {

            String textAlumno = etAlumno.getText().toString(), textAsignatura = etAsignatura.getText().toString(),
                    textExamen = etNotaExamen.getText().toString(),
                    textActividades = etNotaActividades.getText().toString(),
                    textFinal = etNotaFinal.getText().toString();

            if (textAlumno.isEmpty() || textAsignatura.isEmpty() || textExamen.isEmpty() || textActividades.isEmpty() || textFinal.isEmpty()) {
                Toast.makeText(this, NO_DATA, Toast.LENGTH_LONG).show();

            }else if(Double.parseDouble(textExamen) > 10 || Double.parseDouble(textActividades) > 10 || Double.parseDouble(textFinal) > 10){
                Toast.makeText(this, BAD_VALUES, Toast.LENGTH_LONG).show();
            }else{
                FileManager.modificarNota(new NotasAlumnoAsig(textAlumno,
                        textAsignatura,
                        Double.parseDouble(textExamen),
                        Double.parseDouble(textActividades),
                        Double.parseDouble(textFinal)));

                Toast.makeText(this, SUCCESSFULL_SAVE, Toast.LENGTH_LONG).show();

            }

        }else if(v.getId() == R.id.btnLimpiar){
            etAlumno.getText().clear();
            etAsignatura.getText().clear();
            etNotaExamen.getText().clear();
            etNotaActividades.getText().clear();
            etNotaFinal.getText().clear();

            btnCalcularNota.setEnabled(true);
            btnSeleccionarAsignatura.setEnabled(true);
            btnSeleccionarAlumno.setEnabled(true);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                etAlumno.setText(data.getStringExtra("alumno"));
                btnSeleccionarAlumno.setEnabled(false);
            }
        }else if(requestCode == 2) {
            if(resultCode == RESULT_OK){
                etAsignatura.setText(data.getStringExtra("asignatura"));
                btnSeleccionarAsignatura.setEnabled(false);
            }
        }
    }
}
package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaluaciont1_vl.datos.FileManager;
import com.example.evaluaciont1_vl.datos.NotasAlumnoAsig;

public class PantallaRegistro extends AppCompatActivity implements View.OnClickListener {

    Button btnSeleccionarAlumno, btnSeleccionarAsignatura, btnCalcularNota, btnGuardarDatos, btnLimpiarDatos;
    EditText etAlumno, etAsignatura, etNotaExamen, etNotaActividades, etNotaFinal;

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
            int notaExamen = Integer.parseInt(etNotaExamen.getText().toString()),
                    notaActividades = Integer.parseInt(etNotaActividades.getText().toString());

            if(notaExamen >= 4 && notaActividades >= 7){
                etNotaFinal.setText("" + ((notaExamen * 0.6) + (notaActividades * 0.4)));
            }else if(notaExamen < 4){
                etNotaFinal.setText("" + notaExamen);
            }else if(notaActividades < 7){
                etNotaFinal.setText("" + ((notaExamen * 0.7) + (notaActividades * 0.3)));
            }

            btnCalcularNota.setEnabled(false);

        }else if(v.getId() == R.id.btnGuardar){
            FileManager.modificarNota(new NotasAlumnoAsig(etAlumno.getText().toString(), etAsignatura.getText().toString(),  Double.parseDouble(etNotaExamen.getText().toString()), Double.parseDouble(etAsignatura.getText().toString()), Double.parseDouble(etNotaFinal.getText().toString())));
            Toast.makeText(this, FileManager.comprobacion(), Toast.LENGTH_LONG).show();
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
package com.example.evaluaciont1_vl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.evaluaciont1_vl.datos.FileManager;
import com.example.evaluaciont1_vl.datos.NotasAlumnoAsig;
import com.example.evaluaciont1_vl.fragment.NotaFragmento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PantallaConsulta extends AppCompatActivity implements View.OnClickListener {
    private static final String BTN_LIMPIAR = "Limpiar datos";
    private static final String BTN_SELECCION ="Seleccionar alumno" ;

    EditText et;
    Button btn;
    LinearLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_consulta);

        et = findViewById(R.id.etConsultaAlumno);
        btn = findViewById(R.id.btnNotasConsulta);
        fl = findViewById(R.id.flContenedor);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if(btn.getText().equals(BTN_SELECCION)){
            i = new Intent(this,PantallaSeleccionAlumno.class);
            startActivityForResult(i,1);
        }else if(btn.getText().equals(BTN_LIMPIAR)){
            /*Si se pulsa el botón Limpiar datos, se vaciará el EditText, en el botón volverá a aparecer Seleccionar y se borrarán
               los fragmentos añadidos para que si se hace otra consulta no queden restos de las anteriores. */
            limpiarDatos();
            btn.setText("Seleccionar alumno");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String alumnoSeleccionado = data.getStringExtra("alumno");
                Log.e("TAG", "Alumno seleccionado: " + alumnoSeleccionado);

                et.setText(alumnoSeleccionado);
                btn.setText(BTN_LIMPIAR);

                mostrarNotas(alumnoSeleccionado);

            }
        }
    }

    private void limpiarDatos() {
        et.getText().clear();
        btn.setText("Limpiar datos");//Al volver de la selección de alumno se cambiará el texto del botón a Limpiar Datos
        limpiarFragmentos();
    }

    private void limpiarFragmentos() {
        // y se borrarán los fragmentos añadidos
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

//se recuperarán las notas de las asignaturas para aquellas asignaturas cuya nota final sea distinta de 0 para el
// alumno seleccionado (VER apartado Fuente de Datos) y se cargará cada asignatura y su nota en un fragmento
// de tipo NotaFragment.

    private void mostrarNotas(String alumnoSeleccionado) {

        int contador = 1;
        Map<String, Double> mapa = FileManager.readAlumno(alumnoSeleccionado);
        limpiarFragmentos();

        for (Map.Entry<String, Double> entry : mapa.entrySet()) {
            NotaFragmento notaFragmento = NotaFragmento.newInstance(entry.getKey(), entry.getValue());
            agregarFragmento(notaFragmento, contador);
            contador++;
        }


        /*for (NotasAlumnoAsig nota : notas) {

            if (nota.getNombre().equals(alumnoSeleccionado) && nota.getNotaFinal() != 0) {
                Log.e("TAG", "Asignatura: " + nota.getAsignatura() + ", Nota: " + nota.getNotaFinal());
                NotaFragmento notaFragment = NotaFragmento.newInstance(nota.getAsignatura(), nota.getNotaFinal());
                agregarFragmento(notaFragment);
            }
        }*/
    }

    private void agregarFragmento(NotaFragmento notaFragment, int pos) {//agregamos fragmento dinamico al LinearLayout
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (pos){
            case 1:
                ft.add(R.id.flNotas1, notaFragment);
                break;
            case 2:
                ft.add(R.id.flNotas2, notaFragment);
                break;
            case 3:
                ft.add(R.id.flNotas3, notaFragment);
                break;

            case 4:
                ft.add(R.id.flNotas4, notaFragment);
                break;

            case 5:
                ft.add(R.id.flNotas5, notaFragment);
                break;

            case 6:
                ft.add(R.id.flNotas6, notaFragment);
                break;

            case 7:
                ft.add(R.id.flNotas7, notaFragment);
                break;
        }
        ft.addToBackStack(null);// Añade el fragmento al back stack para poder retroceder
        ft.commit();// Commit la transacción

    }



}
package com.example.evaluaciont1_vl.datos;

import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;

public class ListadoNotas implements Serializable { // Se requiere para poder pasar de un intent a otro la clase

    private ArrayList<NotasAlumnoAsig> listado;

    public ListadoNotas() {
        if (!FileManager.fileExists()) listado = cargaInicial();
        else listado = FileManager.readFile();
    }

    public static ArrayList<NotasAlumnoAsig> cargaInicial(){
        ArrayList<NotasAlumnoAsig> listado = new ArrayList<NotasAlumnoAsig>();

        NotasAlumnoAsig temporal;

        for(String asignatura : Utilities.getAsignaturas()){
            for(String alumno : Utilities.getAlumnos()){
                temporal = new NotasAlumnoAsig(alumno, asignatura, 0, 0, 0);
                listado.add(temporal);
                FileManager.writeFile(temporal);
            }
        }

        return listado;
    }
}
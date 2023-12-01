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

        String[] alumnos = {"Amancio Javier Fernández Lobo", "Luis Gallego Herrero",
                "Daniel Gallo Romo", "Alberto García Altez", "Carlos García Hernández",
                "Juan Manuel Guerra Arcas", "Gonzalo González Rodríguez",
                "Jalil Martín Londres Blanes", "Ignacio Martín Fernández", "Javier Martínez Gómez",
                "Víctor Pérez Ramírez", "Lorenzo Sáez Béjar", "Nicolás Suárez Ruiz",
                "Liana Vilkelenoka", "Ángel Zhang"};
        String[] asignaturas = {"PMDM", "AD", "PSP", "DI", "SGE", "IACC", "IOS"};
        NotasAlumnoAsig temporal;

        for(String asignatura : asignaturas){
            for(String alumno : alumnos){
                temporal = new NotasAlumnoAsig(alumno, asignatura, 0, 0, 0);
                listado.add(temporal);
                FileManager.writeFile(temporal);
            }
        }

        return listado;
    }
}
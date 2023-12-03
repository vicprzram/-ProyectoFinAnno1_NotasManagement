package com.example.evaluaciont1_vl.datos;

import android.util.Log;

import com.example.evaluaciont1_vl.R;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.ArrayList;
import java.util.Random;

public class FileManager {

    private static final int BYTE_NOMBRE = 29;
    private static final int BYTE_ASIGNATURA = 4;
    private static final int BYTE_MAX = (BYTE_NOMBRE * 2) + (BYTE_ASIGNATURA * 2) + 8 + 8 + 8;

    private static final String PATH_IN = "/data/data/com.example.evaluaciont1_vl/files/datos.txt";
    private static final String PATH_OUT = "/data/data/com.example.evaluaciont1_vl/files/database/database.dat";
    private static final String ERROR_FILE = "ERROR: No se ha encontrado el archivo: ";

    public static boolean fileExists(){
        return new File(PATH_OUT).exists();
    }



    public static void writeFile(NotasAlumnoAsig n) {
        try(RandomAccessFile raf = new RandomAccessFile(PATH_OUT, "rw")){
            raf.seek(raf.length());
            StringBuffer sb = new StringBuffer(n.getNombre());
            sb.setLength(BYTE_NOMBRE);
            raf.writeChars(sb.toString());

            sb = new StringBuffer(n.getAsignatura());
            sb.setLength(BYTE_ASIGNATURA);
            raf.writeChars(sb.toString());

            raf.writeDouble(n.getNotaExamen());
            raf.writeDouble(n.getNotaActividades());
            raf.writeDouble(n.getNotaFinal());

        }catch(FileNotFoundException e){
            Log.e("FileNotFoundException", ERROR_FILE + PATH_OUT);
        }catch(IOException e){
            Log.e("IOException", e.getMessage());
        }
    }

    public static ArrayList<NotasAlumnoAsig> readFile(){
        ArrayList<NotasAlumnoAsig> listado = new ArrayList<NotasAlumnoAsig>();
        try(RandomAccessFile raf = new RandomAccessFile(PATH_OUT, "rw")){
            StringBuffer sb;
            String nombre, asignatura;
            double notaExamen, notaFinal, notaActividades;

            raf.seek(0);
            while(raf.getFilePointer() < raf.length()){
                sb = new StringBuffer();
                for(int i = 0; i < BYTE_NOMBRE; i++){
                    sb.append(raf.readChar());
                }
                sb.setLength(BYTE_NOMBRE);
                nombre = sb.toString();

                sb = new StringBuffer();
                for(int i = 0; i < BYTE_ASIGNATURA; i++){
                    sb.append(raf.readChar());
                }
                sb.setLength(BYTE_ASIGNATURA);
                asignatura = sb.toString();

                notaExamen = raf.readDouble();
                notaActividades = raf.readDouble();
                notaFinal = raf.readDouble();

                listado.add(new NotasAlumnoAsig(nombre, asignatura, notaExamen, notaActividades, notaFinal));
            }

        }catch(FileNotFoundException e){
            Log.e("FileNotFoundException", ERROR_FILE + PATH_OUT);
        }catch(IOException e){
            Log.e("IOException", e.getMessage());
        }
        return listado;
    }

    public static String comprobacion(){
        String a = "";
        try(RandomAccessFile raf = new RandomAccessFile(PATH_OUT, "rw")){
            StringBuffer sb;
            String nombre, asignatura;
            double notaE, notaA, notaF;


            raf.seek(0);

            while(raf.getFilePointer() < raf.length()){
                nombre = "";

                for(int i = 0; i < BYTE_NOMBRE; i++){
                    nombre += raf.readChar();
                }

                asignatura = "";

                for(int i = 0; i < BYTE_ASIGNATURA; i++){
                    asignatura += raf.readChar();
                }

                notaE = raf.readDouble();
                notaA = raf.readDouble();
                notaF = raf.readDouble();

                if(nombre.trim().equals("Liana Vilkelenoka")){
                    a += nombre + " " + asignatura + " " + notaE + " " + notaA + " " + notaF;
                    break;
                }
            }



        }catch(FileNotFoundException e){
            Log.e("FileNotFoundException", ERROR_FILE + PATH_OUT);
        }catch(IOException e){
            Log.e("IOException", e.getMessage());
        }
        return a;
    }

    public static void modificarNota(NotasAlumnoAsig alumno){
        try(RandomAccessFile raf = new RandomAccessFile(PATH_OUT, "rw")){

            String nombre;


            raf.seek(0);
            while(raf.getFilePointer() < raf.length()){
                 nombre = "";

                for(int i = 0; i < BYTE_NOMBRE; i++){
                    nombre += raf.readChar();
                }

                if(nombre.trim().equals(alumno.getNombre())){
                    raf.seek(raf.getFilePointer() + BYTE_ASIGNATURA);
                    raf.writeDouble(alumno.getNotaExamen());
                    raf.writeDouble(alumno.getNotaActividades());
                    raf.writeDouble(alumno.getNotaFinal());
                    raf.seek(raf.length());
                }else{
                    raf.seek(raf.getFilePointer() + (BYTE_MAX - BYTE_NOMBRE));
                }
            }



        }catch(FileNotFoundException e){
            Log.e("FileNotFoundException", ERROR_FILE + PATH_OUT);
        }catch(IOException e){
            Log.e("IOException", e.getMessage());
        }    }

}

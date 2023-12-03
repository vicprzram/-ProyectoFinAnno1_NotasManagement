package com.example.evaluaciont1_vl.datos;

public class Utilities {
    private static final String[] alumnos = {"Alberto Barba Soriano", "Alvaro Blázquez Vallejo", "Raúl Carrizo Martín",
            "Joana Casco Galea", "Javier Domínguez Fernández", "Gabriel Fernandez Aguilera",
            "Amancio Javier Fernández Lobo", "Luis Gallego Herrero",
            "Daniel Gallo Romo", "Alberto García Altez", "Carlos García Hernández",
            "Juan Manuel Guerra Arcas", "Marcos Giménez Díaz", "Gonzalo González Rodríguez",
            "Jalil Martín Londres Blanes", "Ignacio Martín Fernández", "Javier Martínez Gómez",
            "Víctor Pérez Ramírez", "Lorenzo Sáez Béjar", "Nicolás Suárez Ruiz",
            "Liana Vilkelenoka", "Ángel Zhang"};

    private static final String[] asignaturas = {"PMDM", "AD", "PSP", "DI", "SGE", "IACC", "IOS"};

    public static String[] getAlumnos(){
        return alumnos;
    }

    public static String[] getAsignaturas(){
        return asignaturas;
    }

}

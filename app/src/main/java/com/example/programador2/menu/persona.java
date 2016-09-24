package com.example.programador2.menu;

/**
 * Created by hpsamus on 31/08/2016.
 */
public class persona  {

    private int imagen;
    private String nombre;
    private int edad;

    public persona(){}

    public persona(int imagen, String nombre, int edad) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

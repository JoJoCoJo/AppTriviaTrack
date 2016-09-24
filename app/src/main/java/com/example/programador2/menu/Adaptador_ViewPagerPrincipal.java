package com.example.programador2.menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Programador2 on 07/05/2016.
 */
public class Adaptador_ViewPagerPrincipal extends FragmentPagerAdapter {

    int numeroDeSecciones;

    public Adaptador_ViewPagerPrincipal(FragmentManager fm, int numeroDeSecciones) {
        super(fm);
        this.numeroDeSecciones=numeroDeSecciones;
    }

    @Override
    public Fragment getItem(int position) {
        // recibimos la posición por parámetro y en función de ella devolvemos el Fragment correspondiente a esa sección.
        switch (position) {

            case 0: // siempre empieza desde 0
                return new Fragment_seccion1();

            case 1:
                return new Fragment_seccion2();

            case 2:
                return new Fragment_seccion3();
            case 3:
                return new Fragment_seccion4();


            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }
    }


    /*  getCount() devuelve el nº de secciones, dato que recibiremos cuando instanciemos el adaptador
        desde nuestra actividad principal */
    @Override
    public int getCount() {
        return numeroDeSecciones;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // recibimos la posición por parámetro y en función de ella devolvemos el titulo correspondiente.
        switch (position) {

            case 0: // siempre empieza desde 0
                return "Jugar";
            case 1:
                return "Invitaciones";
            case 2:
                return "Puntuajes";
            case 3:
                return "Partidas";

            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }

    }

}
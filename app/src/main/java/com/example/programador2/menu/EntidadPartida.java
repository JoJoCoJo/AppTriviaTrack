package com.example.programador2.menu;

/**
 * Created by hpsamus on 24/09/2016.
 */

public class EntidadPartida {



    public int IdPartida ;
    public int IdUsuario1 ;
    public int IdUsuario2 ;
    public String retador ;
    public String retado ;
    public int PuntuajeUsuario1 ;
    public int PuntuajeUsuario2 ;
    public int IdNivel ;
    public String Solicitud;


    public EntidadPartida(int idPartida, int idUsuario1, int idUsuario2, String retador, String retado, int puntuajeUsuario1, int puntuajeUsuario2, int idNivel, String solicitud) {
        IdPartida = idPartida;
        IdUsuario1 = idUsuario1;
        IdUsuario2 = idUsuario2;
        this.retador = retador;
        this.retado = retado;
        PuntuajeUsuario1 = puntuajeUsuario1;
        PuntuajeUsuario2 = puntuajeUsuario2;
        IdNivel = idNivel;
        Solicitud = solicitud;
    }

    public int getIdPartida() {
        return IdPartida;
    }

    public int getIdUsuario1() {
        return IdUsuario1;
    }

    public int getIdUsuario2() {
        return IdUsuario2;
    }

    public String getRetador() {
        return retador;
    }

    public String getRetado() {
        return retado;
    }

    public int getPuntuajeUsuario1() {
        return PuntuajeUsuario1;
    }

    public int getPuntuajeUsuario2() {
        return PuntuajeUsuario2;
    }

    public int getIdNivel() {
        return IdNivel;
    }

    public String getSolicitud() {
        return Solicitud;
    }

    public void setIdPartida(int idPartida) {
        IdPartida = idPartida;
    }

    public void setIdUsuario1(int idUsuario1) {
        IdUsuario1 = idUsuario1;
    }

    public void setIdUsuario2(int idUsuario2) {
        IdUsuario2 = idUsuario2;
    }

    public void setRetador(String retador) {
        this.retador = retador;
    }

    public void setRetado(String retado) {
        this.retado = retado;
    }

    public void setPuntuajeUsuario1(int puntuajeUsuario1) {
        PuntuajeUsuario1 = puntuajeUsuario1;
    }

    public void setPuntuajeUsuario2(int puntuajeUsuario2) {
        PuntuajeUsuario2 = puntuajeUsuario2;
    }

    public void setIdNivel(int idNivel) {
        IdNivel = idNivel;
    }

    public void setSolicitud(String solicitud) {
        Solicitud = solicitud;
    }
}

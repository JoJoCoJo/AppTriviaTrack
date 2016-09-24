package com.example.programador2.menu;

import android.app.Application;

/**
 * Created by hpsamus on 31/07/2016.
 */
public class MyApplication extends Application {

    private String vaUsuario;
    private String vaPassword;
    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }


    public String getVaUsuario(){

        return vaUsuario;
    }
    public void setVaUsuario(String usu)
    {
        this.vaUsuario=usu;
    }

    public String getVaPassword()
    {
        return vaPassword;
    }
    public void  setVaPassword(String vapass)
    {
        this.vaPassword=vapass;
    }



}

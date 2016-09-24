package com.example.programador2.menu;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Programador2 on 24/05/2016.
 */
public class DialogoSelectorFecha extends DialogFragment {


    private DatePickerDialog.OnDateSetListener escuchador;
    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener escuchador) {
        this.escuchador = escuchador;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendario = Calendar.getInstance();
        Bundle args = this.getArguments();
        if (args != null) {
            long fecha = args.getLong("fecha");
            calendario.setTimeInMillis(fecha);
        }
        int anyo = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), escuchador, anyo, mes, dia);
    }
}

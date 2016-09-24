package com.example.programador2.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hpsamus on 31/08/2016.
 */
public class ClienteAdapter   extends ArrayAdapter<persona> {

    private final Context contexto;
    private final ArrayList<persona> array_personas;

    public ClienteAdapter(Context context, ArrayList<persona> array_personas) {
        super(context, -1, array_personas);
        this.contexto = context;
        this.array_personas = array_personas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutPersona = inflater.inflate(R.layout.arraypersonalisado, parent, false);

        ImageView img = (ImageView) layoutPersona.findViewById(R.id.imageViewPersona);
        TextView nombreTextView = (TextView) layoutPersona.findViewById(R.id.textViewNombre);
        TextView edad = (TextView) layoutPersona.findViewById(R.id.textViewEdad);

        // Para poder llenar los elementos del Layout de items, necesitamos obtener
        // los datos de la persona que estoy recorriendo en la cada iteración de la lista
        //para ello usamos esto:
        persona personaActual = array_personas.get(position);

        img.setImageResource(personaActual.getImagen());
        nombreTextView.setText(personaActual.getNombre());
        edad.setText(personaActual.getEdad() + " años");

        return layoutPersona;
    }
}

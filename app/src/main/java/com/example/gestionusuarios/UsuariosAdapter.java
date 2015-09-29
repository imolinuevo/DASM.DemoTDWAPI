package com.example.gestionusuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    private ArrayList<Usuario> noticias;
    private Context context;

    /**
     * Constructor del adaptador
     * @param context   Contexto
     * @param textViewResourceId    Identificador del layout
     * @param noticias  Datos a representar
     */
    public UsuariosAdapter(Context context,
                           int textViewResourceId, ArrayList<Usuario> noticias) {
        super(context, textViewResourceId, noticias);
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.usuario, null);
        }
        Usuario usuario = noticias.get(position);
        if (usuario != null) {
            TextView textView01 = (TextView) convertView.findViewById(R.id.textViewUsername);
            TextView textView02 = (TextView) convertView.findViewById(R.id.textViewEmail);
            if (textView01 != null) {
                textView01.setText(usuario.getUsername());
            }
            if (textView02 != null) {
                textView02.setText(usuario.getEmail());
            }
        }
        return convertView;
    }

}


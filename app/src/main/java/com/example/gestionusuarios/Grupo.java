package com.example.gestionusuarios;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;

public class Grupo implements Serializable {

    public static final String TAG_GROUP_ID  = "id";
    public static final String TAG_GROUPNAME = "groupname";
    public static final String TAG_GROUPDESC = "description";

    private int id;
    private String groupname;
    private String description;

    /**
     * Constructor
     */
    public Grupo() {
    }

    /**
     * TO DO Constructor: genera un nuevo grupo a partir de su representación
     * como objeto JSON
     * @param objetoJSON    Representación del objeto JSON
     */
    public Grupo(JSONObject objetoJSON) {
        try {
            this.setId(objetoJSON.getInt(TAG_GROUP_ID));
            this.setGroupname(objetoJSON.getString(TAG_GROUPNAME));
            this.setDescription(objetoJSON.getString(TAG_GROUPDESC));
        } catch (Exception e) {
            Log.e("JSon:", "Error en la creación del Grupo");
        }
//        Gson gson = new GsonBuilder().create();
//        Grupo g = gson.fromJson(objetoJSON.toString(), Grupo.class);
//        this.setId(g.getId());
//        this.setGroupname(g.getGroupname());
//        this.setDescription(g.getDescription());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }
}

package com.example.gestionusuarios;

public class Grupo  {
    private int id;
    private String groupname;
    private String description;

    public Grupo() {
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
        return "Grupo {" +
                "id=" + id +
                ", groupname='" + groupname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

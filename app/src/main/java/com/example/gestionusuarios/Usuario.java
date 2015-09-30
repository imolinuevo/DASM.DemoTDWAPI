package com.example.gestionusuarios;

import android.util.Log;

import org.json.JSONObject;

public class Usuario {

    /**
     * Identificadores de los campos
     */
    public static final String TAG_ID         = "id";
    public static final String TAG_USERNAME   = "username";
    public static final String TAG_PASSWORD   = "password";
    public static final String TAG_EMAIL      = "email";
    public static final String TAG_CTIME      = "createTime";
    public static final String TAG_CTIME_DATE = "date";
    public static final String TAG_CTIME_TZT  = "timezone_type";
    public static final String TAG_CTIME_TZ   = "timezone";
    public static final String TAG_IS_ACTIVE  = "isActive";
    public static final String TAG_IS_ADMIN   = "isAdmin";
    public static final String TAG_GROUP      = "group_id";

    /**
     * Atributos privados
     */
    private int id;
    private String username;
    private String email;
    private String password;
    private Fecha createTime;
    private boolean isActive;
    private boolean isAdmin;
    private Grupo group;

    /**
     * Constructor
     */
    public Usuario() {
    }

    /**
     * TO DO Constructor: genera un nuevo usuario a partir de su representación
     * como objeto JSON
     * @param objetoJSON    Representación del objeto JSON
     */
    public Usuario(JSONObject objetoJSON) {
        try {
            this.setId(objetoJSON.getInt(TAG_ID));
            this.setUsername(objetoJSON.getString(TAG_USERNAME));
            this.setEmail(objetoJSON.getString(TAG_EMAIL));
            this.setIsActive(objetoJSON.getBoolean(TAG_IS_ACTIVE));
            this.setIsAdmin(objetoJSON.getBoolean(TAG_IS_ADMIN));

            // Si hay grupo -> se asigna al usuario
            if (!objetoJSON.isNull(TAG_GROUP)) {
                this.setGroup(new Grupo(objetoJSON.getJSONObject(TAG_GROUP)));
            }

            // Si hay fecha de creación -> se asigna
            if (!objetoJSON.isNull(TAG_CTIME)) {
                this.setCreateTime(new Fecha(objetoJSON.getJSONObject(TAG_CTIME)));
            }
        } catch (Exception e) {
            Log.e("JSon:", "Error en la creación del Usuario");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Fecha getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Fecha createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Grupo getGroup() {
        return group;
    }

    public void setGroup(Grupo group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isActive=" + isActive +
                ", isAdmin=" + isAdmin +
                ", groupId=" + group +
                '}';
    }

    public class Fecha {
        private String date;
        private int timezone_type;
        private String timezone;

        /**
         * TO DO Constructor: genera un nuevo grupo a partir de su representación
         * como objeto JSON
         * @param objetoJSON    Representación del objeto JSON
         */
        public Fecha(JSONObject objetoJSON) {
            try {
                this.setDate(objetoJSON.getString(TAG_CTIME_DATE));
                this.setTimezoneType(objetoJSON.getInt(TAG_CTIME_TZT));
                this.setTimezone(objetoJSON.getString(TAG_CTIME_TZ));
            } catch (Exception e) {
                Log.e("JSon:", "Error en la creación de la fecha de creación");
            }
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getTimezoneType() {
            return timezone_type;
        }

        public void setTimezoneType(int timezone_type) {
            this.timezone_type = timezone_type;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        @Override
        public String toString() {
            return "Fecha{" +
                    "date='" + date + '\'' +
                    ", timezone_type=" + timezone_type +
                    ", timezone='" + timezone + '\'' +
                    '}';
        }
    }
}

package com.example.gestionusuarios;

public class Usuario {

    /**
     * Identificadores de los campos
     */
    public static final String TAG_ID        = "id";
    public static final String TAG_USERNAME  = "username";
    public static final String TAG_PASSWORD  = "password";
    public static final String TAG_EMAIL     = "email";
    public static final String TAG_IS_ACTIVE = "isActive";
    public static final String TAG_IS_ADMIN  = "isAdmin";
    public static final String TAG_GROUP     = "group_id";
    public static final String TAG_GROUP_ID  = "id";
    public static final String TAG_GROUPNAME = "groupname";
    public static final String TAG_GROUPDESC = "description";

    /**
     * Atributos privados
     */
    private int id;
    private String username;
    private String email;
    private String password;
    private String createTime;
    private boolean isActive;
    private boolean isAdmin;
    private Grupo group;

    /**
     * Constructor
     */
    public Usuario() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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
}

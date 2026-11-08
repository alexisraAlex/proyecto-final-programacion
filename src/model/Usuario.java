package model;

public class Usuario {
    protected String correo;
    protected String contrasena;
    protected String nombre;
    protected String carrera;
    protected String grupo;
    protected String cuatrimestre;
    protected int creditos = 0;

    public Usuario(String correo, String contrasena, String nombre, String carrera, String grupo, String cuatrimestre) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.carrera = carrera;
        this.grupo = grupo;
        this.cuatrimestre = cuatrimestre;
    }

    // Getters y setters
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
    public String getGrupo() { return grupo; }
    public String getCuatrimestre() { return cuatrimestre; }
    public int getCreditos() { return creditos; }

    public void setCreditos(int c) { this.creditos = c; }
}

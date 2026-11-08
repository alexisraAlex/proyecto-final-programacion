package model;

public class Administrador extends Usuario {
    public Administrador(String correo, String contrasena, String nombre) {
        super(correo, contrasena, nombre, "Administrador", "contraseña", "-");
    }
}

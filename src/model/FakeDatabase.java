package model;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<CreditoHistorial> historial = new ArrayList<>();

    // Agrega un usuario
    public static void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    // Busca usuario por correo
    public static Usuario buscarUsuario(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) return u;
        }
        return null;
    }

    public static void agregarHistorial(CreditoHistorial h) {
        historial.add(h);
    }

    public static List<CreditoHistorial> getHistorialUsuario(String correo) {
        List<CreditoHistorial> result = new ArrayList<>();
        for (CreditoHistorial h : historial) {
            if (h.getCorreoUsuario().equalsIgnoreCase(correo)) result.add(h);
        }
        return result;
    }
}

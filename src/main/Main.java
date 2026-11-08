package main;

import gui.LoginFrame;
import model.FakeDatabase;
import model.Administrador;

public class Main {
    public static void main(String[] args) {
        String adminEmail = System.getenv("ADMIN_EMAIL");
        String adminPassword = System.getenv("ADMIN_PASSWORD");
        if (adminEmail != null && !adminEmail.isBlank()
                && adminPassword != null && !adminPassword.isBlank()) {
            FakeDatabase.agregarUsuario(new Administrador(adminEmail, adminPassword, "Administrador"));
        }
        javax.swing.SwingUtilities.invokeLater(LoginFrame::new);
    }
}
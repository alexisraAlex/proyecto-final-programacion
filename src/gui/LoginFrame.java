package gui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField correoField;
    private JPasswordField passField;
    private JButton loginBtn, registroBtn;

    public LoginFrame() {
        setTitle("Login ITSE");
        setLayout(new GridLayout(3,2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,150);

        correoField = new JTextField();
        passField = new JPasswordField();
        loginBtn = new JButton("Iniciar Sesión");
        registroBtn = new JButton("Registrar");

        add(new JLabel("Correo:"));
        add(correoField);
        add(new JLabel("Contraseña:"));
        add(passField);
        add(loginBtn);
        add(registroBtn);

        loginBtn.addActionListener(e -> {
            String correo = correoField.getText().trim();
            String pass = String.valueOf(passField.getPassword());
            Usuario u = FakeDatabase.buscarUsuario(correo);

            if (u != null && u.getContrasena().equals(pass)) {
                if (u instanceof Administrador)
                    new AdminFrame((Administrador) u);
                else
                    new UsuarioFrame(u);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });

        registroBtn.addActionListener(e -> {
            this.dispose();
            new RegistroFrame();
        });

        setVisible(true);
    }
}

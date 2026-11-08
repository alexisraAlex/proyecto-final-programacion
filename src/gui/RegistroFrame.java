package gui;

import model.*;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RegistroFrame extends JFrame {
    private JTextField correoField, nombreField, carreraField, grupoField, cuatrimestreField, codigoField;
    private JPasswordField passField;
    private JButton enviarCodigoBtn, verificarBtn, registrarBtn;
    private String codigoEnviado = "";

    public RegistroFrame() {
        // Inicializa UI
        setTitle("Registro de Usuario - ITSE");
        setLayout(new GridLayout(0,2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);

        correoField = new JTextField();
        passField = new JPasswordField();
        nombreField = new JTextField();
        carreraField = new JTextField();
        grupoField = new JTextField();
        cuatrimestreField = new JTextField();
        codigoField = new JTextField();

        enviarCodigoBtn = new JButton("Enviar Código");
        verificarBtn = new JButton("Verificar Código");
        registrarBtn = new JButton("Registrar");

        add(new JLabel("Correo Institucional:"));
        add(correoField);
        add(enviarCodigoBtn);
        add(new JLabel(""));
        add(new JLabel("Código recibido:"));
        add(codigoField);
        add(verificarBtn);
        add(new JLabel(""));
        add(new JLabel("Contraseña:"));
        add(passField);
        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Carrera:"));
        add(carreraField);
        add(new JLabel("Grupo:"));
        add(grupoField);
        add(new JLabel("Cuatrimestre:"));
        add(cuatrimestreField);
        add(registrarBtn);

        habilitarCampos(false);

        enviarCodigoBtn.addActionListener(e -> {
            String correo = correoField.getText().trim();
            if (!correo.endsWith("@itse.ac.pa")) {
                JOptionPane.showMessageDialog(this, "Correo debe ser institucional.");
            } else {
                codigoEnviado = String.format("%06d", new Random().nextInt(999999));
                try {
                    EmailSender.enviarCodigo(correo, codigoEnviado);
                    JOptionPane.showMessageDialog(this, "Código enviado. Revisa tu correo.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error enviando correo: " + ex.getMessage());
                }
            }
        });

        verificarBtn.addActionListener(e -> {
            if (codigoField.getText().trim().equals(codigoEnviado)) {
                habilitarCampos(true);
                JOptionPane.showMessageDialog(this, "Verificación exitosa, completa el formulario.");
            } else {
                JOptionPane.showMessageDialog(this, "Código incorrecto.");
            }
        });

        registrarBtn.addActionListener(e -> {
            Usuario u = new Usuario(
                correoField.getText().trim(),
                String.valueOf(passField.getPassword()),
                nombreField.getText().trim(),
                carreraField.getText().trim(),
                grupoField.getText().trim(),
                cuatrimestreField.getText().trim()
            );
            FakeDatabase.agregarUsuario(u);
            try {
            	String datosEncriptados = CryptoUtil.encriptar(u.getCorreo());
            	BufferedImage qr = QRGenerator.crearQR(datosEncriptados);
                JOptionPane.showMessageDialog(this, new JLabel(new ImageIcon(qr)), "QR Generado", JOptionPane.PLAIN_MESSAGE);

                // Opcional: Enviar QR al correo como imagen adjunta
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error generando QR: " + ex.getMessage());
                
                this.dispose();
                new LoginFrame();
            }
            this.dispose();
            new LoginFrame();
        });

        setVisible(true);
    }

    private void habilitarCampos(boolean habilitar) {
        passField.setEnabled(habilitar);
        nombreField.setEnabled(habilitar);
        carreraField.setEnabled(habilitar);
        grupoField.setEnabled(habilitar);
        cuatrimestreField.setEnabled(habilitar);
        registrarBtn.setEnabled(habilitar);
    }
}

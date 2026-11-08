package gui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminFrame extends JFrame {
    private JTextField correoField, creditosField, motivoField;
    private JTextArea historialArea;
    private JButton buscarBtn, agregarBtn, eliminarBtn, verHistorialBtn;

    public AdminFrame(Administrador admin) {
        setTitle("Panel Administrador");
        setLayout(new GridLayout(7,2));
        setSize(600,300);

        correoField = new JTextField();
        creditosField = new JTextField();
        motivoField = new JTextField();
        historialArea = new JTextArea(10, 30);
        historialArea.setEditable(false);

        buscarBtn = new JButton("Buscar Usuario");
        agregarBtn = new JButton("Añadir Créditos");
        eliminarBtn = new JButton("Eliminar Usuario");
        verHistorialBtn = new JButton("Ver Historial");

        add(new JLabel("Correo de usuario:"));
        add(correoField);
        add(new JLabel("Créditos a añadir/quitar:"));
        add(creditosField);
        add(new JLabel("Motivo:"));
        add(motivoField);
        add(agregarBtn);
        add(eliminarBtn);
        add(verHistorialBtn);
        add(new JScrollPane(historialArea));
        add(buscarBtn);

        buscarBtn.addActionListener(e -> {
            Usuario u = FakeDatabase.buscarUsuario(correoField.getText().trim());
            if (u != null)
                JOptionPane.showMessageDialog(this, "Usuario: " + u.getNombre() + "\nCréditos: " + u.getCreditos());
            else
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
        });

        agregarBtn.addActionListener(e -> {
            Usuario u = FakeDatabase.buscarUsuario(correoField.getText().trim());
            if (u != null) {
                int delta = Integer.parseInt(creditosField.getText());
                u.setCreditos(u.getCreditos() + delta);
                FakeDatabase.agregarHistorial(new CreditoHistorial(u.getCorreo(), delta, motivoField.getText()));
                JOptionPane.showMessageDialog(this, "Créditos modificados.");
            }
        });

        eliminarBtn.addActionListener(e -> {
            Usuario u = FakeDatabase.buscarUsuario(correoField.getText().trim());
            if (u != null) {
                FakeDatabase.usuarios.remove(u);
                JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            }
        });

        verHistorialBtn.addActionListener(e -> {
            List<CreditoHistorial> list = FakeDatabase.getHistorialUsuario(correoField.getText().trim());
            StringBuilder sb = new StringBuilder();
            for (CreditoHistorial h : list) {
                sb.append(h.getFecha()).append(" | ").append(h.getCambioCredito())
                  .append(" | ").append(h.getMotivo()).append('\n');
            }
            historialArea.setText(sb.toString());
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

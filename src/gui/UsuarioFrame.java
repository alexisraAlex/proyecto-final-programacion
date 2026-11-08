package gui;

import model.*;
import util.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UsuarioFrame extends JFrame {
    public UsuarioFrame(Usuario usuario) {
        setTitle("Carnet Virtual ITSE");
        setLayout(new BorderLayout());
        setSize(400,500);

        JPanel infoPanel = new JPanel(new GridLayout(0,1));
        infoPanel.add(new JLabel("Nombre: " + usuario.getNombre()));
        infoPanel.add(new JLabel("Correo: " + usuario.getCorreo()));
        infoPanel.add(new JLabel("Carrera: " + usuario.getCarrera()));
        infoPanel.add(new JLabel("Grupo: " + usuario.getGrupo()));
        infoPanel.add(new JLabel("Cuatrimestre: " + usuario.getCuatrimestre()));
        infoPanel.add(new JLabel("Créditos: " + usuario.getCreditos()));

        JLabel qrLabel = new JLabel();
        try {
            qrLabel.setIcon(new ImageIcon(QRGenerator.crearQR(usuario.getCorreo())));
        } catch (Exception e) {
            qrLabel.setText("No se pudo generar QR");
        }

        JTextArea historialArea = new JTextArea(10, 30);
        List<CreditoHistorial> list = model.FakeDatabase.getHistorialUsuario(usuario.getCorreo());
        StringBuilder sb = new StringBuilder("Historial de créditos:\n");
        for (CreditoHistorial h : list) {
            sb.append(h.getFecha()).append(" | ").append(h.getCambioCredito())
              .append(" | ").append(h.getMotivo()).append('\n');
        }
        historialArea.setText(sb.toString());

        add(infoPanel, BorderLayout.NORTH);
        add(qrLabel, BorderLayout.CENTER);
        add(new JScrollPane(historialArea), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

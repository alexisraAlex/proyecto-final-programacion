package model;

import java.time.LocalDateTime;

public class CreditoHistorial {
    private String correoUsuario;
    private int cambioCredito;
    private String motivo;
    private LocalDateTime fecha;

    public CreditoHistorial(String correoUsuario, int cambioCredito, String motivo) {
        this.correoUsuario = correoUsuario;
        this.cambioCredito = cambioCredito;
        this.motivo = motivo;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public String getCorreoUsuario() { return correoUsuario; }
    public int getCambioCredito() { return cambioCredito; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getFecha() { return fecha; }
}

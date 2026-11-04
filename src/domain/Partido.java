package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LocalDate fecha;
    private final String ubicacion;
    private final Equipo local;
    private final Equipo visitante;
    private Integer golesLocal;
    private Integer golesVisitante;

    public Partido(LocalDate fecha, String ubicacion, Equipo local, Equipo visitante) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.local = local;
        this.visitante = visitante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Equipo getLocal() {
        return local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void registrarResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public boolean isJugado() {
        return golesLocal != null && golesVisitante != null;
    }

    @Override
    public String toString() {
        return local + " - " + visitante;
    }
}

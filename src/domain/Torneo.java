package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String deporte;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final List<Equipo> equipos = new ArrayList<>();
    private final List<String> ubicaciones = new ArrayList<>();
    private final List<Partido> partidos = new ArrayList<>();

    public Torneo(int id, String deporte, LocalDate fechaInicio, LocalDate fechaFin, List<String> ubicaciones) {
        this.id = id;
        this.deporte = deporte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ubicaciones.addAll(ubicaciones);
    }

    public int getId() {
        return id;
    }

    public String getDeporte() {
        return deporte;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }

    public List<String> getUbicaciones() {
        return Collections.unmodifiableList(ubicaciones);
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos);
    }

    public void addEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void addPartido(Partido partido) {
        partidos.add(partido);
        partidos.sort(Comparator.comparing(Partido::getFecha));
    }

    public List<Equipo> getClasificacion() {
        List<Equipo> clasificacion = new ArrayList<>(equipos);
        clasificacion.sort(Comparator.comparingInt(Equipo::getPuntos).reversed().thenComparing(Equipo::getNombre));
        return clasificacion;
    }

    @Override
    public String toString() {
        return deporte;
    }
}

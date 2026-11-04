package domain;

import java.io.Serializable;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private int puntos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

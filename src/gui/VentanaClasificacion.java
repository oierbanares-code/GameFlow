package gui;

import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//Ventana Clasificación tabla con Puesto, Nombre y Puntos
public class VentanaClasificacion extends JDialog {

    private static final long serialVersionUID = 1L;

    public VentanaClasificacion(Window padre, String deporte) {
        super(padre, "Clasificación " + deporte);

        // Tabla de ejemplo, no editable
        Object[][] datos = { { 1, "Equipo A", 12 }, { 2, "Equipo B", 9 }, { 3, "Equipo C", 6 },
                { 4, "Equipo D", 0 } };
        JTable tabla = new JTable(datos, new String[] { "Puesto", "Nombre", "Puntos" });
        tabla.setRowHeight(34);
        tabla.setDefaultEditor(Object.class, null);

        add(new JScrollPane(tabla));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(440, 300);
        setLocationRelativeTo(padre);
    }
}
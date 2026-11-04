package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

//Ventana torneos, desde aquí se accede a Ventana Clasificación y Ventana Partidos

public class VentanaTorneos extends JDialog {

    private static final long serialVersionUID = 1L;

    //Torneos de ejemplo
    static final String[] DEPORTES = { "Fútbol", "Baloncesto", "Tenis" };

    public VentanaTorneos(Window padre) {
        super(padre, "Torneos");
        setModal(true);

        // Fila de cada torneo
        Object[][] datos = new Object[DEPORTES.length][];
        for (int i = 0; i < DEPORTES.length; i++) {
            datos[i] = new Object[] { String.format("%03d", i + 1), DEPORTES[i], "Clasificación", "Partidos" };
        }
        JTable tabla = new JTable(datos, new String[] { "ID", "Deporte", "Clasificación", "Partidos" });
        tabla.setTableHeader(null);
        tabla.setRowHeight(64);
        tabla.setFont(tabla.getFont().deriveFont(Font.BOLD, 16f));
        tabla.setDefaultEditor(Object.class, null); //no se puede editar
        tabla.setRowSelectionAllowed(false); //no se seleccionan
        tabla.setFocusable(false);

        // Renderer 1(ID y deporte)
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centrado);

        // Renderer 2(botones partidos y clasificación)
        TableCellRenderer boton = (t, valor, seleccionada, foco, fila, columna) -> {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
            panel.add(new JButton(String.valueOf(valor)));
            return panel;
        };
        tabla.getColumnModel().getColumn(2).setCellRenderer(boton);
        tabla.getColumnModel().getColumn(3).setCellRenderer(boton);

        // MouseListener clickar
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if (fila < 0) {
                    return;
                }
                String deporte = (String) tabla.getValueAt(fila, 1);
                if (columna == 2) {
                    new VentanaClasificacion(VentanaTorneos.this, deporte).setVisible(true);
                } else if (columna == 3) {
                    new VentanaPartidos(VentanaTorneos.this, deporte).setVisible(true);
                }
            }
        });

        add(new JScrollPane(tabla));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640, 420);
        setLocationRelativeTo(padre);
    }
}

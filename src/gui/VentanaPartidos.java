package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

//Ventana Partidos una tarjeta por partido con fecha, lugar y equipos
public class VentanaPartidos extends JDialog {

    private static final long serialVersionUID = 1L;

    public VentanaPartidos(Window padre, String deporte) {
        super(padre, "Partidos " + deporte);

        // Partidos de ejemplo
        String[][] partidos = { { "06/01/2027", "Anoeta (Donostia)", "Equipo A - Equipo C" },
                { "11/01/2027", "San Mamés (Bilbao)", "Equipo B - Equipo D" } };

        JPanel lista = new JPanel(new GridLayout(0, 1, 0, 12));
        lista.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        for (String[] partido : partidos) {
            // color de la tarjeta y separación entre tarjetas
            JPanel tarjeta = new JPanel(new GridLayout(3, 1, 0, 1));
            tarjeta.setBackground(Color.GRAY);
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            for (String texto : partido) {
                JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);
                etiqueta.setOpaque(true);
                etiqueta.setBackground(Color.WHITE);
                etiqueta.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
                tarjeta.add(etiqueta);
            }
            lista.add(tarjeta);
        }

        // Mantener el tamaño de las tarjetas (que no se estiren si hay pocas)
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(lista, BorderLayout.NORTH);

        add(new JScrollPane(contenedor));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(padre);
    }
}
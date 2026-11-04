package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

//Ventana Crear torneo

public class VentanaCrearTorneo extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JTextField tfDeporte = new JTextField();
    private final JTextField tfInicio = new JTextField();
    private final JTextField tfFin = new JTextField();
    private final JPanel panelEquipos = new JPanel(new GridLayout(0, 2, 8, 8));
    private final JPanel panelUbicaciones = new JPanel(new GridLayout(0, 2, 8, 8));

    public VentanaCrearTorneo(Window padre) {
        super(padre, "+ Crear Torneo");
        setModal(true);

        tfInicio.setToolTipText("dd/mm/aaaa");
        tfFin.setToolTipText("dd/mm/aaaa");

        // Configuración spinner
        JSpinner spEquipos = new JSpinner(new SpinnerNumberModel(2, 2, 32, 1));
        JSpinner spUbicaciones = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spEquipos.addChangeListener(
                e -> ajustarCampos(panelEquipos, (Integer) spEquipos.getValue(), "Nombre equipo"));
        spUbicaciones.addChangeListener(
                e -> ajustarCampos(panelUbicaciones, (Integer) spUbicaciones.getValue(), "Ubicación"));
        ajustarCampos(panelEquipos, 2, "Nombre equipo");
        ajustarCampos(panelUbicaciones, 1, "Ubicación");

        // Columna izquierda (deporte, nº de equipos y lista de nombres)
        JPanel datosIzq = new JPanel(new GridLayout(2, 2, 8, 8));
        datosIzq.add(new JLabel("Deporte:"));
        datosIzq.add(tfDeporte);
        datosIzq.add(new JLabel("Nº equipos:"));
        datosIzq.add(spEquipos);
        JPanel izquierda = new JPanel(new BorderLayout(0, 10));
        izquierda.add(datosIzq, BorderLayout.NORTH);
        izquierda.add(crearScroll(panelEquipos), BorderLayout.CENTER);

        // Columna derecha (fechas, nº de ubicaciones y lista de nombres)
        JPanel datosDer = new JPanel(new GridLayout(3, 2, 8, 8));
        datosDer.add(new JLabel("Fecha inicio:"));
        datosDer.add(tfInicio);
        datosDer.add(new JLabel("Fecha fin:"));
        datosDer.add(tfFin);
        datosDer.add(new JLabel("Nº ubicaciones:"));
        datosDer.add(spUbicaciones);
        JPanel derecha = new JPanel(new BorderLayout(0, 10));
        derecha.add(datosDer, BorderLayout.NORTH);
        derecha.add(crearScroll(panelUbicaciones), BorderLayout.CENTER);

        JPanel formulario = new JPanel(new GridLayout(1, 2, 25, 0));
        formulario.add(izquierda);
        formulario.add(derecha);

        JButton btnRegistrar = new JButton("Registrar torneo");
        btnRegistrar.setPreferredSize(new Dimension(190, 40));
        btnRegistrar.addActionListener(e -> {
            if (hayCamposVacios()) {
                JOptionPane.showMessageDialog(this, "Rellena todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "El torneo de " + tfDeporte.getText().trim() + " se ha creado correctamente.",
                        "Torneo creado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        getRootPane().setDefaultButton(btnRegistrar); //Botón registrar torneo
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRegistrar);

        JPanel principal = new JPanel(new BorderLayout(10, 15));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        principal.add(formulario, BorderLayout.CENTER);
        principal.add(panelBoton, BorderLayout.SOUTH);

        add(principal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(780, 540);
        setLocationRelativeTo(padre);
    }

    //El envoltorio evita que las filas se estiren
    private JScrollPane crearScroll(JPanel panel) {
        JPanel envoltorio = new JPanel(new BorderLayout());
        envoltorio.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        envoltorio.add(panel, BorderLayout.NORTH);
        return new JScrollPane(envoltorio);
    }

    //Añade o quita filas
    private void ajustarCampos(JPanel panel, int cantidad, String etiqueta) {
        while (panel.getComponentCount() < cantidad * 2) {
            panel.add(new JLabel(etiqueta + " " + (panel.getComponentCount() / 2 + 1) + ":"));
            panel.add(new JTextField());
        }
        while (panel.getComponentCount() > cantidad * 2) {
            panel.remove(panel.getComponentCount() - 1);
            panel.remove(panel.getComponentCount() - 1);
        }
        panel.revalidate();
        panel.repaint();
    }

    // Se deben rellenar todos los campos
    private boolean hayCamposVacios() {
        boolean vacio = tfDeporte.getText().isBlank() || tfInicio.getText().isBlank() || tfFin.getText().isBlank();
        for (JPanel panel : new JPanel[] { panelEquipos, panelUbicaciones }) {
            for (Component c : panel.getComponents()) {
                if (c instanceof JTextField && ((JTextField) c).getText().isBlank()) {
                    vacio = true;
                }
            }
        }
        return vacio;
    }
}
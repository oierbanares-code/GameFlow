package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

// Ventana Perfil

public class VentanaPerfil extends JDialog {

    private static final long serialVersionUID = 1L;

    public VentanaPerfil(Window padre) {
        super(padre, "Tu Perfil");
        setModal(true);

        JTextField tfNombre = new JTextField(18);
        JSpinner spEdad = new JSpinner(new SpinnerNumberModel(18, 18, 120, 1));
        JTextField tfDni = new JTextField(10);

        // Formulario de tres filas
        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 14));
        formulario.add(new JLabel("Nombre:"));
        formulario.add(tfNombre);
        formulario.add(new JLabel("Edad:"));
        formulario.add(spEdad);
        formulario.add(new JLabel("DNI:"));
        formulario.add(tfDni);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (tfNombre.getText().isBlank() || tfDni.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Rellena el nombre y el DNI.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Tus datos se han guardado correctamente.",
                        "Perfil guardado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        getRootPane().setDefaultButton(btnGuardar); //Botón guardar
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);

        JPanel principal = new JPanel(new BorderLayout(0, 20));
        principal.setBorder(BorderFactory.createEmptyBorder(25, 30, 20, 30));
        principal.add(formulario, BorderLayout.CENTER);
        principal.add(panelBoton, BorderLayout.SOUTH);

        add(principal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 280);
        setLocationRelativeTo(padre);
    }
}
package main;

import javax.swing.SwingUtilities;

import gui.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        // La ventana se crea en el hilo de Swing
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//Ventana principal, desde aquí se accede a Ventana perfil, Ventana Crear Torneo y Ventana Torenos

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String PISTA = "Buscar torneos";
    private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
    private static final String[] DIAS = { "L", "M", "X", "J", "V", "S", "D" };
    private static final Color COLOR_PARTIDO = new Color(255, 224, 178);

    //Calendario (Límite --> octubre 2026 - febrero 2027)
    private static final YearMonth MES_MIN = YearMonth.of(2026, 10);
    private static final YearMonth MES_MAX = YearMonth.of(2027, 2);

    // Días con partido (color calendario)
    private static final List<LocalDate> DIAS_PARTIDO = List.of(LocalDate.of(2026, 10, 24),
            LocalDate.of(2027, 1, 6), LocalDate.of(2027, 1, 9), LocalDate.of(2027, 1, 11),
            LocalDate.of(2027, 1, 15));

    private final JTextField buscador = new JTextField(PISTA, 28);
    private final JPanel panelTorneos = new JPanel(new GridLayout(0, 1));
    private final JPanel rejilla = new JPanel(new GridLayout(0, 7, 2, 2));
    private final JLabel lblMes = new JLabel("", SwingConstants.CENTER);
    private final JButton btnAnterior = new JButton("←");
    private final JButton btnSiguiente = new JButton("→");
    private YearMonth mes = YearMonth.of(2027, 1);

    public VentanaPrincipal() {
        setTitle("GameFlow - Gestión de Torneos Deportivos");
        setSize(1000, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Separar JFrame en 4 partes
        add(crearCabecera(), BorderLayout.NORTH);
        add(crearMenuLateral(), BorderLayout.WEST);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelDerecho(), BorderLayout.EAST);
    }

    // Título, perfil y buscador
    private JPanel crearCabecera() {
        JLabel titulo = new JLabel("GameFlow - Gestión de Torneos Deportivos");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        titulo.setForeground(Color.WHITE);

        JButton btnPerfil = new JButton("Perfil");
        btnPerfil.setPreferredSize(new Dimension(110, 36));
        btnPerfil.addActionListener(e -> new VentanaPerfil(this).setVisible(true));

        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(31, 78, 121));
        barra.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 8));
        barra.add(titulo, BorderLayout.CENTER);
        barra.add(btnPerfil, BorderLayout.EAST);

        // Al entrar en el buscador se borra la pista "Buscar torneos"
        buscador.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscador.getText().equals(PISTA)) {
                    buscador.setText("");
                }
            }
        });
        //Función buscador
        buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mostrarTorneos(buscador.getText().trim().toLowerCase());
            }
        });
        JPanel panelBuscador = new JPanel();
        panelBuscador.add(buscador);

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.add(barra, BorderLayout.NORTH);
        cabecera.add(panelBuscador, BorderLayout.CENTER);
        return cabecera;
    }

    //Menú de la izquierda (Inicio y Torneos)
    private JPanel crearMenuLateral() {
        JButton btnInicio = crearBotonMenu("› Inicio");
        btnInicio.addActionListener(e -> {
            buscador.setText(PISTA);
            mostrarTorneos("");
        });
        JButton btnTorneos = crearBotonMenu("› Torneos");
        btnTorneos.addActionListener(e -> new VentanaTorneos(this).setVisible(true));

        JPanel botones = new JPanel(new GridLayout(2, 1, 0, 10));
        botones.add(btnInicio);
        botones.add(btnTorneos);

        JPanel menu = new JPanel(new BorderLayout());
        menu.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));
        menu.setPreferredSize(new Dimension(150, 0));
        menu.add(botones, BorderLayout.NORTH);
        return menu;
    }

    // Botones
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setFont(boton.getFont().deriveFont(16f));
        return boton;
    }

    // Zona central (torneos destacador, clasificación)
    private JPanel crearPanelCentral() {
        mostrarTorneos("");
        JPanel destacados = new JPanel(new BorderLayout());
        destacados.setBorder(BorderFactory.createTitledBorder("Torneos destacados"));
        destacados.add(panelTorneos, BorderLayout.NORTH);

        //Clasificación ejemplo
        Object[][] datos = { { "1 Equipo A", 12 }, { "2 Equipo B", 9 }, { "3 Equipo C", 6 }, { "4 Equipo D", 0 } };
        JTable tabla = new JTable(datos, new String[] { "Clasificación Fútbol", "Puntos" });
        tabla.setRowHeight(26);
        tabla.setDefaultEditor(Object.class, null);
        tabla.getColumnModel().getColumn(1).setMaxWidth(100);

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 12));
        centro.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        centro.add(destacados);
        centro.add(new JScrollPane(tabla));
        return centro;
    }

    // Columna derecha(Crear torneo y calendario)
    private JPanel crearPanelDerecho() {
        JButton btnCrear = new JButton("+ Crear Torneo");
        btnCrear.setPreferredSize(new Dimension(0, 48));
        btnCrear.addActionListener(e -> new VentanaCrearTorneo(this).setVisible(true));

        //Flechas calendario
        btnAnterior.addActionListener(e -> {
            mes = mes.minusMonths(1);
            mostrarMes();
        });
        btnSiguiente.addActionListener(e -> {
            mes = mes.plusMonths(1);
            mostrarMes();
        });
        JPanel flechas = new JPanel(new GridLayout(1, 2, 6, 0));
        flechas.add(btnAnterior);
        flechas.add(btnSiguiente);

        JPanel calendario = new JPanel(new BorderLayout(0, 8));
        calendario.setBorder(BorderFactory.createTitledBorder("Calendario"));
        calendario.add(lblMes, BorderLayout.NORTH);
        calendario.add(rejilla, BorderLayout.CENTER);
        calendario.add(flechas, BorderLayout.SOUTH);
        mostrarMes();

        JPanel derecho = new JPanel(new BorderLayout(0, 10));
        derecho.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 15));
        derecho.setPreferredSize(new Dimension(240, 0));
        derecho.add(btnCrear, BorderLayout.NORTH);
        derecho.add(calendario, BorderLayout.CENTER);
        return derecho;
    }

    // Torneos destacados
    private void mostrarTorneos(String filtro) {
        panelTorneos.removeAll();
        for (String deporte : VentanaTorneos.DEPORTES) {
            if (deporte.toLowerCase().contains(filtro)) {
                JButton btnVer = new JButton("Ver");
                btnVer.addActionListener(e -> new VentanaTorneos(this).setVisible(true));

                JPanel fila = new JPanel(new BorderLayout());
                fila.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)));
                fila.add(new JLabel(deporte), BorderLayout.CENTER);
                fila.add(btnVer, BorderLayout.EAST);
                panelTorneos.add(fila);
            }
        }
        panelTorneos.revalidate();
        panelTorneos.repaint();
    }

    // Flechas calendario
    private void mostrarMes() {
        rejilla.removeAll();
        for (String dia : DIAS) {
            rejilla.add(new JLabel(dia, SwingConstants.CENTER));
        }
        //Huecos vacíos hasta el día de la semana en que empieza el mes
        for (int i = 1; i < mes.atDay(1).getDayOfWeek().getValue(); i++) {
            rejilla.add(new JLabel());
        }
        for (int dia = 1; dia <= mes.lengthOfMonth(); dia++) {
            JLabel celda = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
            celda.setOpaque(true);
            celda.setBackground(DIAS_PARTIDO.contains(mes.atDay(dia)) ? COLOR_PARTIDO : Color.WHITE);
            celda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            rejilla.add(celda);
        }
        lblMes.setText(MESES[mes.getMonthValue() - 1] + " " + mes.getYear());

        //Desactivar flechas calendario
        btnAnterior.setEnabled(mes.isAfter(MES_MIN));
        btnSiguiente.setEnabled(mes.isBefore(MES_MAX));
        rejilla.revalidate();
        rejilla.repaint();
    }
}
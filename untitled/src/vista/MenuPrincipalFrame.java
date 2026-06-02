package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {

    public MenuPrincipalFrame() {

        setTitle("BarberPro - Menú Principal");

        setSize(900, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        setLayout(null);

        Estilos.aplicarFondo(this);

        JMenuBar menuBar = new JMenuBar();

        menuBar.setBackground(Color.WHITE);

        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuServicios = new JMenu("Servicios");
        JMenu menuBarberos = new JMenu("Barberos");
        JMenu menuCitas = new JMenu("Citas");
        JMenu menuReportes = new JMenu("Reportes");
        JMenu menuSistema = new JMenu("Sistema");

        JMenuItem itemClientes =
                new JMenuItem("Gestionar Clientes");

        itemClientes.addActionListener(e ->
                new ClienteFrame().setVisible(true));

        JMenuItem itemServicios =
                new JMenuItem("Gestionar Servicios");

        itemServicios.addActionListener(e ->
                new ServicioFrame().setVisible(true));

        JMenuItem itemBarberos =
                new JMenuItem("Gestionar Barberos");

        itemBarberos.addActionListener(e ->
                new BarberoFrame().setVisible(true));

        JMenuItem itemCitas =
                new JMenuItem("Gestionar Citas");

        itemCitas.addActionListener(e ->
                new CitaFrame().setVisible(true));

        JMenuItem itemReportes =
                new JMenuItem("Ver Reportes");

        itemReportes.addActionListener(e ->
                new ReporteFrame().setVisible(true));

        JMenuItem itemSalir =
                new JMenuItem("Salir");

        itemSalir.addActionListener(e -> {

            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Desea salir del sistema?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        menuClientes.add(itemClientes);
        menuServicios.add(itemServicios);
        menuBarberos.add(itemBarberos);
        menuCitas.add(itemCitas);
        menuReportes.add(itemReportes);
        menuSistema.add(itemSalir);

        menuBar.add(menuClientes);
        menuBar.add(menuServicios);
        menuBar.add(menuBarberos);
        menuBar.add(menuCitas);
        menuBar.add(menuReportes);
        menuBar.add(menuSistema);

        setJMenuBar(menuBar);

        JPanel panelSuperior = new JPanel();

        panelSuperior.setBounds(
                0,
                0,
                900,
                120);

        panelSuperior.setBackground(
                Estilos.AZUL);

        panelSuperior.setLayout(null);

        add(panelSuperior);

        JLabel lblTitulo =
                new JLabel("BARBER PRO");

        lblTitulo.setBounds(
                310,
                25,
                300,
                40);

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32));

        lblTitulo.setForeground(
                Color.WHITE);

        panelSuperior.add(lblTitulo);

        JLabel lblSubtitulo =
                new JLabel(
                        "Control total de tu barbería");

        lblSubtitulo.setBounds(
                300,
                70,
                300,
                25);

        lblSubtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        lblSubtitulo.setForeground(
                Color.WHITE);

        panelSuperior.add(lblSubtitulo);

        JLabel lblBienvenida =
                new JLabel(
                        "Bienvenido al sistema BarberPro");

        lblBienvenida.setBounds(
                250,
                210,
                450,
                35);

        lblBienvenida.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24));

        lblBienvenida.setForeground(
                Estilos.AZUL_OSCURO);

        add(lblBienvenida);

        JLabel lblDescripcion =
                new JLabel(
                        "Administra clientes, servicios, barberos, citas y reportes.");

        lblDescripcion.setBounds(
                190,
                255,
                600,
                25);

        lblDescripcion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        add(lblDescripcion);

        JPanel tarjetaClientes =
                crearTarjeta(
                        "Clientes");

        tarjetaClientes.setBounds(
                120,
                350,
                180,
                100);

        add(tarjetaClientes);

        JPanel tarjetaServicios =
                crearTarjeta(
                        "Servicios");

        tarjetaServicios.setBounds(
                360,
                350,
                180,
                100);

        add(tarjetaServicios);

        JPanel tarjetaReportes =
                crearTarjeta(
                        "Reportes");

        tarjetaReportes.setBounds(
                600,
                350,
                180,
                100);

        add(tarjetaReportes);
    }

    private JPanel crearTarjeta(
            String titulo) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BorderLayout());

        panel.setBackground(
                Color.WHITE);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        Estilos.AZUL,
                        2));

        JLabel lbl =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER);

        lbl.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        lbl.setForeground(
                Estilos.AZUL_OSCURO);

        panel.add(
                lbl,
                BorderLayout.CENTER);

        return panel;
    }
}
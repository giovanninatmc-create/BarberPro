package vista;

import javax.swing.*;

public class MenuPrincipalFrame extends JFrame {

    public MenuPrincipalFrame() {
        setTitle("BarberPro - Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuServicios = new JMenu("Servicios");
        JMenu menuBarberos = new JMenu("Barberos");
        JMenu menuCitas = new JMenu("Citas");
        JMenu menuReportes = new JMenu("Reportes");
        JMenu menuSistema = new JMenu("Sistema");

        JMenuItem itemGestionarClientes = new JMenuItem("Gestionar clientes");
        itemGestionarClientes.addActionListener(e -> {
            new ClienteFrame().setVisible(true);
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea salir del sistema?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuClientes.add(itemGestionarClientes);
        menuSistema.add(itemSalir);

        menuBar.add(menuClientes);
        menuBar.add(menuServicios);
        menuBar.add(menuBarberos);
        menuBar.add(menuCitas);
        menuBar.add(menuReportes);
        menuBar.add(menuSistema);

        setJMenuBar(menuBar);

        JLabel lblTitulo = new JLabel("Bienvenido a BarberPro");
        lblTitulo.setBounds(280, 200, 250, 30);
        add(lblTitulo);
    }
}
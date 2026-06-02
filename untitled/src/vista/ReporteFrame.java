package vista;

import dao.ReporteDAO;

import javax.swing.*;
import java.awt.*;

public class ReporteFrame extends JFrame {

    private JLabel lblClientes;
    private JLabel lblBarberos;
    private JLabel lblServicios;
    private JLabel lblCitas;
    private JLabel lblIngresos;

    private ReporteDAO reporteDAO;

    public ReporteFrame() {

        reporteDAO = new ReporteDAO();

        setTitle("BarberPro - Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarReportes();
    }

    private void inicializarComponentes() {

        setLayout(null);

        Estilos.aplicarFondo(this);

        JLabel lblTitulo =
                new JLabel("DASHBOARD BARBERPRO");

        lblTitulo.setBounds(
                280,
                20,
                400,
                35);

        Estilos.titulo(lblTitulo);

        add(lblTitulo);

        JPanel panelClientes =
                crearTarjeta(
                        "Clientes",
                        60,
                        100);

        lblClientes =
                crearValorTarjeta();

        panelClientes.add(lblClientes);

        add(panelClientes);

        JPanel panelBarberos =
                crearTarjeta(
                        "Barberos",
                        330,
                        100);

        lblBarberos =
                crearValorTarjeta();

        panelBarberos.add(lblBarberos);

        add(panelBarberos);

        JPanel panelServicios =
                crearTarjeta(
                        "Servicios",
                        600,
                        100);

        lblServicios =
                crearValorTarjeta();

        panelServicios.add(lblServicios);

        add(panelServicios);

        JPanel panelCitas =
                crearTarjeta(
                        "Citas",
                        190,
                        300);

        lblCitas =
                crearValorTarjeta();

        panelCitas.add(lblCitas);

        add(panelCitas);

        JPanel panelIngresos =
                crearTarjeta(
                        "Ingresos",
                        500,
                        300);

        lblIngresos =
                crearValorTarjeta();

        panelIngresos.add(lblIngresos);

        add(panelIngresos);

        JButton btnActualizar =
                new JButton("Actualizar");

        btnActualizar.setBounds(
                380,
                500,
                140,
                35);

        Estilos.botonPrimario(
                btnActualizar);

        add(btnActualizar);

        btnActualizar.addActionListener(
                e -> cargarReportes());
    }

    private JPanel crearTarjeta(
            String titulo,
            int x,
            int y) {

        JPanel panel =
                new JPanel();

        panel.setLayout(null);

        panel.setBounds(
                x,
                y,
                220,
                140);

        panel.setBackground(
                Color.WHITE);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        Estilos.AZUL,
                        2));

        JLabel lblTitulo =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER);

        lblTitulo.setBounds(
                0,
                10,
                220,
                30);

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        lblTitulo.setForeground(
                Estilos.AZUL_OSCURO);

        panel.add(lblTitulo);

        return panel;
    }

    private JLabel crearValorTarjeta() {

        JLabel label =
                new JLabel(
                        "0",
                        SwingConstants.CENTER);

        label.setBounds(
                0,
                55,
                220,
                50);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        label.setForeground(
                Estilos.AZUL);

        return label;
    }

    private void cargarReportes() {

        lblClientes.setText(
                String.valueOf(
                        reporteDAO.contarClientes()));

        lblBarberos.setText(
                String.valueOf(
                        reporteDAO.contarBarberosActivos()));

        lblServicios.setText(
                String.valueOf(
                        reporteDAO.contarServiciosActivos()));

        lblCitas.setText(
                String.valueOf(
                        reporteDAO.contarCitasProgramadas()));

        lblIngresos.setText(
                "$" +
                        String.format(
                                "%.2f",
                                reporteDAO.calcularIngresosEstimados()));
    }
}
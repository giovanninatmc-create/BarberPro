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

        setTitle("BarberPro - Reportes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarReportes();
    }

    private void inicializarComponentes() {
        setLayout(null);

        JLabel lblTitulo = new JLabel("REPORTES GENERALES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(190, 30, 250, 30);
        add(lblTitulo);

        lblClientes = crearEtiqueta("Clientes registrados: 0", 80);
        lblBarberos = crearEtiqueta("Barberos activos: 0", 120);
        lblServicios = crearEtiqueta("Servicios activos: 0", 160);
        lblCitas = crearEtiqueta("Citas programadas: 0", 200);
        lblIngresos = crearEtiqueta("Ingresos estimados: $0.00", 240);

        add(lblClientes);
        add(lblBarberos);
        add(lblServicios);
        add(lblCitas);
        add(lblIngresos);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(230, 300, 130, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(e -> cargarReportes());
    }

    private JLabel crearEtiqueta(String texto, int y) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(160, y, 300, 25);
        return label;
    }

    private void cargarReportes() {
        lblClientes.setText("Clientes registrados: " + reporteDAO.contarClientes());
        lblBarberos.setText("Barberos activos: " + reporteDAO.contarBarberosActivos());
        lblServicios.setText("Servicios activos: " + reporteDAO.contarServiciosActivos());
        lblCitas.setText("Citas programadas: " + reporteDAO.contarCitasProgramadas());
        lblIngresos.setText("Ingresos estimados: $" + reporteDAO.calcularIngresosEstimados());
    }
}
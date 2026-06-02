package vista;

import dao.ServicioDAO;
import modelo.Servicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ServicioFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtDuracion;
    private JTextField txtPrecio;
    private JComboBox<String> cmbEstado;

    private JTable tablaServicios;
    private DefaultTableModel modeloTabla;

    private ServicioDAO servicioDAO;

    public ServicioFrame() {
        servicioDAO = new ServicioDAO();

        setTitle("BarberPro - Gestión de Servicios");
        setSize(820, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarServicios();
    }

    private void inicializarComponentes() {
        setLayout(null);
        Estilos.aplicarFondo(this);

        JLabel lblTitulo = new JLabel("GESTIÓN DE SERVICIOS");
        lblTitulo.setBounds(270, 20, 320, 35);
        Estilos.titulo(lblTitulo);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(40, 80, 100, 25);
        Estilos.texto(lblId);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 80, 80, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 120, 100, 25);
        Estilos.texto(lblNombre);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 120, 230, 25);
        add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(40, 160, 100, 25);
        Estilos.texto(lblDescripcion);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(140, 160, 230, 25);
        add(txtDescripcion);

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setBounds(40, 200, 100, 25);
        Estilos.texto(lblDuracion);
        add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setBounds(140, 200, 230, 25);
        add(txtDuracion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(40, 240, 100, 25);
        Estilos.texto(lblPrecio);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 240, 230, 25);
        add(txtPrecio);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(40, 280, 100, 25);
        Estilos.texto(lblEstado);
        add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        cmbEstado.setBounds(140, 280, 230, 25);
        add(cmbEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(470, 100, 120, 32);
        Estilos.botonPrimario(btnGuardar);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(620, 100, 120, 32);
        Estilos.botonSecundario(btnActualizar);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(470, 155, 120, 32);
        Estilos.botonSecundario(btnEliminar);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(620, 155, 120, 32);
        Estilos.botonSecundario(btnLimpiar);
        add(btnLimpiar);

        JSeparator separador = new JSeparator();
        separador.setBounds(40, 330, 700, 10);
        add(separador);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Duración");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Estado");

        tablaServicios = new JTable(modeloTabla);
        Estilos.tabla(tablaServicios);

        JScrollPane scroll = new JScrollPane(tablaServicios);
        scroll.setBounds(40, 350, 700, 130);
        add(scroll);

        btnGuardar.addActionListener(e -> guardarServicio());
        btnActualizar.addActionListener(e -> actualizarServicio());
        btnEliminar.addActionListener(e -> eliminarServicio());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarServicio();
            }
        });
    }

    private void guardarServicio() {
        if (txtNombre.getText().isEmpty() ||
                txtDuracion.getText().isEmpty() ||
                txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, duración y precio son obligatorios");
            return;
        }

        try {
            Servicio servicio = new Servicio();
            servicio.setNombre(txtNombre.getText());
            servicio.setDescripcion(txtDescripcion.getText());
            servicio.setDuracion(Integer.parseInt(txtDuracion.getText()));
            servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));
            servicio.setEstado(cmbEstado.getSelectedItem().toString());

            if (servicioDAO.guardar(servicio)) {
                JOptionPane.showMessageDialog(this, "Servicio guardado correctamente");
                cargarServicios();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar servicio");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duración y precio deben ser valores numéricos");
        }
    }

    private void cargarServicios() {
        modeloTabla.setRowCount(0);

        List<Servicio> servicios = servicioDAO.listar();

        for (Servicio servicio : servicios) {
            Object[] fila = {
                    servicio.getIdServicio(),
                    servicio.getNombre(),
                    servicio.getDescripcion(),
                    servicio.getDuracion(),
                    servicio.getPrecio(),
                    servicio.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void seleccionarServicio() {
        int fila = tablaServicios.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtDuracion.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtPrecio.setText(modeloTabla.getValueAt(fila, 4).toString());
            cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 5).toString());
        }
    }

    private void actualizarServicio() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio de la tabla");
            return;
        }

        try {
            Servicio servicio = new Servicio();
            servicio.setIdServicio(Integer.parseInt(txtId.getText()));
            servicio.setNombre(txtNombre.getText());
            servicio.setDescripcion(txtDescripcion.getText());
            servicio.setDuracion(Integer.parseInt(txtDuracion.getText()));
            servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));
            servicio.setEstado(cmbEstado.getSelectedItem().toString());

            if (servicioDAO.actualizar(servicio)) {
                JOptionPane.showMessageDialog(this, "Servicio actualizado correctamente");
                cargarServicios();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar servicio");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duración y precio deben ser valores numéricos");
        }
    }

    private void eliminarServicio() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio de la tabla");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar este servicio?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            int idServicio = Integer.parseInt(txtId.getText());

            if (servicioDAO.eliminar(idServicio)) {
                JOptionPane.showMessageDialog(this, "Servicio eliminado correctamente");
                cargarServicios();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar servicio");
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtDuracion.setText("");
        txtPrecio.setText("");
        cmbEstado.setSelectedIndex(0);
        tablaServicios.clearSelection();
    }
}
package vista;

import dao.*;
import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CitaFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtFecha;
    private JTextField txtHora;

    private JComboBox<Cliente> cmbCliente;
    private JComboBox<Barbero> cmbBarbero;
    private JComboBox<Servicio> cmbServicio;
    private JComboBox<String> cmbEstado;

    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;

    private CitaDAO citaDAO;
    private ClienteDAO clienteDAO;
    private BarberoDAO barberoDAO;
    private ServicioDAO servicioDAO;

    public CitaFrame() {
        citaDAO = new CitaDAO();
        clienteDAO = new ClienteDAO();
        barberoDAO = new BarberoDAO();
        servicioDAO = new ServicioDAO();

        setTitle("BarberPro - Gestión de Citas");
        setSize(950, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarCombos();
        cargarCitas();
    }

    private void inicializarComponentes() {
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE CITAS");
        lblTitulo.setBounds(400, 20, 200, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(40, 70, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 70, 100, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(40, 110, 100, 25);
        add(lblCliente);

        cmbCliente = new JComboBox<>();
        cmbCliente.setBounds(150, 110, 260, 25);
        add(cmbCliente);

        JLabel lblBarbero = new JLabel("Barbero:");
        lblBarbero.setBounds(40, 150, 100, 25);
        add(lblBarbero);

        cmbBarbero = new JComboBox<>();
        cmbBarbero.setBounds(150, 150, 260, 25);
        add(cmbBarbero);

        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setBounds(40, 190, 100, 25);
        add(lblServicio);

        cmbServicio = new JComboBox<>();
        cmbServicio.setBounds(150, 190, 260, 25);
        add(cmbServicio);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(40, 230, 100, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(150, 230, 260, 25);
        txtFecha.setText(LocalDate.now().toString());
        add(txtFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(40, 270, 100, 25);
        add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(150, 270, 260, 25);
        txtHora.setText("10:00");
        add(txtHora);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(40, 310, 100, 25);
        add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{"Programada", "Cancelada", "Finalizada"});
        cmbEstado.setBounds(150, 310, 260, 25);
        add(cmbEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(500, 100, 130, 30);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(680, 100, 130, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(500, 150, 130, 30);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(680, 150, 130, 30);
        add(btnLimpiar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Barbero");
        modeloTabla.addColumn("Servicio");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Estado");

        tablaCitas = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaCitas);
        scroll.setBounds(40, 370, 850, 120);
        add(scroll);

        btnGuardar.addActionListener(e -> guardarCita());
        btnActualizar.addActionListener(e -> actualizarCita());
        btnEliminar.addActionListener(e -> eliminarCita());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarCita();
            }
        });
    }

    private void cargarCombos() {
        cmbCliente.removeAllItems();
        cmbBarbero.removeAllItems();
        cmbServicio.removeAllItems();

        for (Cliente cliente : clienteDAO.obtenerTodos()) {
            cmbCliente.addItem(cliente);
        }

        for (Barbero barbero : barberoDAO.obtenerTodos()) {
            cmbBarbero.addItem(barbero);
        }

        for (Servicio servicio : servicioDAO.obtenerTodos()) {
            cmbServicio.addItem(servicio);
        }
    }

    private void guardarCita() {
        if (cmbCliente.getSelectedItem() == null ||
                cmbBarbero.getSelectedItem() == null ||
                cmbServicio.getSelectedItem() == null ||
                txtFecha.getText().isEmpty() ||
                txtHora.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        try {
            Cita cita = new Cita();
            cita.setCliente((Cliente) cmbCliente.getSelectedItem());
            cita.setBarbero((Barbero) cmbBarbero.getSelectedItem());
            cita.setServicio((Servicio) cmbServicio.getSelectedItem());
            cita.setFecha(LocalDate.parse(txtFecha.getText()));
            cita.setHora(LocalTime.parse(txtHora.getText()));
            cita.setEstado(cmbEstado.getSelectedItem().toString());

            if (citaDAO.guardar(cita)) {
                JOptionPane.showMessageDialog(this, "Cita guardada correctamente");
                cargarCitas();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar cita");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Formato inválido. Usa fecha AAAA-MM-DD y hora HH:MM"
            );
        }
    }

    private void cargarCitas() {
        modeloTabla.setRowCount(0);

        List<Cita> citas = citaDAO.listar();

        for (Cita cita : citas) {
            Object[] fila = {
                    cita.getIdCita(),
                    cita.getCliente().getNombre(),
                    cita.getBarbero().getNombre(),
                    cita.getServicio().getNombre(),
                    cita.getFecha(),
                    cita.getHora(),
                    cita.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void seleccionarCita() {
        int fila = tablaCitas.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            seleccionarComboPorNombre(cmbCliente, modeloTabla.getValueAt(fila, 1).toString());
            seleccionarComboPorNombre(cmbBarbero, modeloTabla.getValueAt(fila, 2).toString());
            seleccionarComboPorNombre(cmbServicio, modeloTabla.getValueAt(fila, 3).toString());
            txtFecha.setText(modeloTabla.getValueAt(fila, 4).toString());
            txtHora.setText(modeloTabla.getValueAt(fila, 5).toString());
            cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 6).toString());
        }
    }

    private void seleccionarComboPorNombre(JComboBox<?> combo, String nombre) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);

            if (item.toString().equals(nombre)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void actualizarCita() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita de la tabla");
            return;
        }

        try {
            Cita cita = new Cita();
            cita.setIdCita(Integer.parseInt(txtId.getText()));
            cita.setCliente((Cliente) cmbCliente.getSelectedItem());
            cita.setBarbero((Barbero) cmbBarbero.getSelectedItem());
            cita.setServicio((Servicio) cmbServicio.getSelectedItem());
            cita.setFecha(LocalDate.parse(txtFecha.getText()));
            cita.setHora(LocalTime.parse(txtHora.getText()));
            cita.setEstado(cmbEstado.getSelectedItem().toString());

            if (citaDAO.actualizar(cita)) {
                JOptionPane.showMessageDialog(this, "Cita actualizada correctamente");
                cargarCitas();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar cita");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Formato inválido. Usa fecha AAAA-MM-DD y hora HH:MM"
            );
        }
    }

    private void eliminarCita() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita de la tabla");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar esta cita?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            int idCita = Integer.parseInt(txtId.getText());

            if (citaDAO.eliminar(idCita)) {
                JOptionPane.showMessageDialog(this, "Cita eliminada correctamente");
                cargarCitas();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar cita");
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtFecha.setText(LocalDate.now().toString());
        txtHora.setText("10:00");
        cmbEstado.setSelectedIndex(0);

        if (cmbCliente.getItemCount() > 0) cmbCliente.setSelectedIndex(0);
        if (cmbBarbero.getItemCount() > 0) cmbBarbero.setSelectedIndex(0);
        if (cmbServicio.getItemCount() > 0) cmbServicio.setSelectedIndex(0);

        tablaCitas.clearSelection();
    }
}
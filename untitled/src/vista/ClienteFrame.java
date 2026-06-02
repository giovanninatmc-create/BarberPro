package vista;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class ClienteFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private ClienteDAO clienteDAO;

    public ClienteFrame() {
        clienteDAO = new ClienteDAO();

        setTitle("BarberPro - Gestión de Clientes");
        setSize(760, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarClientes();
    }

    private void inicializarComponentes() {
        setLayout(null);
        Estilos.aplicarFondo(this);

        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES");
        lblTitulo.setBounds(260, 20, 300, 35);
        Estilos.titulo(lblTitulo);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(40, 80, 100, 25);
        Estilos.texto(lblId);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 80, 80, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 120, 100, 25);
        Estilos.texto(lblNombre);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 120, 220, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(40, 160, 100, 25);
        Estilos.texto(lblTelefono);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(130, 160, 220, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(40, 200, 100, 25);
        Estilos.texto(lblCorreo);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(130, 200, 220, 25);
        add(txtCorreo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(410, 90, 120, 32);
        Estilos.botonPrimario(btnGuardar);
        add(btnGuardar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(560, 90, 120, 32);
        Estilos.botonSecundario(btnActualizar);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(410, 145, 120, 32);
        Estilos.botonSecundario(btnEliminar);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(560, 145, 120, 32);
        Estilos.botonSecundario(btnLimpiar);
        add(btnLimpiar);

        JSeparator separador = new JSeparator();
        separador.setBounds(40, 250, 650, 10);
        add(separador);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Fecha Registro");

        tablaClientes = new JTable(modeloTabla);
        Estilos.tabla(tablaClientes);

        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(40, 270, 650, 180);
        add(scroll);

        btnGuardar.addActionListener(e -> guardarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarCliente();
            }
        });
    }

    private void guardarCliente() {
        if (txtNombre.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y teléfono son obligatorios");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setCorreo(txtCorreo.getText());
        cliente.setFechaRegistro(LocalDate.now());

        if (clienteDAO.guardar(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente");
            cargarClientes();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente");
        }
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);

        List<Cliente> clientes = clienteDAO.listar();

        for (Cliente cliente : clientes) {
            Object[] fila = {
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getFechaRegistro()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void seleccionarCliente() {
        int fila = tablaClientes.getSelectedRow();

        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtTelefono.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtCorreo.setText(modeloTabla.getValueAt(fila, 3).toString());
        }
    }

    private void actualizarCliente() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(txtId.getText()));
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setCorreo(txtCorreo.getText());

        if (clienteDAO.actualizar(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
            cargarClientes();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente");
        }
    }

    private void eliminarCliente() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas eliminar este cliente?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            int idCliente = Integer.parseInt(txtId.getText());

            if (clienteDAO.eliminar(idCliente)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                cargarClientes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar cliente");
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        tablaClientes.clearSelection();
    }
}
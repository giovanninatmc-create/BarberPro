package vista;

import dao.BarberoDAO;
import modelo.Barbero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BarberoFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtEspecialidad;
    private JTextField txtHorario;

    private JComboBox<String> cmbEstado;

    private JTable tablaBarberos;
    private DefaultTableModel modeloTabla;

    private BarberoDAO barberoDAO;

    public BarberoFrame() {

        barberoDAO = new BarberoDAO();

        setTitle("BarberPro - Gestión de Barberos");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarBarberos();
    }

    private void inicializarComponentes() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE BARBEROS");
        lblTitulo.setBounds(360, 20, 200, 30);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(40, 70, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 70, 100, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 110, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 110, 250, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(40, 150, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 150, 250, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(40, 190, 100, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 190, 250, 25);
        add(txtCorreo);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(40, 230, 100, 25);
        add(lblEspecialidad);

        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(140, 230, 250, 25);
        add(txtEspecialidad);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setBounds(40, 270, 100, 25);
        add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(140, 270, 250, 25);
        add(txtHorario);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(40, 310, 100, 25);
        add(lblEstado);

        cmbEstado = new JComboBox<>(
                new String[]{"Activo", "Inactivo"}
        );

        cmbEstado.setBounds(140, 310, 250, 25);
        add(cmbEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(500, 90, 130, 30);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(680, 90, 130, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(500, 140, 130, 30);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(680, 140, 130, 30);
        add(btnLimpiar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Especialidad");
        modeloTabla.addColumn("Horario");
        modeloTabla.addColumn("Estado");

        tablaBarberos = new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tablaBarberos);

        scroll.setBounds(40, 370, 800, 120);

        add(scroll);

        btnGuardar.addActionListener(e -> guardarBarbero());
        btnActualizar.addActionListener(e -> actualizarBarbero());
        btnEliminar.addActionListener(e -> eliminarBarbero());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaBarberos.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseClicked(
                            java.awt.event.MouseEvent evt) {
                        seleccionarBarbero();
                    }
                });
    }

    private void guardarBarbero() {

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El nombre es obligatorio");
            return;
        }

        Barbero barbero = new Barbero();

        barbero.setNombre(txtNombre.getText());
        barbero.setTelefono(txtTelefono.getText());
        barbero.setCorreo(txtCorreo.getText());
        barbero.setEspecialidad(txtEspecialidad.getText());
        barbero.setHorarioDisponible(txtHorario.getText());
        barbero.setEstado(
                cmbEstado.getSelectedItem().toString());

        if (barberoDAO.guardar(barbero)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Barbero guardado correctamente");

            cargarBarberos();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar barbero");
        }
    }

    private void cargarBarberos() {

        modeloTabla.setRowCount(0);

        List<Barbero> barberos =
                barberoDAO.listar();

        for (Barbero barbero : barberos) {

            Object[] fila = {
                    barbero.getIdBarbero(),
                    barbero.getNombre(),
                    barbero.getTelefono(),
                    barbero.getCorreo(),
                    barbero.getEspecialidad(),
                    barbero.getHorarioDisponible(),
                    barbero.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void seleccionarBarbero() {

        int fila =
                tablaBarberos.getSelectedRow();

        if (fila >= 0) {

            txtId.setText(
                    modeloTabla.getValueAt(fila,0).toString());

            txtNombre.setText(
                    modeloTabla.getValueAt(fila,1).toString());

            txtTelefono.setText(
                    modeloTabla.getValueAt(fila,2).toString());

            txtCorreo.setText(
                    modeloTabla.getValueAt(fila,3).toString());

            txtEspecialidad.setText(
                    modeloTabla.getValueAt(fila,4).toString());

            txtHorario.setText(
                    modeloTabla.getValueAt(fila,5).toString());

            cmbEstado.setSelectedItem(
                    modeloTabla.getValueAt(fila,6).toString());
        }
    }

    private void actualizarBarbero() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un barbero");

            return;
        }

        Barbero barbero = new Barbero();

        barbero.setIdBarbero(
                Integer.parseInt(txtId.getText()));

        barbero.setNombre(txtNombre.getText());
        barbero.setTelefono(txtTelefono.getText());
        barbero.setCorreo(txtCorreo.getText());
        barbero.setEspecialidad(txtEspecialidad.getText());
        barbero.setHorarioDisponible(txtHorario.getText());

        barbero.setEstado(
                cmbEstado.getSelectedItem().toString());

        if (barberoDAO.actualizar(barbero)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Barbero actualizado correctamente");

            cargarBarberos();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar barbero");
        }
    }

    private void eliminarBarbero() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un barbero");

            return;
        }

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar este barbero?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            int idBarbero =
                    Integer.parseInt(txtId.getText());

            if (barberoDAO.eliminar(idBarbero)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Barbero eliminado correctamente");

                cargarBarberos();
                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar barbero");
            }
        }
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtEspecialidad.setText("");
        txtHorario.setText("");

        cmbEstado.setSelectedIndex(0);

        tablaBarberos.clearSelection();
    }
}
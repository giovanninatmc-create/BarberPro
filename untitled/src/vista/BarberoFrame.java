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
        setSize(920, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
        cargarBarberos();
    }

    private void inicializarComponentes() {

        setLayout(null);
        Estilos.aplicarFondo(this);

        JLabel lblTitulo =
                new JLabel("GESTIÓN DE BARBEROS");

        lblTitulo.setBounds(
                300,
                20,
                350,
                35);

        Estilos.titulo(lblTitulo);

        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(40, 80, 100, 25);
        Estilos.texto(lblId);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 80, 100, 25);
        txtId.setEditable(false);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 120, 100, 25);
        Estilos.texto(lblNombre);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 120, 250, 25);
        add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(40, 160, 100, 25);
        Estilos.texto(lblTelefono);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 160, 250, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(40, 200, 100, 25);
        Estilos.texto(lblCorreo);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 200, 250, 25);
        add(txtCorreo);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(40, 240, 100, 25);
        Estilos.texto(lblEspecialidad);
        add(lblEspecialidad);

        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(140, 240, 250, 25);
        add(txtEspecialidad);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setBounds(40, 280, 100, 25);
        Estilos.texto(lblHorario);
        add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(140, 280, 250, 25);
        add(txtHorario);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(40, 320, 100, 25);
        Estilos.texto(lblEstado);
        add(lblEstado);

        cmbEstado = new JComboBox<>(
                new String[]{"Activo", "Inactivo"}
        );

        cmbEstado.setBounds(140, 320, 250, 25);
        add(cmbEstado);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(520, 100, 130, 32);
        Estilos.botonPrimario(btnGuardar);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(700, 100, 130, 32);
        Estilos.botonSecundario(btnActualizar);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(520, 155, 130, 32);
        Estilos.botonSecundario(btnEliminar);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(700, 155, 130, 32);
        Estilos.botonSecundario(btnLimpiar);
        add(btnLimpiar);

        JSeparator separador = new JSeparator();
        separador.setBounds(40, 380, 820, 10);
        add(separador);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Especialidad");
        modeloTabla.addColumn("Horario");
        modeloTabla.addColumn("Estado");

        tablaBarberos = new JTable(modeloTabla);

        Estilos.tabla(tablaBarberos);

        JScrollPane scroll =
                new JScrollPane(tablaBarberos);

        scroll.setBounds(
                40,
                400,
                820,
                120);

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

        int fila = tablaBarberos.getSelectedRow();

        if (fila >= 0) {

            txtId.setText(modeloTabla.getValueAt(fila,0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila,1).toString());
            txtTelefono.setText(modeloTabla.getValueAt(fila,2).toString());
            txtCorreo.setText(modeloTabla.getValueAt(fila,3).toString());
            txtEspecialidad.setText(modeloTabla.getValueAt(fila,4).toString());
            txtHorario.setText(modeloTabla.getValueAt(fila,5).toString());

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
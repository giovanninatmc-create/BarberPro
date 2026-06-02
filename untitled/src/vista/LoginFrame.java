package vista;

import dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginFrame() {
        setTitle("BarberPro - Inicio de Sesión");
        setSize(430, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(null);
        Estilos.aplicarFondo(this);

        JLabel lblTitulo = new JLabel("BARBER PRO");
        lblTitulo.setBounds(130, 35, 220, 35);
        Estilos.titulo(lblTitulo);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Sistema de gestión de barbería");
        lblSubtitulo.setBounds(105, 70, 260, 25);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(Estilos.AZUL);
        add(lblSubtitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(55, 125, 100, 25);
        Estilos.texto(lblUsuario);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(160, 125, 200, 28);
        add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(55, 165, 100, 25);
        Estilos.texto(lblPassword);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 165, 200, 28);
        add(txtPassword);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(160, 220, 130, 35);
        Estilos.botonPrimario(btnIngresar);
        add(btnIngresar);

        btnIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtPassword.getPassword());

        UsuarioDAO dao = new UsuarioDAO();
        boolean acceso = dao.validarLogin(usuario, contrasena);

        if (acceso) {
            JOptionPane.showMessageDialog(this, "Bienvenido a BarberPro");
            new MenuPrincipalFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
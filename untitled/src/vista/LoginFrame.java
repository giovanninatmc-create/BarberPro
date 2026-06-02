package vista;

import dao.UsuarioDAO;
import vista.MenuPrincipalFrame;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    public LoginFrame() {

        setTitle("BarberPro - Inicio de Sesión");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("BARBERPRO");

        lblTitulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20));

        lblTitulo.setBounds(
                130,
                20,
                150,
                30);

        add(lblTitulo);

        JLabel lblUsuario =
                new JLabel("Usuario:");

        lblUsuario.setBounds(
                50,
                80,
                100,
                25);

        add(lblUsuario);

        txtUsuario =
                new JTextField();

        txtUsuario.setBounds(
                140,
                80,
                180,
                25);

        add(txtUsuario);

        JLabel lblPassword =
                new JLabel("Contraseña:");

        lblPassword.setBounds(
                50,
                120,
                100,
                25);

        add(lblPassword);

        txtPassword =
                new JPasswordField();

        txtPassword.setBounds(
                140,
                120,
                180,
                25);

        add(txtPassword);

        btnIngresar =
                new JButton("Ingresar");

        btnIngresar.setBounds(
                140,
                165,
                120,
                30);

        add(btnIngresar);

        btnIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String usuario =
                txtUsuario.getText();

        String contrasena =
                new String(
                        txtPassword.getPassword());

        UsuarioDAO dao =
                new UsuarioDAO();

        boolean acceso =
                dao.validarLogin(
                        usuario,
                        contrasena);

        if (acceso) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido a BarberPro");

            new MenuPrincipalFrame().setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos");
        }
    }
}
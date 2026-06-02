package vista;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Estilos {

    public static final Color AZUL = new Color(25, 118, 210);
    public static final Color AZUL_OSCURO = new Color(13, 71, 161);
    public static final Color AZUL_CLARO = new Color(227, 242, 253);
    public static final Color BLANCO = Color.WHITE;
    public static final Color FONDO = new Color(248, 249, 250);
    public static final Color TEXTO = new Color(38, 50, 56);

    public static void aplicarFondo(JFrame frame) {
        frame.getContentPane().setBackground(FONDO);
    }

    public static void titulo(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(AZUL_OSCURO);
    }

    public static void texto(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(TEXTO);
    }

    public static void botonPrimario(JButton button) {
        button.setBackground(AZUL);
        button.setForeground(BLANCO);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
    }

    public static void botonSecundario(JButton button) {
        button.setBackground(AZUL_CLARO);
        button.setForeground(AZUL_OSCURO);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
    }

    public static void tabla(JTable tabla) {
        tabla.setRowHeight(25);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(AZUL);
        header.setForeground(BLANCO);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
    }
}
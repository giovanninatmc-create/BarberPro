package dao;

import conexion.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarLogin(String usuario, String contrasena) {

        String sql = """
                SELECT *
                FROM usuario
                WHERE nombre_usuario = ?
                AND contrasena = ?
                AND estado = 'Activo'
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            System.out.println("Error al validar login:");
            e.printStackTrace();
            return false;
        }
    }
}
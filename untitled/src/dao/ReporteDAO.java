package dao;

import conexion.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReporteDAO {

    public int contarClientes() {
        return contar("SELECT COUNT(*) FROM cliente");
    }

    public int contarBarberosActivos() {
        return contar("SELECT COUNT(*) FROM barbero WHERE estado = 'Activo'");
    }

    public int contarServiciosActivos() {
        return contar("SELECT COUNT(*) FROM servicio WHERE estado = 'Activo'");
    }

    public int contarCitasProgramadas() {
        return contar("SELECT COUNT(*) FROM cita WHERE estado = 'Programada'");
    }

    public double calcularIngresosEstimados() {

        String sql = """
                SELECT IFNULL(SUM(s.precio), 0) AS total
                FROM cita c
                INNER JOIN servicio s
                    ON c.id_servicio = s.id_servicio
                WHERE c.estado = 'Programada'
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (Exception e) {
            System.out.println("Error al calcular ingresos:");
            e.printStackTrace();
        }

        return 0;
    }

    private int contar(String sql) {

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error en reporte:");
            e.printStackTrace();
        }

        return 0;
    }
}
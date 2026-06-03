package dao;

import conexion.ConexionBD;
import modelo.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    public boolean guardar(Servicio servicio) {

        String sql = """
                INSERT INTO servicio
                (nombre, descripcion, duracion, precio, estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setInt(3, servicio.getDuracion());
            ps.setDouble(4, servicio.getPrecio());
            ps.setString(5, servicio.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al guardar servicio:");
            e.printStackTrace();

            return false;
        }
    }

    public List<Servicio> listar() {

        List<Servicio> servicios = new ArrayList<>();

        String sql = """
                SELECT id_servicio,
                       nombre,
                       descripcion,
                       duracion,
                       precio,
                       estado
                FROM servicio
                ORDER BY id_servicio DESC
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Servicio servicio = new Servicio();

                servicio.setIdServicio(
                        rs.getInt("id_servicio"));

                servicio.setNombre(
                        rs.getString("nombre"));

                servicio.setDescripcion(
                        rs.getString("descripcion"));

                servicio.setDuracion(
                        rs.getInt("duracion"));

                servicio.setPrecio(
                        rs.getDouble("precio"));

                servicio.setEstado(
                        rs.getString("estado"));

                servicios.add(servicio);
            }

        } catch (Exception e) {

            System.out.println("Error al listar servicios:");
            e.printStackTrace();
        }

        return servicios;
    }

    public boolean actualizar(Servicio servicio) {

        String sql = """
                UPDATE servicio
                SET nombre = ?,
                    descripcion = ?,
                    duracion = ?,
                    precio = ?,
                    estado = ?
                WHERE id_servicio = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setInt(3, servicio.getDuracion());
            ps.setDouble(4, servicio.getPrecio());
            ps.setString(5, servicio.getEstado());
            ps.setInt(6, servicio.getIdServicio());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al actualizar servicio:");
            e.printStackTrace();

            return false;
        }
    }

    public boolean eliminar(int idServicio) {

        String sql = """
                DELETE FROM servicio
                WHERE id_servicio = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idServicio);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Error al eliminar servicio:");
            e.printStackTrace();

            return false;
        }
    }

    public List<Servicio> obtenerTodos() {
        return listar();
    }
}
package dao;

import conexion.ConexionBD;
import modelo.Barbero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BarberoDAO {

    public boolean guardar(Barbero barbero) {

        String sql = """
                INSERT INTO barbero
                (nombre, telefono, correo, especialidad, horario_disponible, estado)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, barbero.getNombre());
            ps.setString(2, barbero.getTelefono());
            ps.setString(3, barbero.getCorreo());
            ps.setString(4, barbero.getEspecialidad());
            ps.setString(5, barbero.getHorarioDisponible());
            ps.setString(6, barbero.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar barbero:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Barbero> listar() {

        List<Barbero> lista = new ArrayList<>();

        String sql = """
                SELECT id_barbero, nombre, telefono, correo, especialidad, horario_disponible, estado
                FROM barbero
                ORDER BY id_barbero DESC
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Barbero barbero = new Barbero();

                barbero.setIdBarbero(rs.getInt("id_barbero"));
                barbero.setNombre(rs.getString("nombre"));
                barbero.setTelefono(rs.getString("telefono"));
                barbero.setCorreo(rs.getString("correo"));
                barbero.setEspecialidad(rs.getString("especialidad"));
                barbero.setHorarioDisponible(rs.getString("horario_disponible"));
                barbero.setEstado(rs.getString("estado"));

                lista.add(barbero);
            }

        } catch (Exception e) {
            System.out.println("Error al listar barberos:");
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizar(Barbero barbero) {

        String sql = """
                UPDATE barbero
                SET nombre = ?,
                    telefono = ?,
                    correo = ?,
                    especialidad = ?,
                    horario_disponible = ?,
                    estado = ?
                WHERE id_barbero = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, barbero.getNombre());
            ps.setString(2, barbero.getTelefono());
            ps.setString(3, barbero.getCorreo());
            ps.setString(4, barbero.getEspecialidad());
            ps.setString(5, barbero.getHorarioDisponible());
            ps.setString(6, barbero.getEstado());
            ps.setInt(7, barbero.getIdBarbero());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar barbero:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idBarbero) {

        String sql = """
                DELETE FROM barbero
                WHERE id_barbero = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idBarbero);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar barbero:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Barbero> obtenerTodos() {
        return listar();
    }
}
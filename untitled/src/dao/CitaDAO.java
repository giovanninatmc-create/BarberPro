package dao;

import conexion.ConexionBD;
import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDAO {

    public boolean guardar(Cita cita) {
        String sql = """
                INSERT INTO cita(fecha, hora, estado, id_cliente, id_barbero, id_servicio)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(cita.getFecha()));
            ps.setTime(2, Time.valueOf(cita.getHora()));
            ps.setString(3, cita.getEstado());
            ps.setInt(4, cita.getCliente().getIdCliente());
            ps.setInt(5, cita.getBarbero().getIdBarbero());
            ps.setInt(6, cita.getServicio().getIdServicio());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar cita: " + e.getMessage());
            return false;
        }
    }

    public List<Cita> listar() {
        List<Cita> citas = new ArrayList<>();

        String sql = """
                SELECT c.id_cita, c.fecha, c.hora, c.estado,
                       cl.id_cliente, cl.nombre AS cliente_nombre,
                       b.id_barbero, b.nombre AS barbero_nombre,
                       s.id_servicio, s.nombre AS servicio_nombre
                FROM cita c
                INNER JOIN cliente cl ON c.id_cliente = cl.id_cliente
                INNER JOIN barbero b ON c.id_barbero = b.id_barbero
                INNER JOIN servicio s ON c.id_servicio = s.id_servicio
                ORDER BY c.id_cita DESC
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("cliente_nombre"));

                Barbero barbero = new Barbero();
                barbero.setIdBarbero(rs.getInt("id_barbero"));
                barbero.setNombre(rs.getString("barbero_nombre"));

                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("id_servicio"));
                servicio.setNombre(rs.getString("servicio_nombre"));

                Cita cita = new Cita();
                cita.setIdCita(rs.getInt("id_cita"));
                cita.setFecha(rs.getDate("fecha").toLocalDate());
                cita.setHora(rs.getTime("hora").toLocalTime());
                cita.setEstado(rs.getString("estado"));
                cita.setCliente(cliente);
                cita.setBarbero(barbero);
                cita.setServicio(servicio);

                citas.add(cita);
            }

        } catch (Exception e) {
            System.out.println("Error al listar citas: " + e.getMessage());
        }

        return citas;
    }

    public boolean actualizar(Cita cita) {
        String sql = """
                UPDATE cita
                SET fecha = ?, hora = ?, estado = ?, id_cliente = ?, id_barbero = ?, id_servicio = ?
                WHERE id_cita = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(cita.getFecha()));
            ps.setTime(2, Time.valueOf(cita.getHora()));
            ps.setString(3, cita.getEstado());
            ps.setInt(4, cita.getCliente().getIdCliente());
            ps.setInt(5, cita.getBarbero().getIdBarbero());
            ps.setInt(6, cita.getServicio().getIdServicio());
            ps.setInt(7, cita.getIdCita());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCita) {
        String sql = "DELETE FROM cita WHERE id_cita = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }
    public boolean existeCitaEnHorario(int idBarbero, LocalDate fecha, LocalTime hora) {
        String sql = """
            SELECT COUNT(*) 
            FROM cita
            WHERE id_barbero = ?
            AND fecha = ?
            AND hora = ?
            AND estado = 'Programada'
            """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idBarbero);
            ps.setDate(2, Date.valueOf(fecha));
            ps.setTime(3, Time.valueOf(hora));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            System.out.println("Error al validar horario: " + e.getMessage());
        }

        return false;
    }
}

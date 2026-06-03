package dao;

import conexion.ConexionBD;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean guardar(Cliente cliente) {

        String sql = """
                INSERT INTO cliente
                (nombre, telefono, correo, fecha_registro)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getFechaRegistro().toString());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar cliente:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = """
                SELECT id_cliente, nombre, telefono, correo, fecha_registro
                FROM cliente
                ORDER BY id_cliente DESC
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));

                String fecha = rs.getString("fecha_registro");

                if (fecha != null && !fecha.isEmpty()) {
                    cliente.setFechaRegistro(LocalDate.parse(fecha));
                }

                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar clientes:");
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean actualizar(Cliente cliente) {

        String sql = """
                UPDATE cliente
                SET nombre = ?, telefono = ?, correo = ?
                WHERE id_cliente = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setInt(4, cliente.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar cliente:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idCliente) {

        String sql = """
                DELETE FROM cliente
                WHERE id_cliente = ?
                """;

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar cliente:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> obtenerTodos() {
        return listar();
    }
}
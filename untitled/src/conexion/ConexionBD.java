package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/barberpro_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Kokodrilo99@";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return conexion;
    }
}
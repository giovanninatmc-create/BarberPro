package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String URL =
            "jdbc:sqlite:src/database/barberpro.db";

    public static Connection conectar() {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conexion =
                    DriverManager.getConnection(URL);

            System.out.println("Conexión SQLite exitosa");

            return conexion;

        } catch (Exception e) {
            System.out.println("Error SQLite:");
            e.printStackTrace();
            return null;
        }
    }
}
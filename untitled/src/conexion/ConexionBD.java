package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionBD {

    private static final String URL =
            "jdbc:sqlite:src/database/barberpro2.db";

    public static Connection conectar() {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conexion =
                    DriverManager.getConnection(URL);

            try (Statement st = conexion.createStatement()) {
                st.execute("PRAGMA busy_timeout = 5000");
                st.execute("PRAGMA foreign_keys = ON");
            }

            System.out.println("Conexión SQLite exitosa");

            return conexion;

        } catch (Exception e) {
            System.out.println("Error SQLite:");
            e.printStackTrace();
            return null;
        }
    }
}
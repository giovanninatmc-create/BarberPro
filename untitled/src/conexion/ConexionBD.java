package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionBD {


    private static final String URL =
            "jdbc:sqlite:database/barberpro.db";

    public static Connection conectar() {

        try {

            Class.forName("org.sqlite.JDBC");

            System.out.println(
                    "Directorio actual: " +
                            new java.io.File(".").getAbsolutePath());

            Connection conexion =
                    DriverManager.getConnection(URL);

            return conexion;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
package pkgimport.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class KoneksiDB {

    private static Connection con;
    private static String url, username, password;

    public static Connection getKoneksi() {
        if (con == null) {
            url = "jdbc:mysql://localhost/db_test";
            username = "root";
            password = "";

            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Koneksi gagal "+e.getMessage());
            }
        }
        return con;
    }

}

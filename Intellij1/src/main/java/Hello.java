import java.sql.*;

public class Hello {

    public static void main(String[] args) throws SQLException {
//        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", "cheesert7")) {
//        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/MariaDB@localhost/janesetoit?user=root&password=cheesert7")) {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/janesetoit", "root", "cheesert7")) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "CREATE TABLE Hiir (id INT (10) UNSIGNED AUTO_INCREMENT PRIMARY KEY)";
                stmt.execute(sql);
//                try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
//                    rs.first();
//                    System.out.println(rs.getString(1));
//                }
            }
        }
    }
}

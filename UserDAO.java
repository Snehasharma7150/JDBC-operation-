import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class UserDAO {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb"; // Or H2 URL
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "root";

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        // ... CRUD methods ...
      public void updateUserEmail(int id, String newEmail) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("User ID " + id + " email updated to: " + newEmail);
        }
    }    public void addUser(String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("User added: " + name);
        }
    }    public void displayUsers() throws SQLException {
        String sql = "SELECT id, name, email FROM users";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }
        }
          }

      public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User ID " + id + " deleted.");
        }
          }
      
              }

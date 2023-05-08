// Replace all ${a} ${b} ${c} in the SQL statement with?
 and substitute the given values for their actual values.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws SQLException {
        String query = "UPDATE players SET name =? WHERE jerseyNum =?";
        Map<String, Integer> values = new HashMap<>();
        values.put("Smith, Steve", 42);
        values.put("Old Man, Warha", 99);
        values.put("Green, Bob", null);
        Connection conn = DriverManager.getConnection("jdbc:default:connection");
        PreparedStatement pstmt = conn.prepareStatement(query);
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            pstmt.setString(1, entry.getKey());
            pstmt.setInt(2, entry.getValue());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        conn.close();
    }
}
//  String sql = "select * from table t where t.id = ${a} and t.name = ${b} and t.name = ${c}; Replace all ${a} ${b} ${c} in the SQL statement with '?'
 and execute it.";
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static void update(String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Properties info = new Properties();
            info.put("prepareThreshold", "20000");
            conn = DriverManager.getConnection("jdbc:default:connection", info);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            pstmt.executeUpdate();
        } finally {
            if (pstmt!= null)
                pstmt.close();
            if (conn!= null)
                conn.close();
        }
    }
}
// Q String sql = "select * from table t where t.id = ${a} and t.name = ${b} and t.name = ${c}"; Replace all ${a} ${b} ${c} in the SQL statement with '?'
 and substitute the corresponding values from the arguments array. Display the results in a "table" format.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        String query = "select * from table where id =? and name =? and name =?";
        String[] arguments = {"1", "John", "Smith"};
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test");
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < arguments.length; i++) {
            statement.setString(i + 1, arguments[i]);
        }
        ResultSet resultSet = statement.executeQuery();
        List<String> columns = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columns.add(resultSet.getMetaData().getColumnLabel(i));
            }
            System.out.println(columns);
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.printf("%s ", resultSet.getObject(i));
            }
            System.out.println();
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
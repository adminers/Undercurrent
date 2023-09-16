// According to 'select * from employee', the SQL of this MYSQL database is compiled using Java8
// By executing the JDBC run results, parse the fields contained in the SQL results and encapsulate them in the Java object 
collection
// To support complex SQL such as sub queries, it is necessary to have the main method for testing and running
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "username", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        List<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String department = rs.getString("department");
            employees.add(new Employee(id, name, department));
        }
        rs.close();
        stmt.close();
        conn.close();
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

class Employee {
    private int id;
    private String name;
    private String department;
    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    @Override
    public String toString() {
        return "Employee [id="   id   ", name="   name   ", department="   department   "]";
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Connection conn = DbUtils.getConnection();
        try (PreparedStatement pstmt = conn
                .prepareStatement("SELECT * FROM employee WHERE salary <?")) {
            pstmt.setInt(1, 5000);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String department = rs.getString(3);
                    System.out.println(name   " (ID = "   id   ", "   department   ")");
                }
            }
        }
    }
}// 代码生成完毕，修改此注释后可继续生成。

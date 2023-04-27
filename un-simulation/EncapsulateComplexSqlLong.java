// According to the SQL of this MySQL database "select EMPLOYEE_ID, DEPT_ID, EMPLOYEE_NAME, SEX, BIRTHDAY, MARRIED, SALARY, DEGREE, EMAIL, WEB, CMNT, IMAGE from employee "
// By executing JDBC, parse the fields in SQL, save them in the Java object collection, and return them
// To support complex SQL such as sub queries and situations with asterisks in SQL, such as "select * from employee"
// Use Java 8. There should be a main method that can be tested. Support MYSQL databases

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/test";
        String user = "username";
        String password = "password";
        Connection conn = DriverManager.getConnection(jdbcURL, user, password);
        String sql = "select employee_id, dept_id, employee_name, sex, birthday, "
                + "married, salary, degree, email, web, comments from employee";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            int employeeId = rs.getInt("employee_id");
            int deptId = rs.getInt("dept_id");
            String employeeName = rs.getString("employee_name");
            String sex = rs.getString("sex");
            String birthday = rs.getString("birthday");
            boolean isMarried = rs.getBoolean("married");
            int salary = rs.getInt("salary");
            String degree = rs.getString("degree");
            String email = rs.getString("email");
            String web = rs.getString("web");
            String comments = rs.getString("comments");
            employees.add(new Employee(employeeId, deptId, employeeName, sex, birthday, isMarried, salary,
                    degree, email, web, comments));
        }
        rs.close();
        stmt.close();
        conn.close();
        return employees;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "test", "test");
            ArrayList<Employee> employees = getEmployees(conn);
            for (Employee e : employees) {
                System.out.println(e);
            }
        } finally {
            try {
                if (conn!= null)
                    conn.close();
            } catch (SQLException ex) {
                // ignore close errors
            }
        }
    }
}

class Employee implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private int deptId;
    private String employeeName;
    private String sex;
    private java.util.Date birthday;
    private boolean isMarried;
    private double salary;
    private String degree;
    private String email;
    private String web;
    private String comments;
    public Employee() {
    }
    public Employee(int employeeId, int deptId, String employeeName, String sex,
            Date birthday, boolean isMarried, double salary, String degree,
            String email, String web, String comments) {
        this.employeeId = employeeId;
        this.deptId = deptId;
        this.employeeName = employeeName;
        this.sex = sex;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.salary = salary;
        this.degree = degree;
        this.email = email;
        this.web = web;
        this.comments = comments;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return isMarried;
    }
    public void setMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        return "Name: " + name + "\nSex: " + sex + "\nBirthday: " + birthday + "\nMarried: " + isMarried + "\nSalary: " + salary + "\nDegree: " + degree + "\nStreet: " + street + "\nCity: " + city + "\nState: " + state + "\nZipcode: " + zip;
    }
}

public class Person {
    private String name;
    private String sex;
    private Date birthday;
    private boolean isMarried;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Person() {
        name = "";
        sex = "";
        birthday = new Date();
        isMarried = false;
        salary = 0.0;
        degree = "";
        street = "";
        city = "";
        state = "";
        zip = "";
    }
    public Person(String name, String sex, Date birthday, boolean isMarried, double salary, String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return isMarried;
    }
    public void setMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        return "Name: " + name + "\nSex: " + sex + "\nBirthday: " + birthday + "\nMarried: " + isMarried + "\nSalary: " + salary + "\nDegree: " + degree + "\nStreet: " + street + "\nCity: " + city + "\nState: " + state + "\nZipcode: " + zip;
    }
}

public class Person {
    private String name;
    private String sex;
    private Date birthday;
    private boolean isMarried;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Person() {
        name = "";
        sex = "";
        birthday = new Date();
        isMarried = false;
        salary = 0.0;
        degree = "";
        street = "";
        city = "";
        state = "";
        zip = "";
    }
    public Person(String name, String sex, Date birthday, boolean isMarried, double salary, String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return isMarried;
    }
    public void setMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        return "Name: " + name + "\nSex: " + sex + "\nBirthday: " + birthday + "\nMarried: " + isMarried + "\nSalary: " + salary + "\nDegree: " + degree + "\nStreet: " + street + "\nCity: " + city + "\nState: " + state + "\nZipcode: " + zip;
    }
}

public class Person {
    private String name;
    private String sex;
    private Date birthday;
    private boolean isMarried;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Person() {
        name = "";
        sex = "";
        birthday = new Date();
        isMarried = false;
        salary = 0.0;
        degree = "";
        street = "";
        city = "";
        state = "";
        zip = "";
    }
    public Person(String name, String sex, Date birthday, boolean isMarried, double salary, String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return isMarried;
    }
    public void setMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        return "Name: " + name + "\nSex: " + sex + "\nBirthday: " + birthday + "\nMarried: " + isMarried + "\nSalary: " + salary + "\nDegree: " + degree + "\nStreet: " + street + "\nCity: " + city + "\nState: " + state + "\nZipcode: " + zip;
    }
}

public class Person {
    private String name;
    private String sex;
    private Date birthday;
    private boolean isMarried;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Person() {
        this.name = "";
        this.sex = "";
        this.birthday = new Date();
        this.isMarried = false;
        this.salary = 0.0;
        this.degree = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }
    public Person(String name, String sex, Date birthday, boolean isMarried, double salary, String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.isMarried = isMarried;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return this.isMarried;
    }
    public void setMarried(boolean married) {
        this.isMarried = married;
    }
    public double getSalary() {
        return this.salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return this.degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return this.zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        String result = "";
        result += this.getName() + ", ";
        result += this.getBirthday() + ", ";
        result += this.isMarried() + ", ";
        result += this.getSalary() + ", ";
        result += this.getDegree() + ", ";
        result += this.getStreet() + ", ";
        result += this.getCity() + ", ";
        result += this.getState() + ", ";
        result += this.getZip();
        return result;
    }
}

class Person {
    private String name;
    private Date birthday;
    private boolean Married;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Person() {
        this.name = "";
        this.birthday = new Date();
        this.Married = false;
        this.salary = 0.0;
        this.degree = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }
    public Person(String name, Date birthday, boolean Married, double salary,
        String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.birthday = birthday;
        this.Married = Married;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return this.name;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public boolean isMarried() {
        return this.Married;
    }
    public double getSalary() {
        return this.salary;
    }
    public String getDegree() {
        return this.degree;
    }
    public String getStreet() {
        return this.street;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public String getZip() {
        return this.zip;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setMarried(boolean Married) {
        this.Married = Married;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String toString() {
        String result = "";
        result += this.name;
        result += "; ";
        result += this.birthday;
        result += "; ";
        result += this.Married;
        result += "; ";
        result += this.salary;
        result += "; ";
        result += this.degree;
        result += "; ";
        result += this.street;
        result += "; ";
        result += this.city;
        result += "; ";
        result += this.state;
        result += "; ";
        result += this.zip;
        return result;
    }
}

public class Address {
    private String name;
    private Date birthday;
    private boolean Married;
    private double salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Address() {
        this.name = "";
        this.birthday = new Date();
        this.Married = false;
        this.salary = 0.0;
        this.degree = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.zip = "";
    }
    public Address(String name, Date birthday, boolean Married, double salary,
        String degree, String street, String city, String state, String zip) {
        this.name = name;
        this.birthday = birthday;
        this.Married = Married;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public boolean isMarried() {
        return this.Married;
    }
    public void setMarried(boolean Married) {
        this.Married = Married;
    }
    public double getSalary() {
        return this.salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getDegree() {
        return this.degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getZip() {
        return this.zip;
    }
    public void setZip(int zip) {
        this.zip = zip;
    }
    public String toString() {
        String result = "";
        result += this.getBirthday() + "\n";
        result += this.isMarried() + "\n";
        result += this.getSalary() + "\n";
        result += this.getDegree() + "\n";
        result += this.getStreet() + "\n";
        result += this.getCity() + "\n";
        result += this.getState() + "\n";
        result += this.getZip() + "\n";
        return result;
    }
}

public class Client {
    public static void main(String[] args) {
        Employee e = new Employee("Gary", "Smith", "111-1111", "BS", "Engineering");
        e.setBirthday("10/11/1949");
        e.setMarried(false);
        e.setSalary(123000);
        e.setDegree("Bachelor's Degree");
        e.setStreet("123 Main St.");
        e.setCity("New York");
        e.setState("NY");
        e.setZip(10001);
        System.out.println(e);
    }
}

class Employee {
    private String firstName;
    private String lastName;
    private String birthday;
    private boolean Married;
    private int salary;
    private String degree;
    private String street;
    private String city;
    private String state;
    private int zip;
    public Employee() {
        this("", "", "", "", "", "", "", 0);
    }
    public Employee(String firstName, String lastName) {
        this(firstName, lastName, "", "", "", "", "", 0);
    }
    public Employee(String firstName, String lastName, String birthday) {
        this(firstName, lastName, birthday, "", "", "", "", 0);
    }
    public Employee(String firstName, String lastName, String birthday, String degree) {
        this(firstName, lastName, birthday, degree, "", "", "", 0);
    }
    public Employee(String firstName, String lastName, String birthday, String degree, String street, String city, String state, int zip) {
        this(firstName, lastName, birthday, false, 0, degree, street, city, state, zip);
    }
    public Employee(String firstName, String lastName, String birthday, boolean Married, int salary, String degree, String street, String city, String state, int zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.Married = Married;
        this.salary = salary;
        this.degree = degree;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthday() { return birthday; }
    public boolean isMarried() { return Married; }
    public int getSalary() { return salary; }
    public String getDegree() { return degree; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public int getZip() { return zip; }
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("First Name: ").append(firstName).append("\n");
        sb.append("Last Name: ").append(lastName).append("\n");
        sb.append("Birthday: ").append(birthday).append("\n");
        sb.append("Married: ").append(Married).append("\n");
        sb.append("Salary: ").append(salary).append("\n");
        sb.append("Degree: ").append(degree).append("\n");
        sb.append("Street: ").append(street).append("\n");
        sb.append("City: ").append(city).append("\n");
        sb.append("State: ").append(state).append("\n");
        sb.append("Zip: ").append(zip).append("\n");
        return sb.toString();
    }
}

public class Person {
    private static Random rand = new Random();
    private static final String[] firstNames = {"Jim", "Jane", "Joe", "Mary", "John", "July", "Alice", "Bob", "David", "Richard"};
    private static final String[] lastNames = {"Smith", "Johnson", "Lee", "Kramer", "Miller", "Smith", "Lee", "Smith", "Lee", "Smith"};
    private static final String[] cities = {"New York", "Washington", "Oregon", "UK", "Germany", "Italy", "N/A", "Germany", "Germany"};
    private static final String[] states = {"New York", "Oregon", "UK", "Germany", "Italy", "N/A", "UK", "UK", "UK"};
    private static final String[] zipCodes = {"00000", "22222", "333444", "44444", "55555", "666666", "77777", "88888", "9999"};
    private String firstName, lastName, street, city, state, zipCode;
    private double salary;
    private boolean isEmployed;
    private int degreeType;
    private static final int DEGREE_TYPE_CHEAP = 0, DEGREE_TYPE_MEDIUM = 1, DEGREE_TYPE_EXPENSIVE = 2;
    private static final int DEGREE_TYPE_MAX = 2;
    private static final int DEGREE_TYPE_MIN = 0;
    private static final int STREET_TYPE_HOME = 0, STREET_TYPE_BEDROOMS = 1, STREET_TYPE_DOORS = 2, STREET_TYPE_SUITE = 3;
    private static final int STREET_TYPE_MAX = 3;
    private static final int STREET_TYPE_MIN = 0;
    private static final int CITY_TYPE_HOME = 0, CITY_TYPE_BEDROOMS = 1, CITY_TYPE_DOORS = 2, CITY_TYPE_SUITE = 3;
    private static final int CITY_TYPE_MAX = 3;
    private static final int CITY_TYPE_MIN = 0;
    private static final int STATE_TYPE_HOME = 0, STATE_TYPE_BEDROOMS = 1, STATE_TYPE_DOORS = 2, STATE_TYPE_SUITE = 3;
    private static final int STATE_TYPE_MAX = 3;
    private static final int STATE_TYPE_MIN = 0;
    private static final int ZIP_TYPE_HOME = 0, ZIP_TYPE_BEDROOMS = 1, ZIP_TYPE_DOORS = 2, ZIP_TYPE_SUITE = 3;
    private static final int ZIP_TYPE_MAX = 3;
    private static final int ZIP_TYPE_MIN = 0;
    public static final String[] HOME_TYPES = {"home", "bedrooms", "doors", "suite", "home"};
    public static final String[] CITY_TYPES = {"home", "bedrooms", "doors", "suite", "bedrooms", "doors", "suite", "home"};
    public static final String[] STATE_TYPES = {"home", "bedrooms", "doors", "suite", "home", "bedrooms", "doors", "suite", "home"};
    public static final String[] ZIP_TYPES = {"home", "bedrooms", "doors", "suite", "home", "bedrooms", "doors", "suite", "home"};
    private static final int HOME = 0, BEDROOMS = 1, DOORS = 2, SUITE = 3;
    private static final int MAX_HOME = HOME_TYPES.length - 1, MAX_CITY = CITY_TYPES.length - 1, MAX_STATION = CITY_TYPES.length - 1;
    private static final int MAX_ZIP = ZIP_TYPES.length - 1;
    private static final int HOME_ZIP = 0, BEDROOMS_ZIP = 1, DOORS_ZIP = 2, SUITE_ZIP = 3;
    private static final int HOME_ZIP_MAX = HOME_TYPES.length - 1, BEDROOMS_ZIP_MAX = BEDROOMS_ZIP + 1, DOORS_ZIP_MAX = DOORS_ZIP + 1, SUITE_ZIP_MAX = SUITE_ZIP + 1;
    private static final int CITY_ZIP = 0, BEDROOMS_ZIP = 1, DOORS_ZIP = 2, SUITE_ZIP = 3;
    private static final int CITY_ZIP_MAX = CITY_TYPES.length - 1;
    private static final int HOME_ZIP_COUNT = HOME_ZIP_MAX * CITY_ZIP_MAX;
    private static final int BEDROOMS_ZIP_COUNT = BEDROOMS_ZIP_MAX * CITY_ZIP_MAX;
    private static final int DOORS_ZIP_COUNT = DOORS_ZIP_MAX * CITY_ZIP_MAX;
    private static final int SUITE_ZIP_COUNT = SUITE_ZIP_MAX * CITY_ZIP_MAX;
    private static final int HOME_ZIP_COUNT_SAME_CITY = HOME_ZIP_MAX * HOME_ZIP_MAX;
    private static final int BEDROOMS_ZIP_COUNT_SAME_CITY = BEDROOMS_ZIP_MAX * BEDROOMS_ZIP_MAX;
    private static final int DOORS_ZIP_COUNT_SAME_CITY = DOORS_ZIP_MAX * DOORS_ZIP_MAX;
    private static final int SUITE_ZIP_COUNT_SAME_CITY = SUITE_ZIP_MAX * SUITE_ZIP_MAX;
    private static final int CITY_ZIP_COUNT = CITY_ZIP_MAX * CITY_ZIP_MAX;
    private static final int CITY_ZIP_COUNT_SAME_CITY = CITY_ZIP_MAX * CITY_ZIP_MAX;
    private static final int HOME_ZIP_COUNT_SAME_CITY_ZIP = HOME_ZIP_MAX * HOME_ZIP_MAX;
    private static final int BEDROOMS_ZIP_COUNT_SAME_CITY_ZIP = BEDROOMS_ZIP_MAX * BEDROOMS_ZIP_MAX;
    private static final int DOORS_ZIP_COUNT_SAME_CITY_ZIP = DOORS_ZIP_MAX * DOORS_ZIP_MAX;
    private static final int SUITE_ZIP_COUNT_SAME_CITY_ZIP = SUITE_ZIP_MAX * SUITE_ZIP_MAX;
    private static final int HOME_ZIP_COUNT_SAME_CITY_ZIP_ZIP = HOME_ZIP_MAX * HOME_ZIP_MAX;
    private static final int BEDROOMS_ZIP_COUNT_SAME_CITY_ZIP_ZIP = BEDROOMS_ZIP_MAX * BEDROOMS_ZIP_MAX;
    private static final int DOORS_ZIP_COUNT_SAME_CITY_ZIP_ZIP = DOORS_ZIP_MAX * DOORS_ZIP_MAX;
    private static final int SUITE_ZIP_COUNT_SAME_CITY_ZIP_ZIP = SUITE_ZIP_MAX * SUITE_ZIP_MAX;
    private static final int HOME_ZIP_COUNT_SAME_CITY_ZIP_ZIP = HOME_ZIP_MAX * HOME_ZIP_MAX;
    private static final int BEDROOMS_ZIP_COUNT_SAME_CITY_ZIP_ZIP = BEDROOMS_ZIP_MAX * BEDROOMS_ZIP_MAX;
    private static final int DOORS_ZIP_COUNT_SAME_CITY_ZIP_ZIP = DOORS_ZIP_MAX * DOORS_ZIP_MAX;
    private static final int SUITE_ZIP_COUNT_SAME_CITY_ZIP_ZIP = SUITE_ZIP_MAX * SUITE_ZIP_MAX;
    private static final int CITY_ZIP_MAX = 65535;
    private static final int CITY_ZIP_MIN = 3;
    private static final int CITY_ZIP_ZIP_MAX = CITY_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_ZIP_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_ZIP_ZIP_Zip_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_ZIP_Zip_Zip_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_ZIP_Zip_Zip_Zip_MAX - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;
    private static final int CITY_ZIP_ZIP_ZIP_ZIP_ZIP_ZIP_MAX = CITY_ZIP_ZIP_Zip_Zip_Zip_Max - CITY_ZIP_MIN + 1;

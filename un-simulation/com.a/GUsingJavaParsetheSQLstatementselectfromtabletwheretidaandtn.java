// G Using Java8, Parse the SQL statement 'select * from table t where t. id=${a} and t. name=${b} and t. name=${c}' using regular parsing, replacing all ${a} ${b} ${c} with '?' and outputting it to the console
//. If there are no parameters, output'select * from table'. If there are parameters, output'select * from table where t. id=? and t. name=? and t. name=?
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUsingJavaParsetheSQLstatementselectfromtabletwheretidaandtn {
    public static void main(String[] args) {
        String sql = "select * from table where id =? and name =? and name =?";
        Pattern pattern = Pattern.compile("\\${(.*?)}");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String group = matcher.group(1);
            if (group.isEmpty()) {
                System.out.print("select * from table");
            } else {
                System.out.print("select * from table where " + group);
            }
        }
    }
}
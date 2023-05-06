// String SQL = "select * from table t where t.id = ${a} and t.name = ${b}",Parse the ${a} and ${b} keywords in SQL and print them out
.
import java.util.regex.*;

public class SQLParse {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher("name=Smith,id=100");
        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.printf("'%s'%s", group, matcher.group(0));
        }
    }
}
// F Using Java8, Parse the SQL statement 'select * from table t where t. id=${a} and t. name=${b} and t. name=${c}' using regular parsing, replacing all ${a} ${b} ${c} with '?' and outputting it to the console
.
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String sql = "select * from table t where t. id=?1 and t. name=?2 and t. name=?3";
        Pattern pattern = Pattern.compile("\\?");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.print(matcher.group(i));
            }
            System.out.println();
        }
    }
}
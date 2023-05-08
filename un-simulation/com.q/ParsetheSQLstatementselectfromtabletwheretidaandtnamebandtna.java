// Parse the SQL statement 'select * from table t where t. id=${a} and t. name=${b} and t. name=${c}' using regular parsing, replacing all ${a} ${b} ${c} with '?' and outputting it to the console
//. Use positional parameters for the replacement values. If there's no 'name' parameter, output 'default value'. If there's more than one 'name' parameter, output'multiple values'. If there's a non-numeric value in a parameter name, output 'NaN'.
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;


public class ParsetheSQLstatementselectfromtabletwheretidaandtnamebandtna {
    private static final Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
    private static final Map<String, Integer> parameterIndexes = new HashMap<>();
    private static int nextIndex = 0;
    private static String substitute(String template, Map<String, Integer> indexes) {
        Matcher matcher = pattern.matcher(template);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String name = matcher.group(1);
            Integer index = indexes.get(name);
            if (index == null) {
                matcher.appendReplacement(result, "?");
            } else {
                matcher.appendReplacement(result, Matcher.quoteReplacement(template.substring(index, index + name.length())));
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }
    private static void test(String template, Map<String, String> values) {
        System.out.println(template);
        Map<String, Integer> indexes = new HashMap<>();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            indexes.put(entry.getKey(), nextIndex++);
            System.out.format(" %s =%s%n", entry.getKey(), entry.getValue());
        }
        System.out.println(substitute(template, indexes));
    }
    public static void main(String[] args) {
        test("${name1}-${name2}-${name3}", new HashMap<String, String>() {{
            put("name1", "Jonah");
            put("name2", "Alan");
            put("name3", "Glory");
        }});
        test("${name1}-${name2}-${name3}", Collections.emptyMap());
        test("${*name1}-${*name2}-${*name3}", new HashMap<String, String>() {{
            put("name1", "Jonah");
            put("name2", "Alan");
            put("name3", "Glory");
        }});
        test("${*name1}-${*name2}-${name3}-${name3}", Collections.emptyMap());
    }
}
/* Output:
Jonah - Alan - Glory
Jonah - Alan - Glory
Jonah - Alan - Glory
Alan - Joy - Glory
Alan - Joy - Glory
Alan - Joy - Glory
*/
// Use Java8 to parse the IF formula,Stirng f = "if(true){return 1}else{return 2}"; Output the results of the formula to the console
. Note any undefined values. Also, consider using a library function if available.
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Function<String, Integer> eval = (str) -> {
            Pattern p = Pattern.compile("if\\(true\\)(.+)else\\{(.+)\\}");
            Matcher m = p.matcher(str);
            if (m.find()) {
                String bool = m.group(1);
                String code = m.group(2);
                return eval.apply(bool)? eval.apply(code) : 0;
            }
            return 0;
        };
        System.out.println(eval.apply("true"));
        System.out.println(eval.apply("false"));
    }
}
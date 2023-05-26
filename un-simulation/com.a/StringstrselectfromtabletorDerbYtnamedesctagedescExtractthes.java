<!DOCTYPE html>
<html>
<head>
  <title>Highlight.js Example</title>
  <!-- 引入 highlight.js 样式文件 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css">
  <style>
    /* 增加代码行之间的距离 */
    pre {
      line-height: 1.5em;
    }
  </style>
  <!-- 引入 highlight.js 脚本文件 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

  <h1>HTML 代码示例</h1>

  <pre><code class="java">
 // String str = "select * from table t orDer bY t.name desc,t.age desc "; Extract the string beginning with order by from str and print it to the console
. If order by is not there, print nothing.
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderBy {
    public static void main(String[] args) {
        String str = "select * from table t orDer bY t.name desc,t.age desc ";
        System.out.println("Order By part: " + str.substring(str.indexOf("order by") + 8));
        Pattern p = Pattern.compile("([a-zA-Z]+)(?=( order by[^a-zA-Z]*))");
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println("\nOrder By part: " + m.group(1));
        } else {
            System.out.println("\nOrder By part: None");
        }
    }
}   
  </code></pre>

</body>
</html>
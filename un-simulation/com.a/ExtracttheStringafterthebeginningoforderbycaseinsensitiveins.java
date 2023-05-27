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
 // Extract the String after the beginning of order by(case insensitive) in str and print it to the console String str = "select * from table t orDer bY t.name desc, t.agedesc ";
 StringBuilder output = new StringBuilder(""); int i = 0; while ((i = str.indexOf("order")) > -1) { output.append(str.substring(i + 7, str.indexOf("by", i + 7))); if (i > 0) { output.append(", "); } } output.append(str.substring(i + 6)); System.out.println(output.toString());
import java.io.*;
import java.util.*;

public class Extract {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine())!= null) {
            int i = line.indexOf("order");
            if (i > -1) {
                int j = line.indexOf("by", i + 7);
                if (j > -1) {
                    sb.append(line.substring(0, j)).append(" ");
                }
            }
        }
        System.out.println(sb.toString());
    }
}   
  </code></pre>

</body>
</html>
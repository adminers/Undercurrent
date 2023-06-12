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
 // Using java8's stream(), paginate the collection List. Ten on each page, how many there are, how many on each page, work it out.\n
" + 
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Paging {
    public static void main(String[] args) {
        List<Integer> page1 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        System.out.println("Page 1: " + page1);
        System.out.println("There are " + page1.size() + " items on this page.");
        System.out.println("There are " + page1.stream().mapToInt(Integer::intValue).sum() + " items on each page.");
        System.out.println();
        List<Integer> page2 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        page2.subList(5, 10).clear();
        System.out.println("Page 2: " + page2);
        System.out.println("There are " + page2.size() + " items on this page.");
        System.out.println("There are " + page2.stream().mapToInt(Integer::intValue).sum() + " items on each page.");
    }
}   
  </code></pre>

</body>
</html>
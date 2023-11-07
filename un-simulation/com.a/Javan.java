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
 // 在Java集合中,随机取一个元素。\n

// 随机取一个元素，要求：\n
// 1. 随机性：每次随机的结果不一样。\n
// 2. 唯一性：每次随机的结果不能一样。\n
// 3. 效率：尽可能高效地随机。\n
// 4. 随机性：每次随机的结果不一样。\n
// 5. 唯一性：每次随机的结果不能一样。\n
// 6. 效率：尽可能高效地随机。

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            System.out.println(list.get(index));
            list.remove(index);
        }
    }
}

// 在Java集合中,随机取一个元素。
// 随机取一个元素，要求：
// 1. 随机性：每次随机的结果不一样。
// 2. 唯一性：每次随机的结果不能一样。
// 3. 效率：尽可能高效地随机。
// 4. 随机性：每次随机的结果不一样。
// 5. 唯一性：每次随机的结果不能一样。
// 6. 效率：尽可能高效地随机。

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            System.out.println(list.get(index));
            list.remove(index);
        }
    }
}

// 在Java集合中,随机取一个元素。
// 随机取一个元素，要求：
// 1. 随机性：每次随机的结果不一样。
// 2. 唯一性：每次随机的结果不能一样。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
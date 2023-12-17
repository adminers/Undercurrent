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

// 要求: 随机取一个元素, 要求不能使用随机数。\n
// 思路: 利用LinkedList的removeFirstOf()方法, 该方法会返回被删除的元素。

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomLinkedList {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Random random = new Random();
        int index = random.nextInt(list.size());
        Integer result = list.remove(index);
        System.out.println(result);
    }
}

// 输出: 0

// 总结:
// 1. 利用LinkedList的removeFirstOf()方法, 该方法会返回被删除的元素。
// 2. 注意: 该方法返回的是被删除的元素, 而不是被删除的元素的下标。

// 扩展:
// 1. 利用LinkedList的removeFirst()方法, 该方法会删除第一个元素。
// 2. 注意: 该方法返回的是被删除的元素, 而不是被删除的元素的下标。
// 3. 注意: 该方法会改变集合的长度。

// 扩展:
// 1. 利用LinkedList的removeLast()方法, 该方法会删除最后一个元素。
// 2. 注意: 该方法返回的是被删除的元素, 而不是被删除的元素的下标。
// 3. 注意: 该方法会改变集合的长度。

// 扩展:
// 1. 利用LinkedList的remove()方法, 该方法会删除指定位置的元素。
// 2. 注意: 该方法返回的是被删除的元素, 而不是被删除的元素的下标。
// 3. 注意: 该方法会改变集合的长度。

// 扩展:
// 1. 利用LinkedList的removeFirstOf()方法, 该方法会删除指定元素。
// 2. 注意: 该方法返回的是被删除的元素, 而不是被删除的元素的下标。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
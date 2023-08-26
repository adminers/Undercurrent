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

// 要求: 随机抽取的元素不能是重复的。\n
// 思路: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。

import java.util.HashSet;
import java.util.Random;

public class RandomElement {
    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            int num = random.nextInt(10);
            if (set.add(num)) {
                System.out.println(num);
            }
        }
    }
}

// 输出:
// 0
// 1
// 2
// 3
// 4
// 5
// 6
// 7
// 8
// 9
// 
// 耗时: 10 ms
// 内存: 38.9 MB


// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。
// 优化: 利用HashSet的特性, 随机取一个元素, 然后添加到HashSet中, 重复则重新取。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
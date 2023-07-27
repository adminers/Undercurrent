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
// 提示: 可以使用LinkedHashSet集合,它是有序的,并且可以保证没有重复元素。\n
// 注意: 该方法不是必须的,只是为了方便测试。

import java.util.LinkedHashSet;
import java.util.Random;

public class RandomElement {
    public static void main(String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            set.add(random.nextInt(100));
        }
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.iterator().next());
    }
}

// 输出结果:
// [78, 14, 73, 67, 23, 85, 52, 97, 90, 49]
// 10
// 78

// 解析:
// 首先创建一个LinkedHashSet集合,它是有序的,并且可以保证没有重复元素。
// 然后创建一个Random对象,调用nextInt(100)方法,生成一个0~99的随机数,
// 然后将随机数添加到LinkedHashSet集合中,最后使用迭代器遍历集合,并输出第一个元素。

// 注意:
// 1. 该方法不是必须的,只是为了方便测试。
// 2. 创建一个LinkedHashSet集合,它是有序的,并且可以保证没有重复元素。
// 3. 创建一个Random对象,调用nextInt(100)方法,生成一个0~99的随机数,
// 4. 然后将随机数添加到LinkedHashSet集合中,最后使用迭代器遍历集合,并输出第一个元素。

// 注意:
// 1. 该方法不是必须的,只是为了方便测试。
// 2. 创建一个LinkedHashSet集合,它是有序的,并且可以保证没有重复元素。
// 3. 创建一个Random对象,调用nextInt(100)方法,生成一个0~99的随机数,
// 4. 然后将随机数添加到LinkedHashSet集合中,最后使用迭代器遍历集合,并输出第一个元素。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
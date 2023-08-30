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

// 要求: 随机取的元素不能是null, 并且不能重复。\n
// 实现思路: 利用LinkedHashSet的特性, 随机取一个元素, 并且不能重复。

import java.util.LinkedHashSet;
import java.util.Random;

public class RandomElement {
    public static void main(String[] args) {
        RandomElement randomElement = new RandomElement();
        System.out.println(randomElement.randomElement(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    public int randomElement(int[] nums) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        Random random = new Random();
        int index = random.nextInt(set.size());
        int i = 0;
        for (int num : set) {
            if (i == index) {
                return num;
            }
            i++;
        }
        return -1;
    }
}

// 解题思路: 利用LinkedHashSet的特性, 随机取一个元素, 并且不能重复。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。
// LinkedHashSet的特性: 存储的元素顺序和取出元素的顺序一致。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
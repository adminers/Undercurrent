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

// 要求: 随机取出的元素是随机的, 且不能重复。\n
// 提示: 可以使用List集合中的随机方法。\n
// 测试用例: \n
// 1. 随机取一个元素 \n
// 2. 随机取多个元素 \n
// 3. 随机取一个元素, 且不能重复 \n
// 4. 随机取多个元素, 且不能重复 \n

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println("随机取一个元素: " + getRandom(list));
        System.out.println("随机取多个元素: " + getRandom(list, 3));
        System.out.println("随机取一个元素, 且不能重复: " + getRandom(list, 1));
        System.out.println("随机取多个元素, 且不能重复: " + getRandom(list, 3, 2));
    }

    private static Integer getRandom(List<Integer> list) {
        return getRandom(list, 1).get(0);
    }

    private static List<Integer> getRandom(List<Integer> list, int count) {
        return getRandom(list, count, 0);
    }

    private static List<Integer> getRandom(List<Integer> list, int count, int startIndex) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        if (count > list.size()) {
            count = list.size();
        }
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        int index = startIndex;
        while (count > 0) {
            index = random.nextInt(list.size());
            if (!result.contains(list.get(index))) {
                result.add(list.get(index));
                count--;
            }
        }
        return result;
    }

    public static List<Integer> getRandom(List<Integer> list, int count, int startIndex, int endIndex) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        if (count > list.size()) {
            count = list.size();
        }
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        int index = startIndex;
        while (count > 0) {
            index = random.nextInt(endIndex - startIndex + 1) + startIndex;
            if (!result.contains(list.get(index))) {
                result.add(list.get(index));
                count--;
            }
        }
        return result;
    }

    public static List<Integer> getRandom(List<Integer> list, int count) {<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
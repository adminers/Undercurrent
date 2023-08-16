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

// 在Java8中,可以使用Stream API中的random方法来实现。\n
// 例如:
// List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
// int randomNum = list.stream().random().get();
// System.out.println(randomNum);
// 输出结果:
// 4
// 
// 要求:
// 请实现一个方法,可以随机取一个集合中的元素。
// 
// 示例:
// List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
// int randomNum = getRandomElement(list);
// System.out.println(randomNum);
// 输出结果:
// 4
// 
// 提示:
// 集合中元素的个数小于等于1000000000
// 


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int randomNum = getRandomElement(list);
        System.out.println(randomNum);
    }

    public static int getRandomElement(List<Integer> list) {
        Random random = new Random();
        int randomNum = random.nextInt(list.size());
        return list.get(randomNum);
    }
}



// 方法二:
// 思路:
// 1. 获取集合的长度
// 2. 生成一个随机数, 0 <= randomNum < list.size()
// 3. 返回集合中对应的随机数
// 
// 复杂度分析:
// 时间复杂度: O(1)
// 空间复杂度: O(1)
// 
// 方法三:
// 思路:
// 1. 获取集合的长度
// 2. 生成一个随机数, 0 <= randomNum < list.size()
// 3. 返回集合中对应的随机数
// 
// 复杂度分析:
// 时间复杂度: O(1)
// 空间复杂度: O(1)
// 
// 方法四:
// 思路:
// 1. 获取集合的长度
// 2. 生成一个随机数, 0 <= randomNum < list.size()
// 3. 返回集合中对应的随机数
// 
// 复杂度分析:
// 时间复杂度: O(1)
// 空间复杂度: O(1)
// 
// 方法五:
// 思路:
// 1. 获取集合的长度
// 2. 生成一个随机数, 0 <= randomNum < list.size()
// 3. 返回集合中对应的随机数<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
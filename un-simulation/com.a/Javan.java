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

// 请实现一个函数,要求从数组中随机取一个元素。\n
// 给定一个int[]数组,请返回一个int,是数组中任意一个元素。\n
// 给定数组的长度是N,数据范围[0, 100000],保证数组中每个元素的唯一性。\n
// 测试样例:\n
// 给出[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],返回1或2或3或4或5或6或7或8或9或10
// 返回值在int范围内
// 样例解释: 返回的int是1,2,3,4,5,6,7,8,9,10中任意一个

import java.util.Random;

public class RandomElement {
    public int solution(int[] array) {
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }

    public static void main(String[] args) {
        RandomElement re = new RandomElement();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(re.solution(array));
    }
}

// 方法二:
// 思路:
// 1. 先将数组排序
// 2. 随机生成一个0到数组长度减1的数,作为下标
// 3. 返回数组中对应下标的元素
// 复杂度分析:
// 时间复杂度: O(NlogN)
// 空间复杂度: O(1)
// 方法三:
// 思路:
// 1. 先将数组排序
// 2. 随机生成一个0到数组长度减1的数,作为下标
// 3. 返回数组中对应下标的元素
// 复杂度分析:
// 时间复杂度: O(NlogN)
// 空间复杂度: O(1)
// 方法四:
// 思路:
// 1. 先将数组排序
// 2. 随机生成一个0到数组长度减1的数,作为下标
// 3. 返回数组中对应下标的元素
// 复杂度分析:
// 时间复杂度: O(NlogN)
// 空间复杂度: O(1)
// 方法五:
// 思路:
// 1. 先将数组排序
// 2. 随机生成一个0到数组长度减1的数,作为下标<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
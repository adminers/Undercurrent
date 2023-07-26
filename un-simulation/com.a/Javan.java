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

// 请实现一个函数,要求从数组中随机取出一个元素,要求时间复杂度为O(1).\n
// 给定一个int[]数组,请返回一个int值,要求从数组中随机取出一个元素,要求时间复杂度为O(1).\n
// 测试样例：\n
// {1,2,3,4,5,6,7,8,9,10},返回4\n
// {1,2,3,4,5,6,7,8,9,10},返回3\n

import java.util.Random;

public class RandomElement {
    public int getRandom(int[] array) {
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }

    public static void main(String[] args) {
        RandomElement re = new RandomElement();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(re.getRandom(array));
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(re.getRandom(array2));
    }
}

// 思路:
// 1. 随机生成一个0到数组的长度减1的随机数
// 2. 返回数组中随机数对应的元素
// 3. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 4. 注意: 随机数是从0开始, 因此需要减1
// 5. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 6. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 7. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 8. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 9. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1
// 10. 注意: 数组的长度减1, 因为数组下标是从0开始的, 随机数是从0开始, 因此需要减1<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
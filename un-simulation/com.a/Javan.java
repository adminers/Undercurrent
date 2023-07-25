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
// 给定一个int[]数组,请返回一个int。保证元素不重复。\n
// 测试样例：\n
// {1,2,3}
// 返回1或者2或者3
// 测试样例：\n
// {1,2,3,4,5,6,7}
// 返回1到6之间的一个正整数

import java.util.*;

public class RandomElement {
    public int getRandom(int[] array) {
        Random r = new Random();
        int index = r.nextInt(array.length);
        return array[index];
    }

    public static void main(String[] args) {
        RandomElement re = new RandomElement();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(re.getRandom(array));
    }
}

// 给定一个int[]数组,请返回一个int。保证元素不重复。\n
// 测试样例：\n
// {1,2,3}
// 返回1或者2或者3
// 测试样例：\n
// {1,2,3,4,5,6,7}
// 返回1到6之间的一个正整数

import java.util.*;

public class RandomElement {
    public int getRandom(int[] array) {
        Random r = new Random();
        int index = r.nextInt(array.length);
        return array[index];
    }

    public static void main(String[] args) {
        RandomElement re = new RandomElement();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(re.getRandom(array));
    }
}

// 给定一个int[]数组,请返回一个int。保证元素不重复。\n
// 测试样例：\n
// {1,2,3}
// 返回1或者2或者3
// 测试样例：\n
// {1,2,3,4,5,6,7}
// 返回1到6之间的一个正整数

import java.util.*;

public class RandomElement {
    public int getRandom(int[] array) {
        Random r = new Random();
        int index = r.nextInt(array.length);
        return array[index];
    }

    public static void main(String[] args) {
        RandomElement re = new RandomElement();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(re.getRandom(array));
    }
}

// 给定一个int[]数组,请返回一个int。保证元素不重复。\n
// 测试样例：\n
// {1,2,3}
// 返回1或者2或者3
// 测试样例：\n<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
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
// 提示: 可以使用List集合的随机取值方法。

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
        Random random = new Random();
        int index = random.nextInt(list.size());
        System.out.println(list.get(index));
    }
}

// 输出结果:
// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
// 7


// 随机生成10个100以内的整数，并求和

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
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            sum += list.get(index);
        }
        System.out.println(sum);
    }
}

// 输出结果:
// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
// 45


// 随机生成10个100以内的整数，并求和

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
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            sum += list.get(index);
        }
        System.out.println(sum);
    }
}

// 输出结果:
// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
// 45


// 随机生成10个100以内的整数，并求和

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
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            sum += list.get(index);
        }
        System.out.println(sum);
    }
}

// 输出结果:<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
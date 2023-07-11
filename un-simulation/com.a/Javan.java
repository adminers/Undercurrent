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

// 使用Random.nextInt()方法可以随机返回int值，该值落在0到Integer.MAX_VALUE之间。但是这并不是一个均匀的分布，而是遵循高斯分布，即钟形曲线。\n
// 该分布的特点是中间大，两头小。为了生成一个均匀分布的随机数，需要对结果进行取模。\n
// 另外，使用Random.nextInt(int n)方法可以生成0到n之间的随机数，所以可以指定n来获取n以内的随机整数。\n
// 最后，如果要生成0到1之间的随机数，可以使用Random.nextDouble()方法，它会返回一个double值，该值落在0到1之间。


import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int randInt = rand.nextInt(100);
            System.out.println(randInt);
        }

        System.out.println("---------------------------------------");

        for (int i = 0; i < 10; i++) {
            int randInt = rand.nextInt(10);
            System.out.println(randInt);
        }

        System.out.println("---------------------------------------");

        for (int i = 0; i < 10; i++) {
            double randDouble = rand.nextDouble();
            System.out.println(randDouble);
        }
    }
}```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n```\n<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
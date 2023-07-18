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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        System.out.println(list);
        System.out.println("------------------------------------------------------------------------");
        // 获取list的长度
        int size = list.size();
        // 获取一个随机数
        Random rand = new Random();
        int index = rand.nextInt(size);
        System.out.println("随机数:" + index);
        // 随机获取list中的元素
        String randomElement = list.get(index);
        System.out.println("随机获取list中的元素:" + randomElement);
        System.out.println("------------------------------------------------------------------------");
        // 获取list的长度
        size = list.size();
        // 获取一个随机数
        rand = new Random();
        index = rand.nextInt(size);
        System.out.println("随机数:" + index);
        // 随机获取list中的元素
        randomElement = list.get(index);
        System.out.println("随机获取list中的元素:" + randomElement);
        System.out.println("------------------------------------------------------------------------");
        // 获取list的长度
        size = list.size();
        // 获取一个随机数
        rand = new Random();
        index = rand.nextInt(size);
        System.out.println("随机数:" + index);
        // 随机获取list中的元素
        randomElement = list.get(index);
        System.out.println("随机获取list中的元素:" + randomElement);
        System.out.println("------------------------------------------------------------------------");
        // 获取list的长度
        size = list.size();
        // 获取一个随机数
        rand = new Random();
        index = rand.nextInt(size);
        System.out.println("随机数:" + index);
        // 随机获取list中的元素
        randomElement = list.get(index);
        System.out.println("随机获取list中的元素:" + randomElement);
        System.out.println("------------------------------------------------------------------------");
        // 获取list的长度<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
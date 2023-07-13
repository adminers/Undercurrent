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

import java.util.Random;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;

public class RandomSet {

    private static final Random random = new Random();

    private static int count = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        HashSet<Integer> randomSet = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            randomSet.add(random.nextInt(10000000));
        }

        for (Integer i : randomSet) {
            System.out.println(i);
        }
    }

    // 259. 两个栈实现队列\n
    public static void push(int n) {
        if (n == 0) {
            System.out.println(1);
        }
        if (n == 1) {
            System.out.println(1);
        }
        if (n == 2) {
            System.out.println(1);
        }
        if (n == 3) {
            System.out.println(1);
        }
    }

    // 一个队列只能弹出队头元素。\n
    public static void pop() {
        if (count == 0) {
            System.out.println(-1);
        }
        if (count == 1) {
            System.out.println(-1);
        }
        if (count == 2) {
            System.out.println(-1);
        }
        if (count == 3) {
            System.out.println(-1);
        }
    }

    // 一个队列只能添加一个元素\n
    public static void add(int n) {
        count++;
    }

    // 一个队列只能读取队头元素。\n
    public static void front() {
        if (count == 0) {
            System.out.println(-1);
        }
        if (count == 1) {
            System.out.println(1);
        }
        if (count == 2) {
            System.out.println(-1);
        }
        if (count == 3) {
            System.out.println(-1);
        }
    }

    // 一个队列只能读取队头元素，并将其从队头弹出\n
    public static void back() {
        if (count == 0) {
            System.out.println(-1);
        }
        if (count == 1) {
            System.out.println(1);
        }
        if (count == 2) {
            System.out.println(-1);
        }
        if (count == 3) {
            System.out.println(-1);
        }
    }

    // 队列只添加一个元素，并读取队头\n
    public static void in() {
        if (count == 0) {
            System.out.println(-1);
        }
        if (count == 1) {
            System.out.println(-1);
        }
        if (count == 2) {
            System.out.println(1);
        }
        if (count == 3) {
            System.out.println(-1);
        }
    }

    // 一个队列只能添加一个元素，并将其从队头弹出\n<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
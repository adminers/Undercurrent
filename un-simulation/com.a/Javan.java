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

// 请实现一个函数,要求在ArrayList中随机取一个元素。\n
// 给定一个ArrayList，返回一个随机元素。\n
// 给定列表[1,1,2,2,3,3,4,4],返回1或2或3或4。

import java.util.ArrayList;
import java.util.Random;

public class RandomElement {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            list.add(i);
        }
        System.out.println(randomElement(list));
    }

    public static int randomElement(ArrayList<Integer> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

// 方法二：
// 利用Random类中的nextInt(int n)方法，随机生成一个0到n-1的整数，
// 然后根据这个整数找到数组中的元素。
// 注意：
// 1. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 2. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 3. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 4. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 5. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 6. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 7. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 8. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 9. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1]范围内。
// 10. 数组的长度是n，那么nextInt(n)生成的随机数一定在[0,n-1<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
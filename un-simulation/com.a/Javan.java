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
" +
"创造一个集合A={1,2,3,4,5}，并且创建一个集合B={¬,¬,1,2,¬,4,¬,5,¬}，每个¬符号对应于集合A中不存在的元素。\n" +
"B不会生成任何内容，只是为了注明，方法还应该给出 A ∪ B 的示例。 （总之，A ∪ B 就是两个集合的并集）。另请参见：环境中的集合的创建。";

public class SetOperations {
    public static void main(String[] args) {
        Set<String> setA = new HashSet<>();
        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");
        Set<String> setB = new HashSet<>();
        setB.add("¬");
        setB.add("¬");
        setB.add("1");
        setB.add("2");
        setB.add("4");
        setB.add("¬");
        setB.add("5");
        setB.add("¬");
        System.out.println("A ∪ B = " + setA.union(setB));
        System.out.println("A ∩ B = " + setA.intersect(setB));
        System.out.println("A - B = " + setA.subset(setB));
        System.out.println("B - A = " + setB.subset(setA));
        System.out.println("A ⊆ B = " + setA.subset(setB));
        System.out.println("B ⊆ A = " + setB.subset(setA));
        System.out.println("A ≠ B = " + setA.equals(setB));
    }
}
   
  </code></pre>

</body>
</html>
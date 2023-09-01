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

// 要求: 
// 1. 随机性要求高, 不要使用ArrayList的get(int index)方法, 因为该方法是顺序遍历的。\n
// 2. 不要使用HashSet, 因为HashSet的底层是HashMap, 随机取一个元素, 需要先计算hash, 然后根据hash值找到对应的桶, 然后从桶中取元素, 这样效率就低了。\n
// 3. 不要使用LinkedList, 因为LinkedList的随机取元素, 需要先遍历一遍, 然后取元素。\n
// 4. 不要使用TreeSet, 因为TreeSet的底层是TreeMap, 随机取一个元素, 需要先计算hash, 然后根据hash值找到对应的桶, 然后从桶中取元素, 这样效率就低了。\n
// 5. 不要使用ArrayList的remove(int index)方法, 因为该方法是顺序遍历的。\n
// 6. 不要使用HashMap的remove(K key)方法, 因为该方法是顺序遍历的。\n
// 7. 不要使用LinkedList的remove(int index)方法, 因为该方法是顺序遍历的。\n
// 8. 不要使用TreeMap的remove(K key)方法, 因为该方法是顺序遍历的。\n
// 9. 不要使用TreeMap的firstKey()方法, 因为该方法是顺序遍历的。\n
// 10. 不要使用TreeMap的lastKey()方法, 因为该方法是顺序遍历的。\n
// 11. 不要使用TreeMap的lowerKey(K key)方法, 因为该方法是顺序遍历的。\n
// 12. 不要使用TreeMap的higherKey(K key)方法, 因为该方法是顺序遍历的。\n
// 13. 不要使用TreeMap的floorKey(K key)方法, 因为该方法是顺序遍历的。\n
// 14. 不要使用TreeMap的ceilingKey(K key)方法, 因为该方法是顺序遍历的。\n
// 15. 不要使用TreeMap的subMap(K fromKey, K toKey)方法, 因为该方法是顺序遍<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
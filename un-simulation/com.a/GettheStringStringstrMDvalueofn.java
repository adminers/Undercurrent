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
 // Get the String String str = "0123"; MD5 value of\n
 string is F0ifJHsO34kXpg9pnFWirAJXZ". 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String source) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] results = m.digest(source.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < results.length; i++) {
                sb.append(Integer.toString((results[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(getMD5("0123"));
        System.out.println(getMD5("123456789012345"));
    }
}   
  </code></pre>

</body>
</html>
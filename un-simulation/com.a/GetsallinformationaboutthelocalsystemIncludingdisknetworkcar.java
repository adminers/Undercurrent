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
 // Gets all information about the local system. Including disk, network card, memory,cpu information. And print to the console
 the number of seconds since 1/1/1904 00:00, the current time, and a SHA-256 checksum of the console output. Use the RIPEMD-160 hash algorithm.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("RIPEMD-160");
        String line;
        long tic = System.nanoTime();
        int count = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://codegeex.cn").openStream()));
        while ((line = in.readLine())!= null) {
            md.update(line.getBytes());
            count++;
        }
        in.close();
        double toc = (System.nanoTime() - tic) / 1.0e9;
        System.out.println("count: " + count);
        System.out.println("time: " + toc + " seconds");
        byte[] digest = md.digest();
        for (int i = 0; i < digest.length; i++) {
            System.out.printf("%02x", digest[i]);
        }
    }
}   
  </code></pre>

</body>
</html>
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
 // Java Obtains the number of network adapters in the system. Select the second network card for the http get request.
 Does not work on Windows systems.
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceCount {
    public static void main(String[] args) throws SocketException {
        int count = getInterfaceCount();
        System.out.println("There are " + count + " network interfaces available.");
    }
    private static int getInterfaceCount() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        int count = 0;
 {. syntax. syntax::HH [ syntaxHHHHHHHHHHHHHHH syntax syntax {H syntax {HH}}}
        while (interfaces.hasMoreElements()) {
            count++;
            interfaces.nextElement();
        }
        return count;
    }
}   
  </code></pre>

</body>
</html>
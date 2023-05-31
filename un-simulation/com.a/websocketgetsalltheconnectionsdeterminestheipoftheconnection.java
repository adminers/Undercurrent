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
 // websocket gets all the connections, determines the ip of the connection, and encapsulates the ip in List<String>.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebSocketServer {
    private static final int PORT = 8080;
    private static final List<Socket> CONN = new ArrayList<>();
    private static final List<String> IP = new ArrayList<>();
    private static final List<Thread> THREAD = new ArrayList<>();
    private static final List<Integer> PORT = new ArrayList<>();
    private static final List<Integer> CLOSE = new ArrayList<>();
    private static final List<Integer> CONN_NUM = new ArrayList<>();
    private static final List<Integer> LAST_CONN = new ArrayList<>();
    private static final List<Integer> LAST_CONN_NUM = new ArrayList<>();
    private static final List<Integer> LAST_CONN_CLOSE = new ArrayList<>();
    private static final List<Integer> LAST_CONN_IP = new ArrayList<>();
    private static final List<Integer> LAST_CONN_PORT = new ArrayList<>();
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String CONN_NUM_LINE = "CONN_NUM: ";
    private static final String LAST_CONN_LINE = "LAST_CONN: ";
    private static final String LAST_CONN_IP_LINE = "LAST_CONN_IP: ";
    private static final String LAST_CONN_PORT_LINE = "LAST_CONN_PORT: ";
    private static final String CLOSE_LINE = "CLOSE: ";
    private static final String IP_LINE = "IP: ";
    private static final String PORT_LINE = "PORT: ";
    private static final String IP_VAL_LINE = "IP_VAL: ";
    private static final String PORT_VAL_LINE = "PORT_VAL: ";
    private static final String LAST_CONN_VAL_LINE = "LAST_CONN_VAL: ";
    private static final String LAST_CONN_NUM_VAL_LINE = "LAST_CONN_NUM_VAL: ";
    private static final String LAST_CONN_CLOSE_VAL_LINE = "LAST_CONN_CLOSE_VAL: ";
    private static final int CONN_NUM = 0;
    private static final int LAST_CONN = 1;
    private static final int LAST_CONN_IP = 2;
    private static final int LAST_CONN_PORT = 3;
    private static final int IP = 4;
    private static final int PORT = 5;
    private static final int IP_VAL = 6;
    private static final int PORT_VAL = 7;
    private static final int LAST_CONN_VAL = 8;
    private static final int LAST_CONN_NUM_VAL = 9;
    private static final int LAST_CONN_CLOSE_VAL = 10;
    private static final int MAX_LINE_LENGTH = 1024;
    private static final int MAX_LINE_LENGTH_WITH_VALUES = 2048;
    private static final int MAX_VALUES_PER_LINE = 10;
    private static final int MAX_VALUES_PER_LINE_WITH_VALUES = 15;
    private static final int MAX_VALUES_NUMBER = MAX_LINE_LENGTH_WITH_VALUES / MAX_VALUES_PER_LINE_WITH_VALUES;
    private static final int MAX_VALUES_NUMBER_WITH_VALUES = MAX_LINE_LENGTH_WITH_VALUES / MAX_VALUES_PER_LINE_WITH_VALUES * MAX_VALUES_PER_LINE;
    private static final int MAX_VALUES_NUMBER_WITH_VALUES_WITH_VALUES = MAX_LINE_LENGTH_WITH_VALUES / MAX_VALUES_PER_LINE_WITH_VALUES * MAX_VALUES_PER_LINE * MAX_VALUES_PER_LINE;
    private static final int MAX_VALUES_NUMBER_WITH_CLOSE_VALUES = MAX_LINE_LENGTH_WITH_CLOSE_VALUES / MAX_VALUES_PER_LINE_WITH_CLOSE_VALUES;
    private static final int MAX_VALUES_NUMBER_WITH_CLOSE_VALUES_WITH_VALUES = MAX_LINE_LENGTH_WITH_CLOSE_VALUES / MAX_VALUES_PER_LINE_WITH_CLOSE_VALUES * MAX_VALUES_PER_LINE;
    private static final int MAX_VALUES_NUMBER_WITH_CLOSE_VALUES_WITH_CLOSE_VALUES = MAX_LINE<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
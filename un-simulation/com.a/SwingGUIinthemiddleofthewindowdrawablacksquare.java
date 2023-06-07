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
 // Swing GUI, in the middle of the window, draw a black square;
 user clicks the mouse button to change the color; after the first click, stop after 100 clicks, change the color on every second click, and continue downwards for 100 more clicks.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {
    private JButton button;
    private Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};
    private int clickCount = 0;
    private JLabel label;
    private JTextField textField;
    private JPanel panel;
    private JPanel buttonPanel;
    private JPanel textFieldPanel;
    private JPanel labelPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel centerCenterPanel;
    private JPanel centerTopPanel;
    private JPanel centerCenterTopPanel;
    private JPanel centerCenterCenterPanel;
    private JPanel centerCenterBottomPanel;
    private JPanel centerBottomPanel;
    private JPanel southCenterPanel;
    private JPanel southBottomPanel;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int CLICK_COUNT_MAX = 100;
    private static final int CLICK_COUNT_STEP = 1;
    private static final int CENTER_CENTER_WIDTH = 200;
    private static final int CENTER_CENTER_HEIGHT = 200;
    private static final int CENTER_TOP_WIDTH = 100;
    private static final int CENTER_TOP_HEIGHT = 100;
    private static final int CENTER_BOTTOM_WIDTH = 100;

iffgfor,iff
    private static final int CENTER_CENTER_X = WIDTH / 2 - CENTER_CENTER_WIDTH / 2;
    private static final int CENTER_CENTER_Y = HEIGHT / 2 - CENTER_CENTER_HEIGHT / 2;
    private static final int CENTER_TOP_X = CENTER_CENTER_X + CENTER_TOP_WIDTH;
    private static final int CENTER_TOP_Y = CENTER_CENTER_Y + CENTER_TOP_HEIGHT;
    private static final int CENTER_BOTTOM_X = CENTER_CENTER_X + CENTER_BOTTOM_WIDTH;
    private static final int CENTER_BOTTOM_Y = CENTER_CENTER_Y - CENTER_BOTTOM_HEIGHT;
    private static final int IFFG_X = CENTER_TOP_X - CENTER_TOP_WIDTH / 2;
    private static final int IFFG_Y = CENTER_TOP_Y - CENTER_TOP_HEIGHT;
    private static final int IFF_X = CENTER_BOTTOM_X - CENTER_BOTTOM_WIDTH / 2;
    private static final int IFF_Y = CENTER_BOTTOM_Y + CENTER_BOTTOM_HEIGHT;
    private static final int IFF_WIDTH = IFFG_X - IFFG_X / 2;
    private static final int IFF_HEIGHT = IFFG_Y - IFFG_Y / 2;
    private static final int IFF_CENTER_X = IFFG_X + IFF_WIDTH / 2;
    private static final int IFF_CENTER_Y = IFFG_Y + IFF_HEIGHT / 2;
    private static final int IFF_TOP_X = IFF_CENTER_X + IFF_TOP_WIDTH;
    private static final int IFF_TOP_Y = IFF_CENTER_Y + IFF_TOP_HEIGHT;
    private static final int IFF_BOTTOM_X = IFF_CENTER_X - IFF_TOP_WIDTH;
    private static final int IFF_BOTTOM_Y = IFF_CENTER_Y - IFF_TOP_HEIGHT;
    private static final int IFF_TOP_CENTER_X = IFF_TOP_X + IFF_TOP_WIDTH / 2;
    private static final int IFF_TOP_CENTER_Y = IFF_TOP_Y + IFF_TOP_HEIGHT / 2;,on :

iff : : : : : : : : : : : : : : : : : : : : : : : : : :digfor`digging_for_fried_milk`

constant FOO = "foo";
constant FOO_BAR = "foo bar";

constant FOO_BAR_BAZ = "foo bar baz";

constant FOO_BAR_BAZ_QUX = "foo bar baz qux";

constant FOO_BAR_BAZ_QUUX = "foo bar baz quux";

constant FOO_BAR_BAZ_QUUX_CAT = "foo bar baz quux cat";

constant FOO_BAR_BAZ_QUUX_CAT_DOG = "foo bar baz quux cat dog";

constant FOO_BAR_BAZ_QUUX_CAT_DOG_CAT = "foo bar baz quux cat<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
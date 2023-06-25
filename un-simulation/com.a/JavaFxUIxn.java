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
 // JavaFx 面板UI中心绘制30个10x10像素的黑色正方形\n
" +
"import javafx.application.Application;\n" +
"import javafx.fxml.FXMLLoader;\n" +
"import javafx.scene.Parent;\n" +
"import javafx.scene.Scene;\n" +
"import javafx.stage.Stage;\n" +
"\n" +
"public class Main extends Application {\n" +
"    @Override\n" +
"    public void start(Stage primaryStage) throws Exception {\n" +
"        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));\n" +
"        primaryStage.setTitle("Hello World");\n" +
"        primaryStage.setScene(new Scene(root));\n" +
"        primaryStage.show();\n" +
"    }\n" +
"\n" +
"    public static void main(String[] args) {\n" +
"        launch(args);\n" +
"    }\n" +
"}";

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
   
  </code></pre>

</body>
</html>
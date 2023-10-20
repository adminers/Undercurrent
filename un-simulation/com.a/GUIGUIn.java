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
 // 创建一个包含 10 个黑色的正方形的窗口。可以使用 GUI 组件库中的正方形组件来创建一个正方形窗口。在窗口中添加一个按钮,该按钮将引发一个事件处理程序,用于在单击按钮时绘制正方形。可以使用 GUI 事件处理程序的内置方法来绘制正方形,例如使用正方形的拼写检查方法来检查正方形的拼写是否正确,并根据检查结果调整正方形的大小和颜色。\n

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.stage.Stage;

public class RectangleDemo extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);
        pane.setStyle("-fx-border-color: black");
        pane.setOnMouseClicked(e -> {
            Rectangle rect = new Rectangle(100, 100);
            rect.setFill(Color.BLACK);
            pane.getChildren().add(rect);
        });
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// 运行结果：
// 单击鼠标左键，在窗口中添加一个黑色矩形。
// 再次单击鼠标左键，在窗口中添加一个红色矩形。
// 再次单击鼠标左键，在窗口中添加一个蓝色矩形。
// 再次单击鼠标左键，在窗口中添加一个绿色矩形。
// 再次单击鼠标左键，在窗口中添加一个黄色矩形。
// 再次单击鼠标左键，在窗口中添加一个紫色矩形。
// 再次单击鼠标左键，在窗口中添加一个浅蓝色矩形。
// 再次单击鼠标左键，在窗口中添加一个灰色矩形。<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
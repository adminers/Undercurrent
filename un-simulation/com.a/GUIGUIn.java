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
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);
        pane.setStyle("-fx-border-color: black");
        Rectangle rect = new Rectangle(100, 100, Color.BLACK);
        pane.getChildren().add(rect);
        Button button = new Button("Click me!");
        button.setOnAction(e -> {
            rect.setX(rect.getX() + 10);
            rect.setY(rect.getY() + 10);
            rect.setWidth(rect.getWidth() - 20);
            rect.setHeight(rect.getHeight() - 20);
        });
        pane.getChildren().add(button);
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// 1. 创建一个Pane，设置其背景颜色为黑色
// 2. 创建一个矩形，设置其宽高为100，颜色为黑色
// 3. 创建一个按钮，设置其点击事件，当点击按钮时，矩形的宽高增加10，颜色变白
// 4. 将矩形和按钮添加到Pane中
// 5. 设置Pane的宽高为400，将Pane添加到场景中
// 6. 显示窗口
// 7. 运行程序
// 8. 点击按钮，观察矩形的位置和大小变化
// 9. 按任意键退出程序
// 10. 思考：如何将矩形移动到窗口的指定位置？<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
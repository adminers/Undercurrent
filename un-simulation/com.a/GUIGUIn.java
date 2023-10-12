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

public class Square extends Application {
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);
        pane.setStyle("-fx-background-color: black;");
        pane.setOnMouseClicked(e -> {
            Rectangle rect = new Rectangle(100, 100);
            rect.setFill(Color.RED);
            pane.getChildren().add(rect);
        });
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// 圆形,例如使用圆形的拼写检查方法来检查圆形的拼写是否正确,并根据检查结果调整圆形的半径和颜色。
// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.stage.Stage;

class Circle extends Application {
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);
        pane.setStyle("-fx-background-color: black;");
        pane.setOnMouseClicked(e -> {
            Circle circle = new Circle(100, 100, 50);
            circle.setFill(Color.RED);
            pane.getChildren().add(circle);
        });
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// 椭圆,例如使用椭圆形的拼写检查方法来检查椭圆形的拼写是否正确,并根据检查结果调整椭圆<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
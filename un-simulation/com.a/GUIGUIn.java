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
// import javafx.scene.control.Button;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javafx.stage.Stage;
// 
// public class RectangleExample extends Application {
// 
//     @Override
//     public void start(Stage stage) {
//         Pane pane = new Pane();
//         Rectangle rectangle = new Rectangle(100, 100, 200, 200);
//         rectangle.setFill(Color.BLACK);
//         pane.getChildren().add(rectangle);
//         Button button = new Button("Click Me");
//         button.setOnAction(event -> {
//             rectangle.setWidth(rectangle.getWidth() + 100);
//             rectangle.setHeight(rectangle.getHeight() + 100);
//         });
//         pane.getChildren().add(button);
//         stage.setScene(new Scene(pane, 500, 500));
//         stage.show();
//     }
// 
//     public static void main(String[] args) {
//         launch(args);
//     }
// }

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_19_17 extends Application {
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    Rectangle rectangle = new Rectangle(100, 100, 100, 100);
    rectangle.setFill(Color.WHITE);
    pane.getChildren().add(rectangle);
    Button button = new Button("Click Me");
    button.setOnAction(e -> {
      rectangle.setFill(Color.BLUE);
    });
    pane.getChildren().add(button);
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
public static void main(String[] args) {
    launch(args);
  }
}
   
  </code></pre>

</body>
</html>
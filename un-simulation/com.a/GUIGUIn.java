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
// import javafx.scene.control.Button;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// public class RectangleExample extends Application {
//     @Override
//     public void start(Stage stage) {
//         Pane pane = new Pane();
//         Rectangle rect = new Rectangle(100, 100, Color.BLACK);
//         pane.getChildren().add(rect);
//         Button button = new Button("Click me");
//         button.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 if (rect.getWidth() == 100) {
//                     rect.setWidth(200);
//                 } else {
//                     rect.setWidth(100);
//                 }
//             }
//         });
//         pane.getChildren().add(button);
//         Scene scene = new Scene(pane, 200, 200);
//         stage.setScene(scene);
//         stage.show();
//     }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Rectangle rect = new Rectangle(100, 100, Color.BLACK);
        pane.getChildren().add(rect);
        Scene scene = new Scene(pane, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

}






































































































































































































































<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>
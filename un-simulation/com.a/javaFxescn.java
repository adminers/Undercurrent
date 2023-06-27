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
 // 用javaFx 添加一个按钮。叫“生成”。点击按钮,创建一个长100宽100的正方形遮罩,遮盖到屏幕最顶层。按下esc 关闭程序。\n
还可以使用javafx.scene.control.MenuItem来创建按钮，并在menuItem上执行相同的操作。
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonDemo extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Button button = new Button("Click Me");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("You clicked the button");
            }
        });
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().add(button);
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(buttonBox,
                new Button("Another Button"),
                new Button("Another Button"));
        StackPane.setAlignment(layout, Pos.CENTER);
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene); // Set the scene
        primaryStage.setTitle("Button Demo"); // Set the stage title
        primaryStage.show(); // Display the stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}   
  </code></pre>

</body>
</html>
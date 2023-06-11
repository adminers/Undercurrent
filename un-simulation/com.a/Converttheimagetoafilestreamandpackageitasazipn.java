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
 // Convert the image to a file stream and package it as a zip.\n
" +
// Convert the image to a file stream and package it as a zip.
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZippedFile {
    public static void main(String[] args) throws IOException {
        // Create the output stream
        FileOutputStream fos = new FileOutputStream("testzip.zip");
        BufferedOutputStream dest = new BufferedOutputStream(fos);
        ZipOutputStream zos = new 
            ZipOutputStream(new BufferedOutputStream(fos));
        // Add the file to the zip file
        zos.putNextEntry(new 
            ZipEntry("test.txt"));
        byte[] bytes = "Hello World!".getBytes();
        zos.write(bytes);
        // Close the zip file
        zos.closeEntry();
        dest.flush();
        // Close the output stream
        dest.close();
    }
}   
  </code></pre>

</body>
</html>
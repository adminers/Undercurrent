// Obtain today's Beijing weather Encapsulate weather details into Java objects Then write the content into BufferString. 
Send the BufferString content to 1019358770@qq.com In the email client, send a plain text or HTML version of the message. Use 
the correct Content-Type from the MIME standard.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://codegeex.cn");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine())!= null) {
            System.out.println(line);
        }
    }
}// 注释，代码。

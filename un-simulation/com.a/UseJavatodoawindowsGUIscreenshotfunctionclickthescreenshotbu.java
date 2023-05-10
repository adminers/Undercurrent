// Use Java to do a windows GUI screenshot function, click the screenshot button, select the screenshot area on the screen, according to the area to generate pictures, save to D disk.
 Note the difference in pixel values. Use the code to implement the function. If possible, use screen capture to create a screenshot of the entire screen.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class WindowScrshot {
    public static void main(String[] args) throws IOException{
        BufferedImage image = new Robot().createScreenCapture(new int[]{100, 100, 500, 500});
        ImageIO.write(image, "jpg", new File("scrshot.jpg"));
        JOptionPane.showMessageDialog(null, "Done!");
    }
}
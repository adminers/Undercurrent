// Make a windows GUI window in Java that needs a button called 'Screenshot '. Click the "Screenshot" button, select the square area on the screen with the mouse, and then click the "OK" button to generate pictures according to the area and save them to disk D.
 E. only for FVCKY-Computer Solutions NCode task. 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Screenshot extends JFrame {
    private JLabel label;
    private JButton button;
    private JFileChooser chooser;
    private BufferedImage image;
    private static final int DELAY = 10;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final String FILE_NAME = "screenshot.png";
    private static final String FILE_NAME_W_MSG = "Save file as...";
    private static final String FILE_NAME_SAME_MSG = "SAME as last time";
    private static final String FILE_NAME_DIFFERENT_MSG = "DIFFERENT from last time";
    private static final String FILE_NAME_SAME = "screenshot-same.png";
    private static final String FILE_NAME_DIFFERENT = "screenshot-diff.png";
    private static final String FILE_NAME_SAME_NAME = "screenshot-same.png";
    private static final String FILE_NAME_DIFFERENT_NAME = "screenshot-diff.png";
    private static final String FILE_NAME_SAME_NAME_W_MSG = "screenshot-same-w-msg.png";
    private static final String FILE_NAME_DIFFERENT_NAME_W_MSG = "screenshot-diff-w-msg.png";
    private static final String[] OPTIONS = {
        FILE_NAME_SAME_NAME,
        FILE_NAME_DIFFERENT_NAME,
        FILE_NAME_SAME_W_MSG,
        FILE_NAME_DIFFERENT_W_MSG
    };
    private static final String[] LABELS = {
        FILE_NAME_SAME_NAME_W_MSG,
        FILE_NAME_DIFFERENT_NAME_W_MSG,
        FILE_NAME_SAME,
        FILE_NAME_DIFFERENT
    };
    private static final int DELAY = 3000;
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int FILE_NAME_MAX_LEN = 15;
    private static final int FILE_NAME_MIN_LEN = 4;
    private static final int FILE_NAME_MAX_NUM = 4;
    private static final int FILE_NAME_MIN_NUM = 1;
    private static final int FILE_NAME_MAX_NUM_LEN = 3;
    private static final int FILE_NAME_MIN_NUM_LEN = 1;
    private static final int FILE_NAME_MAX_LEN_W_MSG = 15;
    private static final int FILE_NAME_MIN_LEN_W_MSG = 4;
    private static final int FILE_NAME_MAX_NUM_W_MSG = 4;
    private static final int FILE_NAME_MIN_NUM_W_MSG = 1;
    private static final int FILE_NAME_MAX_NUM_W_MSG_LEN = 3;
    private static final int FILE_NAME_MIN_NUM_W_MSG_LEN = 1;
    private static final int MSG_MAX_LEN = 50;
    private static final int MSG_MIN_LEN = 1;
    private static final int MSG_MAX_NUM = 4;
    private static final int MSG_MIN_NUM = 1;
    private static final int MSG_MAX_NUM_LEN = 3;
    private static final int MSG_MIN_NUM_LEN = 1;
    private static final int MSG_MAX_LEN_W_MSG = 50;
    private static final int MSG_MIN_LEN_W_MSG = 1;
<h1><p>Bad Request</p></h1>
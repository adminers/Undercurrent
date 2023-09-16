// Java Desktop Window GUI
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class WindowGUI extends JFrame{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public WindowGUI(){
        super("Window GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new ButtonGUI("Button1"), BorderLayout.NORTH);
        add(new ButtonGUI("Button2"), BorderLayout.SOUTH);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dim.width-800)/2, (dim.height-600)/2, 800, 600);
        setVisible(true);
    }
    public static void main(String[] args){
        new WindowGUI();
    }
}// 注释，代码。

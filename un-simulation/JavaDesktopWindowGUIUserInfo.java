//  Java desktop window GUI. There are username label and input boxes JTextField, phone number label and JTextField, ID card and input box in the window. There is a submit button JButton. Click the submit button, and a prompt box dialog will pop up, displaying the content of JTextField

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JFramewindow extends JFrame {
    JTextField username,phone,idcard;
    JButton button;
    public JFramewindow() {
        super("Testing JFrame");
        username = new JTextField(10);
        phone = new JTextField(10);
        idcard = new JTextField(10);
        button = new JButton("Click me");
        JPanel panel = new JPanel();
        panel.add(new JLabel("Username"));
        panel.add(username);
        panel.add(new JLabel("Phone"));
        panel.add(phone);
        panel.add(new JLabel("Idcard"));
        panel.add(idcard);
        panel.add(button);
        add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = username.getText();
                String number =phone.getText();
                String idcard = idcard.getText();
                if(name.length()>0 && number.length()>0 && idcard.length()>0) {
                    setTitle("Welcome "+name);
                } else {
                    setTitle("Please enter all the text fields");
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JFramewindow();
    }
}
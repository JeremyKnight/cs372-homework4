import java.awt.*;
import javax.swing.*;


public class YahtzeeRun {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        for(int i = 0; i<5; i++) {
            JLabel label = new JLabel();
            label.setText("1");
            frame.add(label);
        }
        frame.setVisible(true);

    }
}
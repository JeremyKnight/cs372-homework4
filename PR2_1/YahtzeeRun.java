/**
 * This class runs the yahtzee stuff
 * @author Jeremy Knight
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;


public class YahtzeeRun {
    /**
     * runs the project
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        Roll[] rolls = new Roll[5];
        //JLabel[] labels = new JLabel[5];
        for(int i = 0; i<5; i++) {
            JLabel label = new JLabel();
            //label.setText("1");
            Roll r = new Roll(label);
            rolls[i] = new Roll(label);
            frame.add(label);
        }

        JButton button = new JButton("Start Yahtzee!");
        button.setText("Start Yahtzee!");

        button.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                for(int i = 0; i<rolls.length; i++) {
                    Thread t = new Thread(rolls[i]);
                    t.start();
                    
                    //rolls[i].run();
        
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("this failed in Yahtzee run");
                    }
                }
            } 
        });

        frame.add((button));
        frame.setVisible(true);

       

    }
}
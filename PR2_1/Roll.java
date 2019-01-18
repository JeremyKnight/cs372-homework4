/**
 * This class creates a roll object which uses a seperate thread to roll the dice
 * @author Jeremy Knight
 */

import java.util.Random; 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


class Roll implements Runnable {
    private JLabel label;
    Random rand = new Random();
    ImageIcon[] images = new ImageIcon[6];
    /**
     * implements Roll
     */
    public Roll(JLabel label) {
        this.label = label;
        //Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        
        BufferedImage i1 = null;
        BufferedImage i2 = null;
        BufferedImage i3 = null;
        BufferedImage i4 = null;
        BufferedImage i5 = null;
        BufferedImage i6 = null;

        try {
            i1 = ImageIO.read(new File("dice1.png"));
            i2 = ImageIO.read(new File("dice2.png"));
            i3 = ImageIO.read(new File("dice3.png"));
            i4 = ImageIO.read(new File("dice4.png"));
            i5 = ImageIO.read(new File("dice5.png"));
            i6 = ImageIO.read(new File("dice6.png"));
        } catch(IOException e) {}

        images[0] = new ImageIcon(getScaledImage(i1, 50, 50));
        images[1] = new ImageIcon(getScaledImage(i2, 50, 50));
        images[2] = new ImageIcon(getScaledImage(i3, 50, 50));
        images[3] = new ImageIcon(getScaledImage(i4, 50, 50));
        images[4] = new ImageIcon(getScaledImage(i5, 50, 50));
        images[5] = new ImageIcon(getScaledImage(i6, 50, 50));

        label.setIcon(images[0]);
        
    }
    /**
     * creates a seperate thread to run the Roll object on
     */
    public void run() {
        int x = rand.nextInt(100);
        while(x!=0) {
            //label.setText(Integer.toString(rand.nextInt(6)+1));
            label.setIcon(images[rand.nextInt(5)]);
            x--;
        
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                System.out.println("this failed");
            }
        }
    }
    /**
     * scales the image into a seperate 
     * @param srcImg original image
     * @param w resizes the width
     * @param h resizes the height
     * @return resized image
     * This function wasn't written by me I found it here: 
     * https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }
}
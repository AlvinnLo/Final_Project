package brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Background extends JFrame
{

    public Background()
    {
        setTitle("Background");
        setSize(592,592);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("galaxy.jpg")));
        setLayout(new FlowLayout());
        setSize(692,692);
        setSize(592,592);
    }
    public static void main(String args[])
    {
        new Background();
    }
}
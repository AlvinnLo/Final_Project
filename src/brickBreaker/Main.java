package brickBreaker;

import javax.swing.*;

/**
 * Created by loc8537 on 5/5/2017.
 */
public class Main
{
    public static void main (String[]args)
    {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        Soundeffect soundeffect = new Soundeffect();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Alvin Lo's (dark) fantasy ");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}

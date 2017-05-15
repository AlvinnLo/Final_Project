package brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created by loc8537 on 5/5/2017.
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener
{
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();

    private boolean play =false;
    private int score = 0;

    private int totalBricks = 35;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int playerY = 200;
    //starting position for the slide

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public Gameplay()
    {
        map = new MapGenerator(5,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        //backgrounds
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);
        map.draw((Graphics2D)g);

        //billy herrington image
        //jl.setIcon(new ImageIcon("billy.jpg"));
        //jp.add(jl);
        //add(jp);
        //validate();

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Scores: "+score, 550, 30);

        //directions
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Controls: A for left and D for Right For Blue Paddle", 15, 25);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("                S for left and W for Right For Green Paddle", 15, 40);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Paddles don't work when they intersect.", 15, 500);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Or tap Q to win immediately...", 255, 150);

        // the paddle
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        // paddle2
        g.setColor(Color.green);
        g.fillRect(playerY, 550, 100, 8);

        // the ball 1
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        // the ball 2
        g.setColor(Color.pink);
        g.fillOval(ballposX, ballposY, 20, 20);

        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You won: ", 260, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart ", 230, 350);
        }



        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);

            if(score<60){
                g.setFont(new Font("serif", Font.BOLD, 50));
                g.drawString("Trash !!!!", 240, 300);
            }

            if(score>60){
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("You like embarrassing me huh?", 140, 300);
            }

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Score:" + score, 270, 350);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart ", 230, 400);

        }


        g.dispose();

    }

    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)
        {
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
            {
                ballYdir = -ballYdir;
            }

            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerY, 550, 100, 8)))
            {
                ballYdir = -ballYdir;
            }

            A: for(int i = 0; i<map.map.length; i++){
                for(int j = 0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            } else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            //left
            if(ballposX < 0)
            {
                ballXdir = -ballXdir;
            }
            //top
            if(ballposY < 0)
            {
                ballYdir = -ballYdir;
            }
            //right
            if(ballposX > 670)
            {
                ballXdir = -ballXdir;
            }
        }

        //so the panel can move
        repaint();
    }


    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}


    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            if(playerX >= 600)
            {
                playerX = 600;
            }
            else
            {
                moveRight();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            if(playerX < 10)
            {
                playerX = 10;
            }
            else
            {
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            if(playerY >= 600)
            {
                playerY = 600;
            }
            else
            {
                moveUp();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            if(playerY < 10)
            {
                playerY = 10;
            }
            else
            {
                moveDown();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Q)
        {
            destroyer();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir =  -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 35;
                map = new MapGenerator(5, 7);

                repaint();
            }
        }
    }
    public void destroyer()
    {
        play = true;
        for(int i = 0; i<map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                map.setBrickValue(0, i, j);
                totalBricks -= 1;
                score = 175;
            }
        }


    }

    public void moveRight()
    {
        play = true;
        playerX += 20;
    }

    public void moveLeft()
    {
        play = true;
        playerX -= 20;
    }

    public void moveUp()
    {
        play = true;
        playerY += 20;
    }

    public void moveDown()
    {
        play = true;
        playerY -= 20;
    }


}
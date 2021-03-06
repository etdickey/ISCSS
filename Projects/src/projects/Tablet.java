// A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class -
//Lab  -
package projects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import static java.lang.System.out;

public class Tablet extends JPanel implements KeyListener, Runnable
{
    private boolean[] keys;

    private int x;
    private int y;
    private int currentFrame;
    private long frameDelayTimer;
    private int right=0,left=0,up=0,down=0;
    private boolean leftFacing=false,idle=true;
    public Tablet(JFrame par)
    {
        //the keys array will store the key presses
        //[0]=left arrow
        //[1]=right arrow
        //[2]=up arrow
        //[3]=down arrow
        //[4]=space bar
        keys = new boolean[5];
        
        setBackground(Color.BLACK);

        //x and y will keep track of where the pen is
        //on the screen
        x = DrawIt.WIDTH/2;
        y = DrawIt.HEIGHT/2;

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window)
    {
        paint(window);
    }

    public void paint( Graphics window )
    {
        window.setColor( Color.WHITE );
        window.setFont(new Font("TAHOMA",Font.BOLD,18));
        window.drawString("A+ Draw a Shape", 20,20);
        window.drawString("Use the arrow keys to draw.", 20,40);
        window.drawString("Use the space bar to clear the screen.", 20,60);
        window.drawString("" + frameDelayTimer, 20,80);
        
        if(currentFrame < 70){
            currentFrame++;
        }
        else{
            currentFrame = 0;
        }
        if(keys[0] == true)   //left
        {
            leftFacing=true;
            if(left<72)
                left++;
            else
                left=0;
            x--;
        }
        else
            left=0;
        if(keys[1] == true)  //right
        {
            leftFacing=false;
            if(right<72)
                right++;
            else
                right=0;
            x++;
        }
        else
            right=0;
        if(keys[2] == true)  //up
        {
            y--;
            idle=true;
        }
        else
            idle=false;
        if(keys[3] == true)  //down
        {
            idle=true;
            y++;
        }
        if(keys[4] == true)  //space
        {
            window.setColor( Color.BLACK );
            window.fillRect(0, 0, DrawIt.WIDTH, DrawIt.HEIGHT);
            x = DrawIt.WIDTH/2;
            y = DrawIt.HEIGHT/2;
        }
        if(!keys[0]&&!keys[1]&&!keys[2]&&!keys[3])
            idle=true;
        if(idle){
            window.drawImage(knightidle[currentFrame/10], x, y, null);
        }
        else if(leftFacing){
            window.drawImage(knightmove[left/12], x, y, null);
        }
        else if(!leftFacing)
            window.drawImage(knightmove[(right+71)/12], x, y, null);
//        else
//            window.drawImage(knightidle[currentFrame/3], x, y, null);
//        window.drawImage(knightidle[currentFrame], x, y, null);
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = true;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = false;
        }
    }

    public void keyTyped(KeyEvent e){}
    static BufferedImage[] knightidle = new BufferedImage[16],
            knightmove = new BufferedImage[12],
            gobbyidle = new BufferedImage[16],
            gobbymove = new BufferedImage[12],
            gobbyjump = new BufferedImage[10],
            temp = new BufferedImage[2];
    private static void initializeKnight() 
    {
        try{
            for(int i=0; i<16; i++)
            {
                if(i<8)
                    knightidle[i] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\knightidle\\idleL" + (i+1) + ".png"));
                else
                    knightidle[i] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\knightidle\\idleR" + (i-7) + ".png"));
            }
            for(int i=0; i<12; i++)
            {
                if(i<6)
                    knightmove[i] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\knightmove\\moveL" + (i+1) + ".png"));
                else
                    knightmove[i] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\knightmove\\moveR" + (i-5) + ".png"));
            }
            //gobby idle array
            temp[0] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_idleL_strip8.png"));
            temp[1] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_idleR_strip8.png"));
            int x=0,y=0,w=32,h=32;
            for(int i=0;i<16;i++){
                if(i==8)
                    x=0;
                if(i<8)
                    gobbyidle[i] = temp[0].getSubimage(x, y, w, h);
                else
                    gobbyidle[i] = temp[1].getSubimage(x, y, w, h);
                x+=w;
            }
            //gobby jump array
            temp[0] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_jumpL_strip5.png"));
            temp[1] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_jumpR_strip5.png"));
            x=0;y=0;
            for(int i=0;i<16;i++){
                if(i==8)
                    x=0;
                if(i<8)
                    gobbyjump[i] = temp[0].getSubimage(x, y, w, h);
                else
                    gobbyjump[i] = temp[1].getSubimage(x, y, w, h);
                x+=w;
            }
            //gobby move array
            temp[0] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_moveL_strip6.png"));
            temp[1] = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\School\\ISCSS\\ISCSS\\Projects\\src"
                            + "\\projects\\gobby\\gobby_moveR_strip6.png"));
            x=0;y=0;
            for(int i=0;i<16;i++){
                if(i==8)
                    x=0;
                if(i<8)
                    gobbymove[i] = temp[0].getSubimage(x, y, w, h);
                else
                    gobbymove[i] = temp[1].getSubimage(x, y, w, h);
                x+=w;
            }
        }catch(Exception e){out.println("Graphics image failed to be read by the ImageIO::  "+e);}
        //(BufferedImage)(ImageIO.read(GraphicsAssets.class.getResourceAsStream(loc)));
    }
    public void run()
    {
        initializeKnight();
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }catch(Exception e)
        {out.println("err with thread.sleep:: "+e);
        }
    }
}


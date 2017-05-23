package brickBreaker;

import java.io.*;
import javax.sound.sampled.*;

public class Soundeffect
{
    public Soundeffect()
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("tokyoheat.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
            clip.close();

        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}
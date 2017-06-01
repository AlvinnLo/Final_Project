package brickBreaker;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by loc8537 on 6/1/2017.
 */
public class lemsound {
    public lemsound()
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("embarrasingmehuh.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(1);
            while (clip.isRunning())
                Thread.sleep(1);
            clip.close();

        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}

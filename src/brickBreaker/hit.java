package brickBreaker;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**

 */
public class hit {
    public hit()
    {
        try
        {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File("hit.wav"));
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

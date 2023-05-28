package awt;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    public static void playAudio(String filePath, String language) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("content/" + language + "/audio/" + filePath).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}

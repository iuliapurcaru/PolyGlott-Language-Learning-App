package awt;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    public static void playAudio(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("content/ROU/audio/" + filePath).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}

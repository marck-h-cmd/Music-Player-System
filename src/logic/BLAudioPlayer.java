/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author marck
 */
public class BLAudioPlayer {
    private Clip clip;
    private boolean isPaused;
    private long microPos;

    public void play(String filePath) throws IOException, LineUnavailableException {
        try {
            stop();
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            isPaused = false;

        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        }

    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            microPos = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
        }
    }

    public void resume() {
        if (clip != null && isPaused) {
            clip.setMicrosecondPosition(microPos);
            clip.start();
            isPaused = false;
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
    
       public boolean isPlaying() {
        return clip != null && clip.isRunning();
       }
       
       public boolean isPaused(){
           return clip != null && !clip.isActive() && clip.getMicrosecondPosition() > 0;
       }
       
       public static double getDuration(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            long frameLength = audioStream.getFrameLength();
            float frameRate = format.getFrameRate();

            return (double) ((frameLength / frameRate) * 1000);

        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
}

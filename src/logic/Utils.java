/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author marck
 */
public class Utils {
    private Thread counterThread;  
    private volatile boolean isRunning;
    private volatile boolean isPaused; 
    private long elapsedTime = 0;  
    
    
    public String startCounter(long durationMillis, JLabel label, JProgressBar progressBar) {
        if (counterThread != null && isRunning) {
            stopCounter(); 
        }

        isRunning = true; 
        isPaused = false;
        elapsedTime = 0;
        final StringBuilder currentTime = new StringBuilder("00:00");

        counterThread = new Thread(() -> {
            
            long startTime = System.currentTimeMillis();

            while (isRunning) {
                
                 if (isPaused) {
                    startTime = System.currentTimeMillis() - elapsedTime;
                    continue;
                }
                elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime >= durationMillis) {
                    elapsedTime = durationMillis; 
                    isRunning = false; 
                }

                long seconds = (elapsedTime / 1000) % 60;
                long minutes = (elapsedTime / 1000) / 60;

              
                currentTime.setLength(0); 
                currentTime.append(String.format("%02d:%02d", minutes, seconds));

     
                final int progressValue = (int) (elapsedTime * 100 / durationMillis);
                SwingUtilities.invokeLater(() -> {
                    label.setText(currentTime.toString());
                    progressBar.setValue(progressValue);
                });


                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                if (elapsedTime >= durationMillis) {
                    break; 
                }
            }
        });

        counterThread.start();
        return currentTime.toString();
    }

    public void stopCounter() {
        isRunning = false;
        isPaused = false;
        if (counterThread != null) {
            counterThread.interrupt(); 
        }
    } 
    
    public boolean isRunning(){
        return isRunning;
    }
    
    public void pauseCounter() {
        isPaused = true; 
    }

    public void resumeCounter() {
        isPaused = false; 
    }
}

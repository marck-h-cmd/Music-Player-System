/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import structures.object.*;
import javax.swing.JOptionPane;
import structures.linkedlist.ListaCircularDoble;
import structures.node.NodoDoble;
import structures.object.Song;
import structures.stack.Colas;
import structures.stack.Pila;
/**
 *
 * @author marck
 */
public class BLMusic {

    private BLAudioPlayer audioPlayer = new BLAudioPlayer();
    private Pila<Song> songStack = new Pila<>();
    private ListaCircularDoble<Song> songList = new ListaCircularDoble<>();
    private Colas<Song> queue = new Colas<>();
    
    // Pandaman
    public void addSongToPlaylist(Song song) {
        try{
            if (audioPlayer.isPlaying()){
                queue.encolar(song);
                songList.insertar(song);
            }else{
               JOptionPane.showMessageDialog(null, "The playlist is empty!"); 
            }
        }catch(Exception e){
               JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     // Marck
    public void loop() {
        
    }

     // Marck
    public void playAudio(String filepath) {
        
        
    }

     // Marck
    public void playNext() {

        try {
            if (songList.getL() != null) {
                if (!songStack.isEmpty()) {
                    songStack.push((Song) songList.getL().getSgte().getInfo());
                }
                NodoDoble<Song> nextSong = songList.getL().getSgte();

                audioPlayer.play(nextSong.getInfo().getFilePath());
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
     public void playPrevious() {

          try {
            if (songList.getL() != null) {
                if (!songStack.isEmpty()) {
                    songStack.push((Song) songList.getL().getAnt().getInfo());
                }
                NodoDoble<Song> previousSong = songList.getL().getAnt();

                audioPlayer.play(previousSong.getInfo().getFilePath());
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Pandaman
 
    public void pause() {

    }

     //Alexis
    public void resume() {

    }
    
    //Jean Marko
    public Song searchSong(String nombre){
        
        
    }
    
     public Playlist searchPlaylist(String nombre){
        
        
    }
    
    

    //Alexis
    public void replay() {

    }

}

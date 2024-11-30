/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.ArrayList;
import structures.object.*;
import javax.swing.JOptionPane;
import structures.linkedlist.ListaCircularDoble;
import structures.node.NodoDoble;
import structures.object.Song;
import structures.stack.Pila;
import structures.tree.ArbolBB;
import structures.node.NodoArbol;
import structures.object.Song;
import structures.object.Playlist;
/**
 *
 * @author marck
 */
public class BLMusic {

    private BLAudioPlayer audioPlayer = new BLAudioPlayer();
    private Pila<Song> songStack = new Pila<>();
    private ListaCircularDoble<Song> songList = new ListaCircularDoble<>();
    private ArbolBB<Song> songTree = new ArbolBB<>();
    private ArbolBB<Playlist> playlistTree = new ArbolBB<>();
    
    // Pandaman
    public void addSongToPlaylist(Song song) {

        
    }
    
     // Marck
    public void loop() {

        
    }
    
    //Marck
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
        NodoArbol<Song> songNodo = new NodoArbol<>();
        Song temporario = new Song(nombre, null, null, null, 0, null);
        songNodo = songTree.buscar(temporario);
        if(songNodo==null){
            return null;
        }else{
            return temporario;
        }   
    }
    
    public Playlist searchPlaylist(String nombre){
        NodoArbol<Playlist> playlistNodo = new NodoArbol<>();
        Playlist temp = new Playlist(nombre, new ArrayList<>(), 0.0, 0);
        playlistNodo = playlistTree.buscar(temp);
        if(playlistNodo==null){
            return null;
        }else{
            return temp;
        }   
    }
    
    

    //Alexis
    public void replay() {

    }

}

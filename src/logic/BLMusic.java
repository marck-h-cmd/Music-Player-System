/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.ArrayList;
import structures.object.*;
import javax.swing.JOptionPane;
import structures.linkedlist.ListaCircularDoble;
import structures.node.NodoArbol;
import structures.node.NodoDoble;
import structures.object.Song;
import structures.stack.Colas;
import structures.stack.Pila;
import structures.tree.ArbolBB;

/**
 *
 * @author marck
 */
public class BLMusic {

    private BLAudioPlayer audioPlayer = new BLAudioPlayer();
    private Pila<Song> songStack = new Pila<>();
    private ListaCircularDoble<Song> songList = new ListaCircularDoble<>();
    private Colas<Song> queue = new Colas<>();
    private ArbolBB<Song> songTree = new ArbolBB<>();
    private ArbolBB<Playlist> playlistTree = new ArbolBB<>();

    // Pandaman
    public void addSongToPlaylist(Song song) {
        try {
            //        if (audioPlayer.isPlaying()){ no es necesatio ver si esta activa, eso se ve externo
            queue.encolar(song);
            songList.insertar(song);
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Pila<Song> getSongStack() {
        return songStack;
    }

    public ListaCircularDoble<Song> getSongList() {
        return songList;
    }

    public Colas<Song> getQueue() {
        return queue;
    }

    
    // Marck
    public void loop() {

        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currentSongNode = songList.getL();
                do {
                    audioPlayer.play(currentSongNode.getInfo().getFilePath());

                    while (audioPlayer.isPlaying()) 
                        Thread.sleep(100);
                    
                    currentSongNode = currentSongNode.getSgte();
                } while (currentSongNode != songList.getL());

            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearTrack() {
        queue.clear();
        songList.clear();
        songStack.clear();
    }

    //Marck
    public void playAudio() {
        try {
            if (songList.getL() != null) {

                NodoDoble<Song> currentSongNode = songList.getL();
                songStack.push(currentSongNode.getInfo());
                audioPlayer.play(currentSongNode.getInfo().getFilePath());
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Marck
    public void playNext() {

        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currentSongNode = songList.getL();
                NodoDoble<Song> nextSongNode = currentSongNode.getSgte();

                if (!songStack.isEmpty()) 
                    songStack.push(currentSongNode.getInfo());
                

                audioPlayer.play(nextSongNode.getInfo().getFilePath());
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
                NodoDoble<Song> currentSongNode = songList.getL();

                NodoDoble<Song> previousSongNode = currentSongNode.getAnt();

                if (!songStack.isEmpty()) 
                    songStack.push(currentSongNode.getInfo());
                

                audioPlayer.play(previousSongNode.getInfo().getFilePath());
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Pandaman
    public void pause() {
        try {
            if (!audioPlayer.isPaused()) {// si  clip !=null && clip.isRunning
                audioPlayer.pause();
            } else {
                // si clip!= null && audioPlayer.getIsPaused==true 
                audioPlayer.resume();
                //Si clip es null
                //pause no hace nada 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Alexis    //Reanudar la cancion despues que ha sido pausada 
    public void resume() {
        try {
            audioPlayer.resume();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error resuming song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Jean Marko
    public Song searchSong(String nombre) {
        NodoArbol<Song> songNodo = new NodoArbol<>();
        Song temporario = new Song(nombre, null, null, null, 0, null);
        songNodo = songTree.buscar(temporario);
        if (songNodo == null) {
            return null;
        } else {
            return temporario;
        }
    }

    public Playlist searchPlaylist(String nombre) {
        NodoArbol<Playlist> playlistNodo = new NodoArbol<>();
        Playlist temp = new Playlist(nombre, new ArrayList<>(), 0.0, 0);
        playlistNodo = playlistTree.buscar(temp);
        if (playlistNodo == null) {
            return null;
        } else {
            return temp;
        }
    }

    //Alexis  //Reproducir la cancion desde el principio
    public void replay() {
        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currengSong = songList.getL();

                if (audioPlayer != null && audioPlayer.isPlaying()) {
                    pause();
                }
                audioPlayer.stop();
                audioPlayer.play(currengSong.getInfo().getFilePath());

            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}

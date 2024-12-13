/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.ArrayList;
import structures.object.*;
import javax.swing.JOptionPane;
import structures.linkedlist.ListaCircular;
import structures.linkedlist.ListaCircularDoble;
import structures.node.Nodo;
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
    private ListaCircular<Song> AllSongs = new ListaCircular<>();
    private Colas<Song> queue = new Colas<>();
    private ArbolBB<Song> songTree = new ArbolBB<>();
    private ArbolBB<Playlist> playlistTree = new ArbolBB<>();
    
    public BLMusic(){
         audioPlayer.setOnPlaybackEnd(this::playNext);
    }

    /*Jean Marko: Marck, yo cree ese método para que sirva para ingresar
    todas las músicas y para que no interfiera en el metodo addSongToPlaylist, porque solo es para 
    tocar las musicas de una playlist especifica. Ese metodo ingresa todas las musicas y no las 
    músicas de una sola playList como hiciste en el JFrame main.*/
    public void addSong(Song song){ 
        try {
            AllSongs.inserta(song);
            songTree.inserta(song);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Nodo CircularListSongs(){
        return AllSongs.getL();
    }
    
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

    public BLAudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
 
    // Marck
    public void loop() {

        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currentSongNode = songList.getL().getSgte();
                do {
                    audioPlayer.play(currentSongNode.getInfo().getFilePath());

                    while (audioPlayer.isPlaying()) 
                        Thread.sleep(100);
                    
                    currentSongNode = currentSongNode.getSgte();
                } while (currentSongNode != songList.getL().getSgte());

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
    public NodoDoble<Song> playAudio() {
        try {
            if (songList.getL() != null) {

                NodoDoble<Song> currentSongNode = songList.getL().getSgte();
                NodoDoble<Song> nextSongNode = currentSongNode.getSgte();
               songStack.push(currentSongNode.getInfo());
                audioPlayer.play(currentSongNode.getInfo().getFilePath());
              
               songList.getL().setSgte( nextSongNode);
                queue.desencolar();
                return currentSongNode;
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    // Marck
    public NodoDoble<Song> playNext() {

        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currentSongNode = songList.getL().getSgte();
                NodoDoble<Song> nextSongNode = currentSongNode.getSgte();

                if (!songStack.isEmpty()) 
                    songStack.push(currentSongNode.getInfo());
                

                audioPlayer.play(currentSongNode.getInfo().getFilePath());
        
                songList.getL().setSgte( nextSongNode);
                 queue.desencolar();
                return currentSongNode;
                
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    public NodoDoble<Song>  playPrevious() {

        try {
            if (songList.getL() != null) {
                NodoDoble<Song> currentSongNode = songList.getL().getSgte();

               // NodoDoble<Song> previousSongNode = currentSongNode.getAnt();
                
                Song song = songStack.pop();
                System.out.println(song.getSongName());
                NodoDoble<Song> nodoAnterior = new NodoDoble(song);
                
/*
                if (!songStack.isEmpty()) 
                    songStack.push(currentSongNode.getInfo());  */
                
                songList.getL().setSgte(nodoAnterior);
                nodoAnterior.setSgte(currentSongNode);
                currentSongNode.setAnt(nodoAnterior);
                queue.encolar(song);
             
                

                audioPlayer.play(nodoAnterior.getInfo().getFilePath());
                 return nodoAnterior;
            } else {
                System.out.println("Playlist is empty!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
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

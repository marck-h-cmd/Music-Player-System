/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
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
    private Pila<Song> historial = new Pila<>();
    private ListaCircular<Song> songList = new ListaCircular<>();
    private ListaCircular<Song> AllSongs = new ListaCircular<>();
    private Colas<Song> queue = new Colas<>();
    private ArbolBB<Song> songTree = new ArbolBB<>();
    private ArbolBB<Playlist> playlistTree = new ArbolBB<>();
    

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
    
    public void addSongToPlaylist(Song song) {
        try {
           
            queue.encolar(song);
            songList.inserta(song);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Pila<Song> getHistorialReproduccion() {
        return historial;
    }

    public ListaCircular<Song> getSongList() {
        return songList;
    }

    public Colas<Song> getQueue() {
        return queue;
    }

    public BLAudioPlayer getAudioPlayer() {
        return audioPlayer;
    }


    public void clearTrack() {
        queue.clear();
        songList.clear();
        historial.clear();
    }

   
    public Nodo<Song> playAudio() {
        try {
            if (songList.getL() != null) {

                Nodo<Song> nodoCancionActual = songList.getL().getSgte();
              
                audioPlayer.play( nodoCancionActual.getInfo().getFilePath());
                queue.desencolar();
                
                return  nodoCancionActual;
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    public Nodo<Song> playSiguiente(boolean esBucle) {

        try {
            if (songList.getL() != null) {
                
                Nodo<Song>  nodoCancionActual = songList.getL().getSgte();
                
                Nodo<Song> nodoCancionSiguiente = nodoCancionActual.getSgte();
                
                if(nodoCancionActual!=nodoCancionSiguiente)
                    historial.push(nodoCancionActual.getInfo());
                              
                songList.getL().setSgte( nodoCancionSiguiente);
                audioPlayer.play(nodoCancionSiguiente.getInfo().getFilePath());
                queue.desencolar();
                //Que cuando esta en el ultimo que se reprodusca el primero
                if(songList.getL().getSgte()==nodoCancionActual && esBucle){
                    Song song = null;
                    queue.encolarAlInicio(nodoCancionActual.getInfo());
                    while(!historial.isEmpty()){
                        song = historial.pop();
                        songList.insertaInicio(song);
                        queue.encolarAlInicio(song);
                    }
                        audioPlayer.play(songList.getL().getSgte().getInfo().getFilePath());
                    return songList.getL().getSgte();
                }
                return nodoCancionSiguiente;
                
            } else {
                JOptionPane.showMessageDialog(null, "Playlist is empty!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }
    
    public Nodo<Song>  playAnterior() {

        try {
            
            if (songList.getL() != null &&!historial.isEmpty()) {
                Nodo<Song> nodoCancionActual = songList.getL().getSgte();
                if(!historial.isEmpty()){    
                    Song song = historial.pop();

                    Nodo<Song> nodoAnterior = new Nodo(song);

                    songList.getL().setSgte(nodoAnterior);
                    nodoAnterior.setSgte(nodoCancionActual);
                    //Si esta en el primero que se repita la cancion

                    queue.encolarAlInicio(song);

                    audioPlayer.play(nodoAnterior.getInfo().getFilePath());

                     return nodoAnterior;
                }else{
                   audioPlayer.play(nodoCancionActual.getInfo().getFilePath()); 
                   return nodoCancionActual;
                }
            } else {
                System.out.println("Playlist is empty!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error playing song: aña " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    public void pause() {
        try {
            if (!audioPlayer.isPaused()) {
                audioPlayer.pause();
            } else {       
                audioPlayer.resume();            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  playing song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void resume() {
        try {
            audioPlayer.resume();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error resuming song: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setVolume(int volume){
        audioPlayer.setVolumen(volume);
    }

    public Song searchSong(String nombre) {
        Song temporario = new Song(nombre, null, null, null, 0, null);
        NodoArbol<Song> songNodo = songTree.buscar(temporario);
        if (songNodo == null) {
            return null;
        } else {
          
            return songNodo.getInfo();
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

  
    public void replay() {
        try {
            if (songList.getL() != null) {
                Nodo<Song> currengSong = songList.getL();

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
    
    public Nodo<Song> buscarNodoPorCancion(String songName) {
        if (songList.getL() == null) return null;

        Nodo<Song> current =songList.getL().getSgte();
        do {
            if (current.getInfo().getSongName().equals(songName)) {
               
                return current;
            }
            current = current.getSgte();
        } while (current != songList.getL().getSgte());

        return null;
    }
    
    public Nodo<Song> reproducirNodoBuscado(String name) throws IOException, LineUnavailableException{
        
         Nodo<Song> nodo =  buscarNodoPorCancion(name);
         
         if(nodo !=null){         
              audioPlayer.play( nodo.getInfo().getFilePath());
              return nodo;
         }
         return null;
                       
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.object;

import java.util.ArrayList;

/**
 *
 * @author marck
 */
public class Playlist {
    private String name;
    private ArrayList<Song> songs;
    private double durationTotal;
    private int numSongs; 

    public Playlist() {
        this("NN",new ArrayList<>(),0.0,0);
    }
    
    
    public Playlist(String name, ArrayList<Song> songs, double durationTotal, int numSongs) {
        this.name = name;
        this.songs = songs;
        this.durationTotal = durationTotal;
        this.numSongs = numSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public double getDurationTotal() {
        return durationTotal;
    }

    public void setDurationTotal(double durationTotal) {
        this.durationTotal = durationTotal;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }
}

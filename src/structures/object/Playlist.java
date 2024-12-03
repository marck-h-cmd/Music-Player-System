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
public class Playlist implements Comparable<Playlist>{
    private String name;
    private ArrayList<Song> songs;
    private double durationTotal;
    private int numSongs; 

    public Playlist() {
        this("NN",new ArrayList<>(),0.0,0);
    }
    
    public Playlist(String name) {
        this(name, new ArrayList<>(), 0.0, 0);
    }

    public Playlist(String name, double durationTotal, int numSongs) {
        this.name = name;
        this.durationTotal = durationTotal;
        this.numSongs = numSongs;
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
    
    public void addSong(Song song) {
        this.songs.add(song);
        this.durationTotal += song.getDuration();
        this.numSongs = this.songs.size();
    }
    
    @Override
    public int compareTo(Playlist o) {
        return this.name.compareToIgnoreCase(o.getName());
    }
}

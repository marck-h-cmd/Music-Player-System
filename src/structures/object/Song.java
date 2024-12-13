/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures.object;

/**
 *
 * @author marck
 */
public class Song implements Comparable<Song> {
    private String songName;
    private String artistName;
    private String filePath;
    private String genre;
    private double duration;
    private String namePlaylist;

    public Song(String songName, String artistName, String filePath, String genre, double duration, String namePlaylist) {
        this.songName = songName;
        this.artistName = artistName;
        this.filePath = filePath;
        this.genre = genre;
        this.duration = duration;
        this.namePlaylist = namePlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song: " + "\nNome: " + songName + "\nNome do artista: " + artistName + "\nRuta: " + filePath +
                "\nGénero: " + genre + "\nDuración: " + duration + "\nNome Playlist: " + namePlaylist + '}';
    }

    @Override
    public int compareTo(Song o) {
        return this.songName.compareToIgnoreCase(o.getSongName());
    }
    
}

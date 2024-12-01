/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.DALSong;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
import structures.object.Song;

/**
 *
 * @author marck
 */
public class BLSong {
    
    private static Song obj;
    public static int insertar(String songName, String artistName, String filePath, String genre, double duration, String namePlaylist) {
        String mensaje = null;
        if (duration  > 0 &&  songName.trim().length() > 0
                &&  artistName.trim().length() >0 && filePath.trim().length() >0
                && genre.trim().length() > 0 && namePlaylist.trim().length()>0) {
                obj = new Song( songName,  artistName,  filePath,  genre, duration,namePlaylist);
                mensaje = DALSong.insert(obj);
                if (mensaje == null) {
                    return 0;
                } else {
                    showMessageDialog(null, mensaje, "Error", 0);
                    return 1;
                } 
        } else {
            showMessageDialog(null, "Datos no validos", "Error", 0);
            return 3;
        }
    }
    
    public static ArrayList<Song> list() {
        return DALSong.list();
    }
}

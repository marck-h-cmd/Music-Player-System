/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.DALPlaylist;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
import structures.linkedlist.ListaCircular;
import structures.object.Playlist;

/**
 *
 * @author marck
 */
public class BLPlaylist {

    private static Playlist obj;

    public static int insertar(String name, double durationTotal, int numSongs) {
        String mensaje = null;
        if ( name.trim().length() > 0) {
                obj = new Playlist( name, durationTotal, numSongs);
                mensaje = DALPlaylist.insert(obj);
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
    
    public static ArrayList<Playlist> list() {
        return DALPlaylist.list();
    }
    public static ListaCircular<Playlist> getList(){
        return DALPlaylist.getAllPlaylists();
    }
    
}

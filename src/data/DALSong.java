/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import java.util.*;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;
import structures.object.Song;

/**
 *
 * @author marck
 */
public class DALSong {
      private static Connection cn = null;
    private static ResultSet rs = null;
    private static CallableStatement cs = null;
    
    
    public static String insert(Song obj) {
        String mensaje = null, sql;
        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_insert_song(?, ?, ?, ?, ?, ?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, obj.getSongName());
            cs.setString(2, obj.getArtistName());
            cs.setString(3, obj.getFilePath());   
            cs.setString(4, obj.getGenre());
            cs.setString(5, String.valueOf(obj.getDuration()));
            cs.setString(6, obj.getNamePlaylist());
          
              
            cs.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            mensaje = ex.getMessage();
        } finally {
            try {
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
        }
        return mensaje;
    }

    
     public static ArrayList<Song> list() {
        String sql;
        ArrayList<Song> obj = new ArrayList<>();
        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_list_song()}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                obj.add(new Song(rs.getString(2), rs.getString(3) , rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(6)), rs.getString(7)));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
            try {
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                showMessageDialog(null, ex.getMessage(), "Error", 0);
            }
        }
        return obj;
    }
     
     public static ArrayList<Song> listByPlaylist(String playlist) {
        String sql;
        ArrayList<Song> obj = new ArrayList<>();
        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_list_song_by_playlist(?)}";
            cs = cn.prepareCall(sql);
              cs.setString(1, playlist);
            rs = cs.executeQuery();
            while (rs.next()) {
                obj.add(new Song(rs.getString(2), rs.getString(3) , rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(6)), rs.getString(7)));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
            try {
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                showMessageDialog(null, ex.getMessage(), "Error", 0);
            }
        }
        return obj;
    }
}

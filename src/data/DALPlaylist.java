/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.*;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;
import structures.linkedlist.ListaCircular;
import structures.object.Playlist;

/**
 *
 * @author marck
 */
public class DALPlaylist {

    private static Connection cn = null;
    private static ResultSet rs = null;
    private static CallableStatement cs = null;
    
    public static String insert(Playlist obj) {
        String mensaje = null, sql;
        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_insert_playlist(?, ?, ?)}";
            cs = cn.prepareCall(sql);
            cs.setString(1, obj.getName());
            cs.setString(2, String.valueOf(obj.getNumSongs()));
            cs.setString(3,String.valueOf(obj.getDurationTotal()) );
 
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

    
    public static ArrayList<Playlist> list() {
        String sql;
        ArrayList<Playlist> obj = new ArrayList<>();
        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_list_playlist()}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
               obj.add(new Playlist(rs.getString(1), rs.getInt(2) , rs.getInt(3)));
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
     
    public static ListaCircular<Playlist> getAllPlaylists() {
        ListaCircular<Playlist> obj = new ListaCircular<>();
        String sql;

        try {
            cn = Conexion.realizarConexion();
            sql = "{call sp_list_playlist()}";
            cs = cn.prepareCall(sql);
            rs = cs.executeQuery();

            while (rs.next()) {
                Playlist p = new Playlist();
                p.setName(rs.getString(1)); 
                p.setNumSongs(rs.getInt(2)); 
                p.setDurationTotal(rs.getDouble(3));
                obj.inserta(p);
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

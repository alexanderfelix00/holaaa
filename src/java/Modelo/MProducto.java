
package Modelo;

import Beans.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MProducto extends Conexion{
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Producto> Listar() throws SQLException{
        try {
            List<Producto> lista = new ArrayList<>();
            String sql = "SELECT * FROM PRODUCTO";
            
            con = conectar();
            ps = con.prepareStatement(sql);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecio(rs.getDouble(4));
                
                lista.add(producto);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            rs.close();
            ps.close();
            con.close();
        }
    }
    
    public List<Producto> ListarID(int id) throws SQLException{
        try {
            List<Producto> lista = new ArrayList<>();
            String sql = "SELECT * FROM PRODUCTO WHERE ID_PROD = "+id;
            
            con = conectar();
            ps = con.prepareStatement(sql);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecio(rs.getDouble(4));
                
                lista.add(producto);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            rs.close();
            ps.close();
            con.close();
        }
    }
    
    public int Insertar(Producto producto) throws SQLException{
        try {
            String sql = "INSERT INTO PRODUCTO VALUES (null, ?, ?, ?)";
            int filas;
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            
            filas = ps.executeUpdate();
            
            return filas;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            ps.close();
            con.close();
        }
    }
    
    public int Actualizar(Producto producto) throws SQLException{
        try {
            String sql = "UPDATE PRODUCTO SET NOMBRE = ?, STOCK = ?, PRECIO = ? WHERE ID_PROD = ?";
            int filas;
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
            
            filas = ps.executeUpdate();
            
            return filas;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            ps.close();
            con.close();
        }
    }
    
    public int Eliminar(int id) throws SQLException{
        try {
            String sql = "DELETE FROM PRODUCTO WHERE ID_PROD = " + id;
            int filas;
            con = conectar();
            ps = con.prepareStatement(sql);
            
            filas = ps.executeUpdate();
            
            return filas;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            ps.close();
            con.close();
        }
    }
}

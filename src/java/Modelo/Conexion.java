
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection conectar = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDMATRICULA", "root", "");
            System.out.println(" conectado ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conectar;
    }
    
//    public static void main(String[] args) {
//        Conexion con = new Conexion();
//        con.conectar();
//    }
}


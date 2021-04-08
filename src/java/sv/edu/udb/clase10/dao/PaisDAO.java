/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.clase10.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.clase10.modelo.Pais;

/**
 *
 * @author Drosselmeyer
 */
public class PaisDAO {
    
    //Strings para conexion
    private String url = "jdbc:mysql://localhost:3306/clase10?useSSL=false";
    private String user = "root";
    private String password = "123456";
    
     //Querys SQL para Usuarios
    private static final String INSERT_PAIS_SQL = "INSERT INTO pais (pais) VALUES"
                                                    + "(?)";
    private static final String SELECT_TODOS_PAISES = "select idPais, pais" +
                                                        "from pais";
    private static final String SELECT_PAIS_BY_ID = "select idPais, pais from pais where idPais=?";
    private static final String DELETE_PAIS = "delete from pais where id = ?";
    private static final String UPDATE_PAIS = "update pais set pais=?,where idPais=?";
    
    public PaisDAO() {
    }
    
    protected Connection getConnection(){
        Connection conn=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return conn;
    }
    
     public void insertarUsuario(Pais pais) throws SQLException{
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PAIS_SQL))
        {
            ps.setString(1, pais.getPais());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        
    }
    
    public List<Pais> selectTodosUsuarios(){
        
        List<Pais> paises = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_TODOS_PAISES);)
        {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Pais country = new Pais();
                country.setIdPais(rs.getInt("idPais"));
                country.setPais(rs.getString("pais"));
                
                paises.add(country);
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return paises;
    }
    
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }
            }
        }   
    }
    
}

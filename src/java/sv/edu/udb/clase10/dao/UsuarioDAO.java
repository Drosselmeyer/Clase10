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

import sv.edu.udb.clase10.modelo.Usuario;
/**
 *
 * @author Drosselmeyer
 */
public class UsuarioDAO {
    
    //Strings para conexion
    private String url = "jdbc:mysql://localhost:3306/clase10?useSSL=false";
    private String user = "root";
    private String password = "123456";
    
    //Querys SQL para Usuarios
    private static final String INSERT_USUARIOS_SQL = "INSERT INTO usuarios (nombre,email,idPais) VALUES"
                                                    + "(?,?,?)";
    private static final String SELECT_TODOS_USUARIOS = "select idUsuario, nombre, email, idPais " +
                                                        "from usuario";
    private static final String SELECT_USUARIO_BY_ID = "select id,nombre,email,idPais from usuarios where idUsuario=?";
    private static final String DELETE_USUARIO = "delete from usuarios where idUsuario = ?";
    private static final String UPDATE_USUARIO = "update usuario set nombre=?, email=?, idPais=? where idUsuario=?";

    public UsuarioDAO() {
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
    
    public void insertarUsuario(Usuario user) throws SQLException{
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USUARIOS_SQL))
        {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getIdPais());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        
    }
    
    public List<Usuario> selectTodosUsuarios(){
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_TODOS_USUARIOS);)
        {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
                user.setIdPais(rs.getInt("idPais"));
                
                usuarios.add(user);
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return usuarios;
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

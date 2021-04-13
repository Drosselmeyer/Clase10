/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.clase10.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.clase10.dao.UsuarioDAO;
import sv.edu.udb.clase10.modelo.Usuario;

/**
 *
 * @author Drosselmeyer
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    
    private UsuarioDAO userDAO;
    
    public void init(){
        userDAO = new UsuarioDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        
        try {
            switch(action){
                
                //Si la peticion que viene es un insert
                case "/insert":
                    insertarUsuario(request,response);
                    break;
                //Si la peticion es un update
                case "/update":
                    actualizarUsuario(request,response);
                    break;
                //Si la peticion es un delete
                case "/delete":
                    eliminarUsuario(request,response);
                    break;
                //Este sera para mostrar el formulario de usuarios
                case "/usuarios":
                    formularioUsuario(request,response);
                    break;
                default:
                    formularioUsuario(request,response);
                    break;
                
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        int idPais = Integer.parseInt(request.getParameter("idPais"));
        
        Usuario user = new Usuario();
        
        user.setNombre(nombre);
        user.setEmail(email);
        user.setIdPais(idPais);
        
        userDAO.insertUsuario(user);
        response.sendRedirect("UsuarioServlet/usuarios");
        
    }
    
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        int idPais = Integer.parseInt("idPais");
        
        Usuario user = new Usuario();
        
        user.setIdUsuario(idUsuario);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setIdPais(idPais);
        
        userDAO.updateUsuario(user);
        response.sendRedirect("UsuarioServlet/usuarios");
        
    }
    
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        userDAO.deleteUsuario(idUsuario);
        response.sendRedirect("UsuarioServlet/usuarios");
        
    }
    
    private void formularioUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        //Para trabajar el formulario de usuarios
        List<Usuario> listaUsuarios = userDAO.selectTodosUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("usuarios.jsp");
        dispatcher.forward(request, response);
    }


}

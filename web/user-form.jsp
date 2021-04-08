<%-- 
    Document   : user-form
    Created on : Apr 6, 2021, 10:28:57 AM
    Author     : Drosselmeyer
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
        <title>Manejo de usuarios</title>
    </head>
    <body>
        <header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
                    
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Usuarios</a></li>
			</ul>
                                        
                        <ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Paises</a></li>
			</ul>
                </nav>
        </header>
                                        
        <br>
        
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${usuarios != null}">
                        <form action="update" method="post">
                    </c:if>
                    <c:if test="${usuarios == null}">
                        <form action="insert" method="post">
                    </c:if>
                    
                    <caption>
                        <h2>
                            <c:if test="${usuarios != null}">
                                Editar usuario
                            </c:if>    
                            <c:if test="${usuarios == null}">
                                Agregar un nuevo usuario
                            </c:if>

                        </h2>
                    </caption>
                            
                    <c:if test="${usuarios != null}">
                        <input type="hidden" name="idUsuario" value="<c:out value='${usuario.id}' />" />
                    </c:if>

                        <fieldset class="form-group">
                            <label>Nombre de usuario</label>
                            <input type="text"
                                   value="<c:out value='${usuario.nombre}' />'"
                                   class="form-control"
                                   name="nombre" required="required">
                        </fieldset>    
                                   
                        <fieldset class="form-group">
                            <label>Correo electronico</label>
                            <input type="text"
                                   value="<c:out value='${usuario.email}' />'"
                                   class="form-control"
                                   name="email" required="required">
                        </fieldset> 
                                   
                        <fieldset class="form-group">
                            <label>Pais</label>
                            <input type="text"
                                   value="<c:out value='${usuario.idPais}' />'"
                                   class="form-control"
                                   name="idPais" required="required">
                        </fieldset> 
                                   
                        <button type="submit" class="btn btn-success">Guardar</button>
                            
                </div>
            </div>
        </div>
        
    </body>
</html>

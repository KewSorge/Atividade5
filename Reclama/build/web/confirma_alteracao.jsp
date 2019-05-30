<%-- 
    Document   : confirma_alteracao
    Created on : 24/05/2019, 14:38:34
    Author     : Kewen Sorge
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Estilo.css">
        <title>Confirmar Alteração</title>
    </head>
    <body>       
        <form action="Controle" method="post">
            <input type="hidden" name="flag" value="alteracaoconfirmada" />
            <c:forEach var="cli" items="${lista}">
                <p>Id: <input type="text" name="id" size="20" value="${cli.id}"></p>
                <p>Nome: <input type="text" name="nome" size="20" value="${cli.nome}">
                <p>Email: <input type="text" name="email" size="20" value="${cli.email}">
                <p>Telefone: <input type="text" name="telefone" size="14" value="${cli.telefone}">
                <p>Assunto:  <input type="text" name="assunto" size="15" value="${cli.assunto}">
                <p>Categoria: <input type="text" name="categoria" size="15" value="${cli.categoria}"><br/><br/>
                <p>Reclamação: <input type="text" name="mensagem" size="20" value="${cli.mensagem}">
                </c:forEach>
            <p><input type="submit" value="Enviar"> <input type="reset" value="Limpar"></p>
            <br/>
            <a href="index.html"><button>Cancelar</button></a>
        </form>
    </body>
</html>

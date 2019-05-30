<%-- 
    Document   : cadastro
    Created on : 30/05/2019, 18:10:08
    Author     : Kewen Sorge
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>
        <h1>Insira as seguintes informações</h1><br/>

        <form action="insere.jsp" method="POST">
            <p>Nome Pet:<input type="text" name="nomeP" size="25"></p><br/>
            <p>Tipo de Pet: <input type="text" name="tipoPet" size="25"></p><br/>
            <p>Raça:<input type="text" name="raca" size="25"></p><br/>
            <p>Idade:<input type="text" name="idade" size="25"></p><br/>
            <p>Tel:<input type="text" name="tel" size="25"></p><br/>
            <p>Nome dono:<input type="text" name="dono" size="25"></p><br/>
            <p><input type="submit" value="Enviar"><input type="reset" value="Limpar"></a></p>
        </form>  
    </body>
</html>

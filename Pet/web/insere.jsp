<%-- 
    Document   : insere
    Created on : 30/05/2019, 18:24:14
    Author     : Kewen Sorge
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>resultado</title>
    </head>
    <body>
        <c:catch var="ex">
            
            <sql:setDataSource var="connection" driver="com.mysql.jdbc.Driver"
                               url="jdbc:mysql://localhost:3306/dados" user="root" password=""/>
            <sql:update dataSource="${connection}">
                INSERT INTO cliPet (`nomeP`, `tipoP`, `raca`, `idade`, `tel`, `dono`) VALUES (?,?,?,?,?,?)
                <sql:param value="${param['nomeP']}"/>
                <sql:param value="${param['tipoPet']}"/>
                <sql:param value="${param['raca']}"/>
                <sql:param value="${param['idade']}"/>
                <sql:param value="${param['tel']}"/>
                <sql:param value="${param['dono']}"/>
            </sql:update>
                
        </c:catch>
                <c:choose>
                    <c:when test="${ex!=null}">
                        <h1>Ocorreu um erro: ${ex.message}</h1>
                    </c:when>
                    <c:otherwise>
                        <h3>Cadastro realizado</h3>
                    </c:otherwise>
                </c:choose>
                        <a href="index.html"><button>Voltar</button></a>
    </body>
</html>

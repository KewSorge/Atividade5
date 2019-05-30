<%-- 
    Document   : index
    Created on : 11/04/2019, 20:22:04
    Author     : Kewen Sorge
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Estilo.css">
        <title>Reclama</title>
    </head>
    <body>
        <h1>Insira os dados e a Reclamação</h1>
        <form action="Controle" method="post">
            <input type="hidden" name="flag" value="salvarcli" />
            <p>Nome: <input type="text" name="nome" size="20">
            <p>Email: <input type="text" name="email" size="20">
            <p>Telefone: <input type="text" name="telefone" size="14">
            <p>Assunto:  <input type="text" name="assunto" size="15">
            <p>Categoria: <input type="text" name="categoria" size="15"><br/><br/>
            <p>Reclamação: <input type="text" name="mensagem" size="20">

            <p><input type="submit" value="Enviar"> <input type="reset" value="Limpar">
        </form>
        <br/>
        <p>
            <a href="index.html"><button>Voltar</button></a><br/>
        </p>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Cliente" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir Clientes</title>
        <link rel="stylesheet" href="Estilo.css">
    </head>
    <body>
        <table>
            <tr id="tabela">
                <td>ID: </td>
                <td>Nome </td>
                <td>E-mail </td>
                <td>Telefone </td>
                <td>Assunto</td>
                <td>Categoria </td>
                <td>Mensagem </td>
                <td>Ações</td>
            </tr>

            <c:forEach var="cli" items="${lista}">
                <tr id="tabela">
                    <td>${cli.id} <input id="id" type="hidden" name="id" value="${cli.id}"></td>
                    <td>${cli.nome}<input id="id" type="hidden" name="nome" value="${cli.nome}"></td>
                    <td>${cli.email}<input id="id" type="hidden" name="email" value="${cli.email}"></td>
                    <td>${cli.telefone}<input id="id" type="hidden" name="telefone" value="${cli.telefone}"></td>
                    <td>${cli.assunto}<input id="id" type="hidden" name="assunto" value="${cli.assunto}"></td>
                    <td>${cli.categoria}<input id="id" type="hidden" name="categoria" value="${cli.categoria}"></td>
                    <td>${cli.mensagem}<input id="id" type="hidden" name="mensagem" value="${cli.mensagem}"></td>
                    <td><p><a href="Controle?id=${cli.id}&flag=excluircliente" type="hidden"><button>Excluir</button></a>
                            <a href="Controle?id=${cli.id}&flag=altera" type="hidden"><button>Alterar</button></a></p></td>
                    </form>
                </tr>  
            </c:forEach>
        </table>
        <br/>
        <a href="index.html"><button>Inicio</button></a>
        <a href="consulta_reclama.jsp"><button>Voltar</button></a>
    </body>
</html>

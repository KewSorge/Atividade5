/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDao;

/**
 *
 * @author Kewen Sorge
 */
@WebServlet(name = "Controle", urlPatterns = {"/Controle"})
public class Controle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagemFinal;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nome, email, telefone, assunto, categoria, mensagem;
        int id;

        String flag = request.getParameter("flag");

        if (flag.equalsIgnoreCase("salvarcli")) {
            nome = request.getParameter("nome");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            assunto = request.getParameter("assunto");
            categoria = request.getParameter("categoria");
            mensagem = request.getParameter("mensagem");

            Cliente cli = new Cliente();

            cli.setNome(nome);
            cli.setEmail(email);
            cli.setTelefone(telefone);
            cli.setAssunto(assunto);
            cli.setCategoria(categoria);
            cli.setMensagem(mensagem);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagemFinal = "Erro ao se conectar ao banco de dados";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.salvarCliente(cli);
                if (r == 1) {
                    mensagemFinal = "Cliente Cadastrado!!!";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("cadastroSucesso.jsp");
                    d.forward(request, response);
                } else {
                    mensagemFinal = "Ocorreu algum erro!!!";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
            }
        } else if (flag.equalsIgnoreCase("consultarclientenome")) {
            nome = request.getParameter("nome");
            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();
            if (r == 0) {
                mensagemFinal = "erro na conexão com o bd";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                ArrayList lista = dao.consultarClienteNome(nome);
                if (!lista.isEmpty()) {
                    request.setAttribute("lista", lista);
                    RequestDispatcher d = request.getRequestDispatcher("exibir_cliente.jsp");
                    d.forward(request, response);
                    return;
                } else {
                    mensagemFinal = "Usuário não encontrado!";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                    d.forward(request, response);
                }
                dao.desconectar();
            }
        } else if (flag.equalsIgnoreCase("consultartodosclientes")) {
            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();
            if (r == 0) {
                mensagemFinal = "erro na conexão com o bd";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                ArrayList lista = dao.consultarTodosCliente();
                if (!lista.isEmpty()) {
                    request.setAttribute("lista", lista);
                    RequestDispatcher d = request.getRequestDispatcher("exibir_cliente.jsp");
                    d.forward(request, response);
                    return;
                } else {
                    mensagemFinal = "Usuário não encontrado!";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
                dao.desconectar();
            }
        } else if (flag.equalsIgnoreCase("altera")) {
            id = Integer.parseInt(request.getParameter("id"));

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();
            if (r == 0) {
                mensagemFinal = "erro na conexão com o bd";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                ArrayList lista = dao.consultarClienteID(id);
                if (!lista.isEmpty()) {
                    request.setAttribute("lista", lista);
                    RequestDispatcher d = request.getRequestDispatcher("confirma_alteracao.jsp");
                    d.forward(request, response);
                    return;
                } else {
                    mensagemFinal = "Usuário não encontrado!";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                    d.forward(request, response);
                }
            }
        } else if (flag.equalsIgnoreCase("alterarcliente")) {
            id = Integer.parseInt(request.getParameter("id"));
            nome = request.getParameter("nome");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            assunto = request.getParameter("assunto");
            categoria = request.getParameter("categoria");
            mensagem = request.getParameter("mensagem");

            Cliente cli = new Cliente();
            cli.setId(id);
            cli.setNome(nome);
            cli.setEmail(email);
            cli.setTelefone(telefone);
            cli.setAssunto(assunto);
            cli.setCategoria(categoria);
            cli.setMensagem(mensagem);

            ArrayList alter = new ArrayList();
            alter.add(cli);

            if (!alter.isEmpty()) {
                request.setAttribute("lista", alter);
                RequestDispatcher d = request.getRequestDispatcher("confirma_alteracao.jsp");
                d.forward(request, response);
            } else {
                mensagemFinal = "Ocorreu um erro";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            }
        } else if (flag.equalsIgnoreCase("alteracaoconfirmada")) {
            id = Integer.parseInt(request.getParameter("id"));
            nome = request.getParameter("nome");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            assunto = request.getParameter("assunto");
            categoria = request.getParameter("categoria");
            mensagem = request.getParameter("mensagem");

            Cliente cli = new Cliente();
            cli.setId(id);
            cli.setNome(nome);
            cli.setEmail(email);
            cli.setTelefone(telefone);
            cli.setAssunto(assunto);
            cli.setCategoria(categoria);
            cli.setMensagem(mensagem);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagemFinal = "Erro ao conectar ao banco!";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.alterarCliente(cli);
                if (r == 1) {
                    mensagemFinal = "Cliente alterado";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("cadastroSucesso.jsp");
                    d.forward(request, response);
                } else {
                    mensagemFinal = "ocorreu algum erro";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
            }
        } else if (flag.equalsIgnoreCase("excluircliente")) {
            id = Integer.parseInt(request.getParameter("id"));

            Cliente cli = new Cliente();
            cli.setId(id);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagemFinal = "Erro ao se conectar no banco";
                request.setAttribute("mensagem", mensagemFinal);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.excluiCliente(cli);
                if (r == 1) {
                    mensagemFinal = "Cliente Excluido";
                    request.setAttribute("mensagem", mensagemFinal);
                    RequestDispatcher d = request.getRequestDispatcher("cadastroSucesso.jsp");
                    d.forward(request, response);
                }
            }
        } else {
            mensagemFinal = "Erro na flag!!!";
            request.setAttribute("mensagem", mensagemFinal);
            RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
            d.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otavio
 */
public class ClienteDao {

    private Connection conexao;
    private PreparedStatement st;

    public int conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dados", "root", "");
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            return 0;
        }
    }

    public void desconectar() {
        try {
            conexao.close();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int salvarCliente(Cliente cli) {
        try {
            String comando = "INSERT INTO `dados`.`reclama` (nome,email, telefone,assunto,categoria,mensagem) VALUES (?, ?, ?, ?,?,?)";
            st = (PreparedStatement) this.conexao.prepareStatement(comando);
            st.setString(1, cli.getNome());
            st.setString(2, cli.getEmail());
            st.setString(3, cli.getTelefone());
            st.setString(4, cli.getAssunto());
            st.setString(5, cli.getCategoria());
            st.setString(6, cli.getMensagem());
            st.execute();

            return 1;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                return 0;
            } else {
                return 2;
            }
        }
    }

    public ArrayList consultarClienteNome(String nome) {
        try {
            String sql = "SELECT * FROM reclama WHERE NOME LIKE ?";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = (PreparedStatement) conexao.prepareStatement(sql);
            st.setString(1, "%" + nome + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setAssunto(rs.getString("assunto"));
                cli.setCategoria(rs.getString("categoria"));
                cli.setMensagem(rs.getString("Mensagem"));

                lista.add(cli);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
public ArrayList consultarClienteID(int id) {
        try {
            String sql = "SELECT * FROM reclama WHERE id LIKE ?";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = (PreparedStatement) conexao.prepareStatement(sql);
            st.setString(1, "%" + id + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setAssunto(rs.getString("assunto"));
                cli.setCategoria(rs.getString("categoria"));
                cli.setMensagem(rs.getString("Mensagem"));

                lista.add(cli);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    public ArrayList consultarTodosCliente() {
        try {
            String sql = "SELECT * FROM reclama";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setAssunto(rs.getString("assunto"));
                cli.setCategoria(rs.getString("categoria"));
                cli.setMensagem(rs.getString("Mensagem"));

                lista.add(cli);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

    public int alterarCliente(Cliente cli) {
        try {
            String comando = "UPDATE reclama set nome  = ?,email = ?,telefone = ?,assunto =?,categoria = ?,mensagem = ? WHERE id = ?";
            st = (PreparedStatement) this.conexao.prepareStatement(comando);

            st.setString(1, cli.getNome());
            st.setString(2, cli.getEmail());
            st.setString(3, cli.getTelefone());
            st.setString(4, cli.getAssunto());
            st.setString(5, cli.getCategoria());
            st.setString(6, cli.getMensagem());
            st.setInt(7, cli.getId());
            st.execute();
            return 1;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    public int excluiCliente(Cliente cli) {
        try {
            String comando = "DELETE FROM `dados`.`reclama` WHERE id = ? ";
            st = (PreparedStatement) conexao.prepareStatement(comando);
            st.setInt(1, cli.getId());
            st.execute();
            return 1;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                return 2;
            } else {
                return 0;
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Cliente;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class ClienteDao {

    Connection connection;

    public ClienteDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }
    
    public boolean verificaUsuario(String cpf) throws Exception {
        String SQL = "SELECT CLIENTE.NOME FROM CLIENTE WHERE CLIENTE.CPF = ?";
        try {
            Cliente cliente = new Cliente();
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                cliente.setNome(rs.getString("NOME"));
                return false;
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return true;
    }

    public String save(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha) throws Exception {

        String SQL = "INSERT INTO CLIENTE(NOME, DATA_NASCIMENTO, CPF, CEP, ENDERECO, NUMERO, COMPLEMENTO,"
                + " BAIRRO, ESTADO, CIDADE, TELEFONE, EMAIL, SENHA, TIPO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (verificaUsuario(cpf)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(data);
                PreparedStatement p = connection.prepareStatement(SQL);
                p.setString(1, nome);
                p.setDate(2, new java.sql.Date (date.getTime()));
                p.setString(3, cpf);
                p.setString(4, cep);
                p.setString(5, endereco);
                p.setInt(6, numero);
                p.setString(7, complemento);
                p.setString(8, bairro);
                p.setString(9, estado);
                p.setString(10, cidade);
                p.setString(11, telefone);
                p.setString(12, email);
                p.setString(13, senha);
                p.setString(14, "C");
                p.execute();
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
            return "Cadastrado com sucesso!";
        }
        return "Já possui cliente cadastrado com esse cpf!";
    }

    public Cliente login(String email, String senha) throws SQLException, Exception {
        Cliente cliente = new Cliente();
        try {
            String SQL = "SELECT * "
                    + " FROM CLIENTE "
                    + " WHERE CLIENTE.EMAIL = ?"
                    + " AND CLIENTE.SENHA = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, email);
            p.setString(2, senha);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                cliente.setCcliente(rs.getInt("CCLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setData_nascimento(rs.getString("DATA_NASCIMENTO"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setComplemento(rs.getString("COMPLEMENTO"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setEstado(rs.getString("ESTADO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setSenha(rs.getString("SENHA"));
                cliente.setTipo(rs.getString("TIPO"));
                cliente.setLogado(true);
                return cliente;
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return null;
    }
}

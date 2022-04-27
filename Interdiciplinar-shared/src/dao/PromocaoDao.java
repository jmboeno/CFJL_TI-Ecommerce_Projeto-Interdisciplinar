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
import java.util.Date;
import model.Promocao;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class PromocaoDao {

    Connection connection;

    public PromocaoDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Promocao fiendById(int id) throws Exception {
        try {
            Promocao promocao = new Promocao();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM PROMOCAO WHERE CPROMOCAO=?");
            p.setInt(1, promocao.getCpromocao());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                promocao.setCpromocao(rs.getInt("CPROMOCAO"));
                promocao.setData_inicial(rs.getString("DATA_INICIAL"));
                promocao.setData_final(rs.getString("DATA_FINAL"));
                promocao.setQtde(rs.getInt("QTDE"));
                promocao.setCproduto(rs.getInt("CPRODUTO"));
            }
            return promocao;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }

    public boolean save(int cpromocao, Date data_inicial, Date data_final, int qtde, int cproduto) throws Exception {

        String SQL = "INSERT INTO PROMOCAO(CPROMOCAO, DATA_INICIAL, DATA_FINAL, QTDE, CPRODUTO) "
                + " VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpromocao);
            //p.setString(2, new java.sql.Date(data_inicial.getTime()));
            //p.setString(3, new java.sql.Date(data_final.getTime()));
            p.setInt(4, qtde);
            p.setInt(5, cproduto);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return true;
    }

    public boolean delete(int cpromocao) throws SQLException {
        String SQL = "DELETE * FROM PROMOCAO WHERE CPROMOCAO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cpromocao);
        p.execute();
        return true;
    }

    public boolean update(Promocao promocao) throws Exception {
        if (promocao != null && !promocao.equals("")) {
            String SQL = "UPDATE PROMOCAO SET DATA_INICIAL=?, DATA_FINAL=?, QTDE=?, WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, promocao.getCpromocao());
            p.setString(2, promocao.getData_inicial());
            p.setString(3, promocao.getData_final());
            p.setInt(4, promocao.getQtde());
            p.setInt(5, promocao.getCproduto());
            return true;
        }
        return false;
    }
}

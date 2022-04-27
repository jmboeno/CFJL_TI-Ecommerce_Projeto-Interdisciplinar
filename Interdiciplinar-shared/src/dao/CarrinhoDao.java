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
import model.Categoria;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CarrinhoDao {

    Connection connection;

    public CarrinhoDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public boolean possuiCarrinho(int ccliente) throws SQLException {
        String SQL = "SELECT * FROM CARRINHO WHERE CARRINHO.CCLIENTE = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public int retornaCodCarrinho(int ccliente) throws SQLException{
        String SQL = "SELECT CCARRINHO FROM CARRINHO WHERE CCLIENTE = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt("CCARRINHO");
        }
        return 0;
    }
    
    public String save(int ccliente) throws SQLException {
            String SQL = "INSERT INTO CARRINHO(CCLIENTE) "
                    + " VALUES(?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccliente);
            p.execute();
            
            retornaCodCarrinho(ccliente);
        return "Carrinho salvo com sucesso.";
    }
}

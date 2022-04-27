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
public class CategoriaDao {

    Connection connection;

    public CategoriaDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Categoria fiendById(int id) throws Exception {
        try {
            Categoria categoria = new Categoria();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM CATEGORIA WHERE CCATEGORIA=?");
            p.setInt(1, categoria.getCcategoria());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                categoria.setCcategoria(rs.getInt("CCATEGORIA"));
                categoria.setDescricao(rs.getString("DESCRICAO"));
            }
            return categoria;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }
    

    public String save(String descricao) throws SQLException {

        String SQL = "INSERT INTO CATEGORIA(DESCRICAO) "
                + " VALUES(?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setString(1, descricao);
        p.execute();
        return "Categoria salva com sucesso.";
    }

    public boolean delete(int ccategoria) throws SQLException {
        String SQL = "DELETE * FROM MARCA WHERE MARCA.CMARCA = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccategoria);
        p.execute();
        return true;
    }

    public boolean update(int ccategoria) throws Exception {
        Categoria categoria = new Categoria();
        categoria = fiendById(ccategoria);
        if (categoria != null && !categoria.equals("")) {
            String SQL = "UPDATE CATEGORIA SET DESCRICAO=? WHERE CCATEGORIA=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, categoria.getCcategoria());
            p.setString(2, categoria.getDescricao());
            return true;
        }
        return false;
    }
}

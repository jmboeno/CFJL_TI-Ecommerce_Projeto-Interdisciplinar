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
import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Produto;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class MarcaDao {

    Connection connection;

    public MarcaDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Marca fiendById(int id) throws Exception {
        try {
            Marca marca = new Marca();
            PreparedStatement p = connection.prepareStatement("SELECT * FROM MARCA WHERE CMARCA=?");
            p.setInt(1, marca.getCmarca());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                marca.setCmarca(rs.getInt("CMARCA"));
                marca.setDescricao(rs.getString("DESCRICAO"));
            }
            return marca;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Contatar Suporte.", ex);
        }
    }

    public String save(String descricao) throws Exception {

        String SQL = "INSERT INTO MARCA(DESCRICAO) "
                + " VALUES(?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, descricao);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return "Marca cadastrada com sucesso!";
    }

    public boolean delete(int cmarca) throws SQLException {
        String SQL = "DELETE * FROM MARCA WHERE MARCA.CMARCA = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cmarca);
        p.execute();
        return true;
    }

    public boolean update(int cmarca, String descricao) throws Exception {
        Marca marca = new Marca();
        marca = fiendById(cmarca);
        if (marca != null && !marca.equals("")) {
            String SQL = "UPDATE MARCA SET DESCRICAO=? WHERE CMARCA=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, marca.getCmarca());
            p.setString(2, marca.getDescricao());
            p.execute();
            return true;
        }
        return false;
    }
    
    public List<Marca> getLista() throws Exception {
        List<Marca> list = new ArrayList<>();
        Marca objeto;
        String SQL = " SELECT FIRST 12 * "
                + " FROM PRODUTO "
                + " WHERE PRODUTO.QTDE > 0"
                + " ORDER BY CPRODUTO DESC";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Marca();
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));

                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }
}


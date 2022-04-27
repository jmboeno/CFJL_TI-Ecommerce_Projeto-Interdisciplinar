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
import model.Produto;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class ProdutoDao {

    Connection connection;

    public ProdutoDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }
    
    public List<Produto> getListaClassificacao(int ccategoria, String descricao) throws Exception{
        descricao = "'%"+descricao.toLowerCase()+"%'";
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        
        String SQL = " SELECT * FROM PRODUTO  WHERE PRODUTO.QTDE > 0 AND PRODUTO.CCATEGORIA = ?"
                + " AND LOWER(PRODUTO.DESCRICAO) LIKE "+descricao;
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccategoria);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro("DATA_CADASTRO");

                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }

    public List<Produto> getListaSearch(String pesquisa) throws Exception {
        pesquisa = "'%"+pesquisa.toLowerCase()+"%'";
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        
        String SQL = " SELECT * "
                + " FROM PRODUTO "
                + " WHERE PRODUTO.QTDE > 0"
                + " AND LOWER(PRODUTO.DESCRICAO) LIKE "+pesquisa;
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro("DATA_CADASTRO");

                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }

    public Produto retornaDadosProduto(int cproduto) throws SQLException, Exception{
        Produto objeto = null;
        String SQL = "SELECT * FROM PRODUTO WHERE CPRODUTO = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            ResultSet rs = p.executeQuery();
            if(rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro(rs.getString("DATA_CADASTRO"));
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return objeto;
    }
    
    public List<Produto> getListaProdutos() throws Exception {
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = " SELECT FIRST 12 * "
                + " FROM PRODUTO "
                + " WHERE PRODUTO.QTDE > 0"
                + " ORDER BY CPRODUTO DESC";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Produto();
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setCcategoria(rs.getInt("CCATEGORIA"));
                objeto.setCmarca(rs.getInt("CMARCA"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setPreco_unitario(rs.getDouble("PRECO_UNITARIO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setImagem(rs.getString("IMAGEM"));
                objeto.setDataCadastro(rs.getString("DATA_CADASTRO"));

                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }

    public String save(int marca, int categoria, String descricao, double precoUnitario, int qtde, String imagem) throws Exception {

        String SQL = "INSERT INTO PRODUTO(CMARCA, CCATEGORIA, DESCRICAO, PRECO_UNITARIO, QTDE, IMAGEM, DATA_CADASTRO) "
                + " VALUES(?, ?, ?, ?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, marca);
            p.setInt(2, categoria);
            p.setString(3, descricao);
            p.setDouble(4, precoUnitario);
            p.setInt(5, qtde);
            p.setString(6, imagem);
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date(d.getTime());
            p.setDate(7, dt);
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return "Produto cadastrado com sucesso.";
    }
}

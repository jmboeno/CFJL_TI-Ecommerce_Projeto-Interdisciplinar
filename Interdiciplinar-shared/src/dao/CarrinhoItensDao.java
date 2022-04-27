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
public class CarrinhoItensDao {

    Connection connection;

    public CarrinhoItensDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public String salvaProdutoCarrinho(int ccarrinho, int cproduto) throws SQLException {
        if (!isProdInCarrinho(ccarrinho, cproduto)) {
            String SQL = "INSERT INTO CARRINHOITENS(CCARRINHO, CPRODUTO) "
                    + " VALUES(?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccarrinho);
            p.setInt(2, cproduto);
            p.execute();
            return "Produto Salvo no carrinho com sucesso.";
        }
        return "Produto já foi adicionado ao carrinho.";
    }
    
    public String excluirCarrinhoItem(int ccarrinho, int cproduto) throws SQLException{
        String SQL = "DELETE FROM CARRINHOITENS WHERE CCARRINHO = ? AND CPRODUTO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccarrinho);
        p.setInt(2, cproduto);
        p.execute();
        return "Item deletado com sucesso.";
    }

    public boolean isProdInCarrinho(int ccarrinho, int cproduto) throws SQLException {
        String SQL = "SELECT * "
                + " FROM CARRINHOITENS"
                + " WHERE CARRINHOITENS.CCARRINHO = ?"
                + " AND CARRINHOITENS.CPRODUTO = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccarrinho);
        p.setInt(2, cproduto);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public int retornaQtdeCarrinho(int ccliente) throws SQLException {
        String SQL = " SELECT COUNT(*) AS QTDE"
                + " FROM CARRINHOITENS"
                + " INNER JOIN CARRINHO ON (CARRINHO.CCARRINHO = CARRINHOITENS.CCARRINHO)"
                + " WHERE CARRINHO.CCLIENTE = ?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        ResultSet rs = p.executeQuery();
        if(rs.next()) {
            return rs.getInt("QTDE");
        }
        return 0;
    }

    public List<Produto> getListaItensCarrinho(int ccarrinho) throws Exception {
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = " SELECT * "
                + " FROM PRODUTO"
                + " INNER JOIN CARRINHOITENS ON (CARRINHOITENS.CPRODUTO = PRODUTO.CPRODUTO)"
                + " WHERE CARRINHOITENS.CCARRINHO = ?"
                + " AND PRODUTO.QTDE > 0";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccarrinho);
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
}

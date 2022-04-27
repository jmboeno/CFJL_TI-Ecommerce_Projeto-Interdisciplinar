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
import model.Compra;
import model.CompraItens;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CompraItensDao {

    Connection connection;

    public CompraItensDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public String salvaItensCompra(int cproduto, int ccompra, int qtde, double valorUnitario) throws SQLException {
        String SQL = "INSERT INTO COMPRAITENS(CPRODUTO, CCOMPRA, QTDE, VALORUNITARIO, TOTAL)"
                + " VALUES(?, ?, ?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, cproduto);
        p.setInt(2, ccompra);
        p.setInt(3, qtde);
        p.setDouble(4, valorUnitario);
        p.setDouble(5, (valorUnitario * qtde));
        p.execute();
        return "Compra efetuada com sucesso.";
    }

    public void atualizaValorProduto(int qtde, int cproduto) throws SQLException {
        String SQL = "UPDATE PRODUTO SET QTDE=? WHERE CPRODUTO=?";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, qtde);
        p.setInt(2, cproduto);
        p.execute();
    }

    public List<CompraItens> retornaCompras(String condicao) throws Exception {
        List<CompraItens> list = new ArrayList<>();
        CompraItens objeto;
        String SQL;
        if (!condicao.equals("T")) {
            SQL = " SELECT COMPRAITENS.*,"
                    + " COMPRA.CCLIENTE, COMPRA.DATA, COMPRA.STATUS"
                    + " FROM COMPRAITENS "
                    + " INNER JOIN COMPRA ON (COMPRA.CCOMPRA = COMPRAITENS.CCOMPRA)"
                    + " WHERE COMPRA.STATUS = '" + condicao + "'";
        }else{
            SQL = " SELECT COMPRAITENS.*,"
                    + " COMPRA.CCLIENTE, COMPRA.DATA, COMPRA.STATUS"
                    + " FROM COMPRAITENS "
                    + " INNER JOIN COMPRA ON (COMPRA.CCOMPRA = COMPRAITENS.CCOMPRA)";
        }
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new CompraItens();
                objeto.setCcompra(rs.getInt("CCOMPRA"));
                objeto.setCcompraItens(rs.getInt("CCOMPRAITENS"));
                objeto.setCproduto(rs.getInt("CPRODUTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setValorUnitario(rs.getDouble("VALORUNITARIO"));
                objeto.setCcliente(rs.getInt("CCLIENTE"));
                objeto.setData(rs.getString("DATA"));
                objeto.setTotal(rs.getDouble("TOTAL"));
                objeto.setStatus(rs.getString("STATUS"));
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

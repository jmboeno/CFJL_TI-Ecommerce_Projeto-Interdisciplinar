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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Compra;
import util.ConnectionUtil;

/**
 *
 * @author User
 */
public class CompraDao {

    Connection connection;

    public CompraDao() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public boolean efetuaCompra(int ccliente) throws SQLException, ParseException {
        String SQL = "INSERT INTO COMPRA(CCLIENTE, DATA, STATUS) "
                + " VALUES(?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        java.util.Date d = new java.util.Date();
        java.sql.Date dt = new java.sql.Date(d.getTime());
        p.setDate(2, dt);
        p.setString(3, "P");
        p.execute();
        return true;
    }
    public int retornaCCompra(int ccliente) throws SQLException{
        String SQL = "SELECT FIRST 1 COMPRA.CCOMPRA"
                + " FROM COMPRA"
                + " WHERE COMPRA.CCLIENTE = ?"
                + " ORDER BY COMPRA.CCOMPRA DESC";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, ccliente);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt("CCOMPRA");
        }
        return 0;
    }
    
    public List<Compra> retornaComprasCliente(int ccliente) throws Exception {
        List<Compra> list = new ArrayList<>();
        Compra objeto;
        String SQL = " SELECT * "
                + " FROM COMPRA "
                + " WHERE COMPRA.STATUS = 'A'"
                + " AND COMPRA.CCLIENTE = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccliente);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Compra();
                objeto.setCcompra(rs.getInt("CCOMPRA"));
                objeto.setCcliente(rs.getInt("CCLIENTE"));
                objeto.setData(rs.getString("DATA"));
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

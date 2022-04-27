/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Remote;
import model.Compra;

/**
 *
 * @author User
 */
@Remote
public interface CompraBeanRemote{
    public boolean efetuaCompra(int ccliente) throws SQLException, ParseException;
    public int retornaCCompra(int ccliente) throws SQLException;
    public List<Compra> retornaComprasCliente(int ccliente) throws Exception;
}

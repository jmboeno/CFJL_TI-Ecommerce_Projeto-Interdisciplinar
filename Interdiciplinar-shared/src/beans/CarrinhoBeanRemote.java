/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import javax.ejb.Remote;

/**
 *
 * @author User
 */
@Remote
public interface CarrinhoBeanRemote {
    public String cadastraCarrinho(int ccliente);
    public boolean possuiCarrinho(int ccliente) throws SQLException;
    public int retornaCodCarrinho(int ccliente) throws SQLException;
}

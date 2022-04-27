/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Remote;
import model.Marca;

/**
 *
 * @author User
 */
@Remote
public interface MarcaBeanRemote {
    public String cadastraMarca(String descricao);
    public List<Marca> getLista() throws Exception;
}

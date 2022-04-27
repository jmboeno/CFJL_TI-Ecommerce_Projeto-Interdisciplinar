/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.MarcaDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Marca;

/**
 *
 * @author User
 */
@Stateless
public class MarcaBean implements MarcaBeanRemote{
    
    private final MarcaDao marcaDao;

    public MarcaBean() throws Exception {
        this.marcaDao = new MarcaDao();
    }

    @Override
    public String cadastraMarca(String descricao){
        try {
            return marcaDao.save(descricao);
        } catch (Exception ex) {
            Logger.getLogger(MarcaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    public boolean deletarMarca(int cmarca){
        try{
            return marcaDao.delete(cmarca);
        }catch(Exception ex){
            Logger.getLogger(MarcaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Marca fiendById(int cmarca){
        try{
            return marcaDao.fiendById(cmarca);
        }catch(Exception ex){
            Logger.getLogger(MarcaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Marca> getLista() throws Exception {
        return marcaDao.getLista();
    }
}

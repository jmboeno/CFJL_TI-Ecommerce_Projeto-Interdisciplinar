/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PromocaoDao;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Promocao;

/**
 *
 * @author User
 */
@Stateless
public class PromocaoBean {
    private final PromocaoDao promocaoDao;

    public PromocaoBean() throws Exception {
        this.promocaoDao = new PromocaoDao();
    }

    //@Override
    public boolean cadastraPromocao(int cpromocao, Date data_inicial, Date data_final, int qtde, int cproduto){
        try {
            return promocaoDao.save(cpromocao, data_inicial, data_final, qtde, cproduto);
        } catch (Exception ex) {
            Logger.getLogger(PromocaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deletarPromocao(int cpromocao){
        try{
            return promocaoDao.delete(cpromocao);
        }catch(Exception ex){
            Logger.getLogger(PromocaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updatePromocao(Promocao promocao){
        try{
            return promocaoDao.update(promocao);
        }catch(Exception ex){
            Logger.getLogger(PromocaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Promocao fiendById(int cpromocao){
        try{
            return promocaoDao.fiendById(cpromocao);
        }catch(Exception ex){
            Logger.getLogger(PromocaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

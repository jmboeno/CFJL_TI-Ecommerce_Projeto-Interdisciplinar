/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CategoriaDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Categoria;

/**
 *
 * @author User
 */
@Stateless
public class CategoriaBean implements CategoriaBeanRemote{
    
    private final CategoriaDao categoriaDao;

    public CategoriaBean() throws Exception {
        this.categoriaDao = new CategoriaDao();
    }

    @Override
    public String cadastraCategoria(String descricao){
        try {
            return categoriaDao.save(descricao);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    public boolean deletarCategoria(int ccategoria){
        try{
            return categoriaDao.delete(ccategoria);
        }catch(Exception ex){
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//    public boolean updateCategoria(Categoria categoria){
//        try{
//            return categoriaDao.update(categoria);
//        }catch(Exception ex){
//            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public Categoria fiendById(int ccategoria){
        try{
            return categoriaDao.fiendById(ccategoria);
        }catch(Exception ex){
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

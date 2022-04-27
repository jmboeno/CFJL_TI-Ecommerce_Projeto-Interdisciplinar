/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CarrinhoItensDao;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import model.Produto;

/**
 *
 * @author User
 */
@Stateless
public class CarrinhoItensBean implements CarrinhoItensBeanRemote{
   
    private final CarrinhoItensDao carrinhoItensDao;

    public CarrinhoItensBean() throws Exception {
        this.carrinhoItensDao = new CarrinhoItensDao();
    }
    
    @Override
    public String salvaProdutoCarrinho(int ccarinho, int cproduto) throws SQLException{
        return carrinhoItensDao.salvaProdutoCarrinho(ccarinho, cproduto);
    }
    
    @Override
    public List<Produto> getListaItensCarrinho(int ccarrinho) throws Exception {
        return carrinhoItensDao.getListaItensCarrinho(ccarrinho);
    }
   
    @Override
    public int retornaQtdeCarrinho(int ccliente) throws SQLException {
        return carrinhoItensDao.retornaQtdeCarrinho(ccliente);
    }
    
    @Override
    public String excluirCarrinhoItem(int ccarrinho, int cproduto) throws SQLException{
        return carrinhoItensDao.excluirCarrinhoItem(ccarrinho, cproduto);
    }
}

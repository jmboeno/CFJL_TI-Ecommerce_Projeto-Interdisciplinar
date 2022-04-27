/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CompraItensDao;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import model.CompraItens;

/**
 *
 * @author User
 */
@Stateless
public class CompraItensBean implements CompraItensBeanRemote{
    private final CompraItensDao compraItensDao;

    public CompraItensBean() throws Exception {
        this.compraItensDao = new CompraItensDao();
    }
    
    @Override
    public String salvaItensCompra(int cproduto, int ccompra, int qtde, double valorUnitario) throws SQLException {
        return compraItensDao.salvaItensCompra(cproduto, ccompra, qtde, valorUnitario);
    }
    
    @Override
    public void atualizaValorProduto(int qtde, int cproduto) throws SQLException{
         compraItensDao.atualizaValorProduto(qtde, cproduto);
    }
    
    @Override
    public List<CompraItens> retornaCompras(String condicao) throws Exception {
        return compraItensDao.retornaCompras(condicao);
    }
}

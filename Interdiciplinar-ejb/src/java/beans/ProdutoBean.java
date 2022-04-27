/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdutoDao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Produto;

/**
 *
 * @author User
 */
@Stateless
public class ProdutoBean implements ProdutoBeanRemote {

    private final ProdutoDao produtoDao;

    public ProdutoBean() throws Exception {
        this.produtoDao = new ProdutoDao();
    }

    @Override
    public String cadastraProduto(int marca, int categoria, String descricao, double precoUnitario, int qtde,
            String imagem) {
        try {
            return produtoDao.save(marca, categoria, descricao, precoUnitario, qtde, imagem);
        } catch (Exception ex) {
            Logger.getLogger(beans.ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }

    @Override
    public List<Produto> getListaProdutos() throws Exception {
        return produtoDao.getListaProdutos();
    }
       
    @Override
    public List<Produto> getListaSearch(String pesquisa) throws Exception {
        return produtoDao.getListaSearch(pesquisa);
    }
    
    @Override
    public List<Produto> getListaClassificacao(int ccategoria, String descricao) throws Exception{
        return produtoDao.getListaClassificacao(ccategoria, descricao);
    }
    
    @Override
    public Produto retornaDadosProduto(int cproduto) throws SQLException, Exception{
        return produtoDao.retornaDadosProduto(cproduto);
    }
}

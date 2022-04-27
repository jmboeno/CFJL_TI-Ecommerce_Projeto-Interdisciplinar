/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import model.Produto;

/**
 *
 * @author User
 */
@Remote
public interface CarrinhoItensBeanRemote {
    public String salvaProdutoCarrinho(int ccarinho, int cproduto) throws SQLException;
    public List<Produto> getListaItensCarrinho(int ccarrinho) throws Exception;
    public int retornaQtdeCarrinho(int ccliente) throws SQLException;
    public String excluirCarrinhoItem(int ccarrinho, int cproduto) throws SQLException;
}

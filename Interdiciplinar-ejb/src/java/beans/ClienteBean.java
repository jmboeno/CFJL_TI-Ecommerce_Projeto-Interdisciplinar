/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Cliente;

@Stateless
public class ClienteBean implements ClienteBeanRemote{

    private final ClienteDao clienteDao;

    public ClienteBean() throws Exception {
        this.clienteDao = new ClienteDao();
    }

    @Override
    public String cadastraCliente(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha){
        try {
            return clienteDao.save(numero, nome, data, cpf, cep, endereco, 
             complemento, bairro, estado, cidade, telefone, email, senha);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Não foi possivel efetuar o cadastro, contatar suporte!";
    }
    
    @Override
    public Cliente logarCliente(String email, String senha) throws Exception{
        return clienteDao.login(email, senha);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import exceptions.AppException;
import javax.ejb.Remote;
import model.Cliente;
/**
 *
 * @author User
 */
@Remote
public interface ClienteBeanRemote {
    public String cadastraCliente(int numero, String nome, String data, String cpf, String cep, String endereco, 
            String complemento, String bairro, String estado, 
                    String cidade, String telefone, String email, String senha)throws AppException;
    public Cliente logarCliente(String email, String senha)throws Exception;
}

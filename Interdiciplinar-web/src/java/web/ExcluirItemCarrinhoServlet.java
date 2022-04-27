/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.CarrinhoBeanRemote;
import beans.CarrinhoItensBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author User
 */
public class ExcluirItemCarrinhoServlet extends HttpServlet {
    @EJB
    private CarrinhoItensBeanRemote bean;
    @EJB
    private CarrinhoBeanRemote beanCarrinho;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        JsonObject retorno = null, json;
        String dados = null, content;
        int ccliente, cproduto;
        boolean saberQtde;
        
        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));

        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject dado = reader.readObject();
        
        ccliente = dado.getJsonNumber("ccliente").intValue();
        cproduto = dado.getJsonNumber("cproduto").intValue();

        try {
            dados = bean.excluirCarrinhoItem(beanCarrinho.retornaCodCarrinho(ccliente), cproduto);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            retorno = Json.createObjectBuilder()
                    .add("mensagem", dados).build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saida.write(retorno.toString());
    }
}

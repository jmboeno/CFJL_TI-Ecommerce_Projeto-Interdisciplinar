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
import java.sql.SQLException;
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
public class CarrinhoServlet extends HttpServlet {

    @EJB
    private CarrinhoBeanRemote beanCarrinho;
    @EJB
    private CarrinhoItensBeanRemote beanCarrinhoItens;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String content, retorno = "Não foi possivel salvar o produto no carrinho.";
        int cproduto, ccliente;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));

        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject dados = reader.readObject();

        cproduto = dados.getJsonNumber("cproduto").intValue();
        ccliente = dados.getJsonNumber("ccliente").intValue();

        try {
            if (beanCarrinho.possuiCarrinho(ccliente)) {
                retorno = beanCarrinhoItens.salvaProdutoCarrinho(beanCarrinho.retornaCodCarrinho(ccliente), cproduto);
            } else {
                beanCarrinho.cadastraCarrinho(ccliente);
                retorno = beanCarrinhoItens.salvaProdutoCarrinho(beanCarrinho.retornaCodCarrinho(ccliente), cproduto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarrinhoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonObject json = Json.createObjectBuilder()
                .add("mensagem", retorno)
                .build();

        saida.write(json.toString());
    }
}

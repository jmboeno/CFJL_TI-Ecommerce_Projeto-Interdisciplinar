/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
import beans.ProdutoBeanRemote;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ProdutoServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String descricao, imagem, content;
        int marca, categoria, qtde;
        double precoUnitario;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        content = leitor.lines().collect(Collectors.joining());
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        marca = form.getJsonNumber("marca").intValue();
        categoria = form.getJsonNumber("categoria").intValue();
        descricao = form.getJsonString("descricao").getString();
        precoUnitario = form.getJsonNumber("precoUnitario").doubleValue();
        qtde = form.getJsonNumber("qtde").intValue();
        imagem = form.getJsonString("imagem").getString();

        String retorno;

        retorno = bean.cadastraProduto(marca, categoria, descricao, precoUnitario, qtde, imagem);

        JsonObject json = Json.createObjectBuilder()
                .add("mensagem", retorno)
                .build();

        saida.write(json.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        JsonObject retorno = null, json;
        String dados = null;

        try {
            for(Produto produto : bean.getListaProdutos()){
                if(dados != null){
                    dados += ",";
                }
                json = Json.createObjectBuilder()
                        .add("codigo", produto.getCproduto())
                        .add("cmarca", produto.getCmarca())
                        .add("ccategoria", produto.getCcategoria())
                        .add("nome", produto.getDescricao())
                        .add("imagem", produto.getImagem())
                        .add("valor", produto.getPreco_unitario())
                        .add("qtde", produto.getQtde())
                        .add("dataCadastro", produto.getDataCadastro())
                        .add("session", request.getSession().getId()).build();
                if(dados != null){
                    dados += json.toString();
                }else{
                    dados = json.toString();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            retorno = Json.createObjectBuilder()
                    .add("produtos", "["+dados+"]").build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno.toString());
    }
}

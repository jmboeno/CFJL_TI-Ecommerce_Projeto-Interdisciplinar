/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.ProdutoBeanRemote;
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
public class ClassificacaoServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String content, dados = null, descricao;
        JsonObject json, retorno = null;
        int ccategoria;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));

        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        descricao = form.getJsonString("descricao").getString();
        ccategoria = form.getJsonNumber("ccategoria").intValue();

        try {
            for (Produto produto : bean.getListaClassificacao(ccategoria, descricao)) {
                if (dados != null) {
                    dados += ",";
                }
                json = Json.createObjectBuilder()
                        .add("codigo", produto.getCproduto())
                        .add("cmarca", produto.getCmarca())
                        .add("ccategoria", produto.getCcategoria())
                        .add("nome", produto.getDescricao())
                        .add("imagem", produto.getImagem())
                        .add("valor", produto.getPreco_unitario())
                        .add("qtde", produto.getQtde()).build();
                if (dados != null) {
                    dados += json.toString();
                } else {
                    dados = json.toString();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            retorno = Json.createObjectBuilder()
                    .add("produtos", "[" + dados + "]").build();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno.toString());
    }
}

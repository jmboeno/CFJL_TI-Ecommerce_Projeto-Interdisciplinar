/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.MarcaBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Marca;
import model.Produto;

/**
 *
 * @author User
 */
public class MarcaServlet extends HttpServlet {

    @EJB
    private MarcaBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String descricao, content;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        descricao = form.getJsonString("descricao").getString();

        String retorno = "";

        retorno = bean.cadastraMarca(descricao);

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
            for(Marca marca : bean.getLista()){
                if(dados != null){
                    dados += ",";
                }
                json = Json.createObjectBuilder()
                        .add("codigo", marca.getCmarca())
                        .add("cmarca", marca.getDescricao()).build();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.BufferedReader;
import java.io.IOException;
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
import exceptions.AppException;
import java.io.InputStreamReader;
import beans.ClienteBeanRemote;

/**
 *
 * @author User
 */
public class ClienteServlet extends HttpServlet {

    @EJB
    private ClienteBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();
        
        String nome, data, cpf, cep, endereco, complemento, bairro, estado, cidade, telefone, email, senha, content;
        int numero;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));
        
        content = leitor.lines().collect(Collectors.joining());

        //System.out.println("Conteudo "+content);
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        nome = form.getJsonString("nome").getString();
        data = form.getJsonString("data_nascimento").getString();
        cpf = form.getJsonString("cpf").getString();
        cep = form.getJsonString("cep").getString();
        endereco = form.getJsonString("endereco").getString();
        numero = form.getJsonNumber("numero").intValue();
        complemento = form.getJsonString("complemento").getString();
        bairro = form.getJsonString("bairro").getString();
        estado = form.getJsonString("estado").getString();
        cidade = form.getJsonString("cidade").getString();
        telefone = form.getJsonString("telefone").getString();
        email = form.getJsonString("email").getString();
        senha = form.getJsonString("senha").getString();
       
        String retorno = "";
        try {
            retorno = bean.cadastraCliente(numero, nome, data, cpf, cep, endereco, complemento, bairro, estado, 
                    cidade, telefone, email, senha);
        } catch (AppException ex) {
            retorno = ex.getMessage();
        }

        JsonObject json = Json.createObjectBuilder()
                .add("mensagem", retorno)
                .build();

        saida.write(json.toString());
    }
}

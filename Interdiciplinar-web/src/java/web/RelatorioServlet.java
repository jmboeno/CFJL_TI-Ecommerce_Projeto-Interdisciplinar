/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.CompraItensBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
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
import model.CompraItens;

/**
 *
 * @author User
 */
public class RelatorioServlet extends HttpServlet {

    @EJB
    private CompraItensBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter saida = response.getWriter();

        String content, condicao, dados = null;
        JsonObject json, retorno = null;

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8"));

        content = leitor.lines().collect(Collectors.joining());

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject form = reader.readObject();

        condicao = form.getJsonString("condicao").getString();

        try {
            if (bean.retornaCompras(condicao).size() > 0) {
                
                try {
                    for (CompraItens compraItens : bean.retornaCompras(condicao)) {
                        if (dados != null) {
                            dados += ",";
                        }
                        json = Json.createObjectBuilder()
                                .add("ccompra", compraItens.getCcompra())
                                .add("ccompraItens", compraItens.getCcompraItens())
                                .add("qtde", compraItens.getQtde())
                                .add("cproduto", compraItens.getCproduto())
                                .add("unitario", compraItens.getValorUnitario())
                                .add("total", compraItens.getTotal())
                                .add("ccliente", compraItens.getCcliente())
                                .add("data", compraItens.getData())
                                .add("status", compraItens.getStatus()).build();
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
                            .add("compras", "[" + dados + "]").build();
                } catch (Exception ex) {
                    Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                retorno = Json.createObjectBuilder()
                            .add("compras", "nada").build();
            }
        } catch (Exception ex) {
            Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno.toString());
    }
}

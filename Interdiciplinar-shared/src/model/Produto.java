/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
/**
 *
 * @author User
 */
public class Produto implements Serializable{
    private int cproduto;
    private int cmarca;
    private int ccategoria;
    private String descricao, imagem, dataCadastro;
    private double preco_unitario;
    private int qtde;

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }

    public int getCmarca() {
        return cmarca;
    }

    public void setCmarca(int cmarca) {
        this.cmarca = cmarca;
    }

    public int getCcategoria() {
        return ccategoria;
    }

    public void setCcategoria(int ccategoria) {
        this.ccategoria = ccategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}

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
public class CompraItens implements Serializable{
    private int ccompraItens;
    private int ccliente;
    private int qtde;
    private int cproduto;
    private int ccompra;
    private double valorUnitario;
    private double total;
    private String data;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCcliente() {
        return ccliente;
    }

    public void setCcliente(int ccliente) {
        this.ccliente = ccliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public int getCcompraItens() {
        return ccompraItens;
    }

    public void setCcompraItens(int ccompraItens) {
        this.ccompraItens = ccompraItens;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }

    public int getCcompra() {
        return ccompra;
    }

    public void setCcompra(int ccompra) {
        this.ccompra = ccompra;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}

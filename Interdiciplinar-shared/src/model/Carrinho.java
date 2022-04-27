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
public class Carrinho implements Serializable{
    private int ccarinho;
    private int ccliente;

    public int getCcarinho() {
        return ccarinho;
    }

    public void setCcarinho(int ccarinho) {
        this.ccarinho = ccarinho;
    }

    public int getCcliente() {
        return ccliente;
    }

    public void setCcliente(int ccliente) {
        this.ccliente = ccliente;
    }
}

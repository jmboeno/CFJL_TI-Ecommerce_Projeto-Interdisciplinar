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
public class Categoria implements Serializable{
    private int ccategoria;
    private String descricao;

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
}

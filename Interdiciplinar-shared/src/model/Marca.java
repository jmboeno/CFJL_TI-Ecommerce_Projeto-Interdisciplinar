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
public class Marca implements Serializable{
    private int cmarca;
    private String descricao;

    public int getCmarca() {
        return cmarca;
    }

    public void setCmarca(int cmarca) {
        this.cmarca = cmarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

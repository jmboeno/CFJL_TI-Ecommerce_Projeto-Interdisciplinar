package model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Compra implements Serializable{
    private int ccompra;
    private int ccliente;
    private String data;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCcompra() {
        return ccompra;
    }

    public void setCcompra(int ccompra) {
        this.ccompra = ccompra;
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
}

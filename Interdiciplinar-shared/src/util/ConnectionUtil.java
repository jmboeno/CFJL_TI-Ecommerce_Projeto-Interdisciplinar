/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    public static Connection getConnection() throws Exception{
        if(connection == null){
        

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
    
            //Dados da conexao
            String servidor = "localhost";
            String database = "C:\\database\\ECOMMERCE.FDB";
            String User = "SYSDBA";
            String password = "masterkey";
            
            String url = "jdbc:firebirdsql:" + servidor + "/3050:"+database+ "?encoding=utf8";
            
            
            // obtem a conexao xom o banco de dados
            connection = DriverManager.getConnection(url, User, password);


            
            System.out.println("Concluido!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não localizado!");
        } catch (SQLException ex) {
            System.out.println("Não foi possivel conectar ao BD.");
            System.out.println("Erro: "+ex.getMessage());
        }
        }
        return connection;
    }
    
    public static void main(String[] args) {
        System.out.println("Teste!");
        try {
            ConnectionUtil.getConnection();
        } catch (Exception ex) {
            System.out.println(""+ex.getMessage());
        }
    }
}

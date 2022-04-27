/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author gr005972
 */
public class AppException extends Exception{
    public AppException(String message) {
        super(message);
    }
    
    public AppException(String message, Exception exception) {
        super (message, exception);
    }
}

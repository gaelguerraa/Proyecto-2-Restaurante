/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author jorge
 */
public class PresentacionExcepcion extends Exception{

    public PresentacionExcepcion() {
    }

    public PresentacionExcepcion(String message) {
        super(message);
    }

    public PresentacionExcepcion(String message, Throwable cause) {
        super(message, cause);
    }

    public PresentacionExcepcion(Throwable cause) {
        super(cause);
    }

    public PresentacionExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

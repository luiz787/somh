/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Luiz
 */
public class ExcecaoNegocio extends Exception {

    public ExcecaoNegocio(String msg) {
        super(msg);
    }

    public ExcecaoNegocio(Exception ex) {
        super(ex);
    }
    
}

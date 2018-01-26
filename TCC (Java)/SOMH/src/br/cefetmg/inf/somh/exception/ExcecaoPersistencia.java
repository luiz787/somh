/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.somh.exception;

/**
 *
 * @author Luiz
 */
public class ExcecaoPersistencia extends Exception {

    public ExcecaoPersistencia(String msg) {
        super(msg);
    }
    public ExcecaoPersistencia(Exception ex) {
        super(ex);
    }
    
}

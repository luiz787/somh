/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Acessorio;
import Model.Equipamento;
import Model.OS;
import Model.OSStatus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andro
 */
public interface OSDAOInterface {
    public void insert(Equipamento equipamento, OS os, ArrayList<Acessorio> acessorios, OSStatus osStatus) throws OSDAOException;
    /* IMPLEMENTAÇÃO FUTURA
    
    public void update(OS os) throws OSDAOException;
    public OS delete(int nro_OS) throws OSDAOException;
    public OS getOSById(int nro_OS) throws OSDAOException;
    public List<OS> listAll() throws OSDAOException;
    */
}

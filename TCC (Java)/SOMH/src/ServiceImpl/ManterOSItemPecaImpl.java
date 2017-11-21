/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.OSItemPecaDAO;
import DAOImpl.OSItemPecaDAOImpl;
import Domain.OSItemPeca;
import Domain.OSStatus;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterOSItemPeca;
import java.util.List;

/**
 *
 * @author Luiz
 */
public class ManterOSItemPecaImpl implements ManterOSItemPeca {    
    private final OSItemPecaDAO osItemPecaDAO;
    
    public ManterOSItemPecaImpl(OSItemPecaDAOImpl instance) {
        osItemPecaDAO = instance;
    }

    @Override
    public List<OSItemPeca> getAllByOS(Long id) throws ExcecaoPersistencia, ExcecaoNegocio {
        if(id==null){
            throw new ExcecaoNegocio("Id da os não pode ser nulo.");
        }
        return osItemPecaDAO.getAllByOS(id);
    }

    @Override
    public List<OSItemPeca> getAll() throws ExcecaoPersistencia {
        return osItemPecaDAO.getAll();
    }

    @Override
    public boolean cadastrarOSItemPeca(OSItemPeca osItemPeca) throws ExcecaoPersistencia, ExcecaoNegocio {
        return osItemPecaDAO.cadastrarOSItemPeca(osItemPeca);
    }
    
}

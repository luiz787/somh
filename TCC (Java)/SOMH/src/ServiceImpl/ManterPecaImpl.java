/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.PecaDAO;
import Domain.Peca;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterPeca;
import java.util.List;

/**
 *
 * @author Luiz
 */
public class ManterPecaImpl implements ManterPeca {

    public ManterPecaImpl(PecaDAO pecaDAO) {
        this.pecaDAO = pecaDAO;
    }
    
    private final PecaDAO pecaDAO;
    
    @Override
    public List<Peca> listAll() throws ExcecaoPersistencia {
        return pecaDAO.listAll();
    }

    @Override
    public boolean insert(Peca peca) throws ExcecaoNegocio, ExcecaoPersistencia {
        String errList = "";
        if (peca.getDescricao()==null || peca.getDescricao().isEmpty()){
            errList+="Descricao não pode ser nula. ";
        }
        if (peca.getMarca()==null || peca.getMarca().isEmpty()){
            errList+="Peca deve possuir uma marca. ";
        }
        return pecaDAO.insert(peca);
    }
    
}

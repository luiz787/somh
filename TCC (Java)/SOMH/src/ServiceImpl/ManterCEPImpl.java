/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.CEPDAO;
import Domain.CEP;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterCEP;
import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterCEPImpl implements ManterCEP {
private final CEPDAO cepdao;

    public ManterCEPImpl(CEPDAO cepdao) {
        this.cepdao = cepdao;
    }

    
    
    
    @Override
    public boolean cadastrarCEP(CEP cep) throws ExcecaoPersistencia, ExcecaoNegocio {
        return cepdao.insert(cep);
    }

    @Override
    public CEP deletarCEP(int id) throws ExcecaoPersistencia {
        return cepdao.delete(id);
    }

    @Override
    public CEP getCEPById(int id) throws ExcecaoPersistencia {
        return cepdao.getCEPById(id);
    }

    @Override
    public List<CEP> getAll() throws ExcecaoPersistencia {
        return cepdao.listAll();
    }
    
}

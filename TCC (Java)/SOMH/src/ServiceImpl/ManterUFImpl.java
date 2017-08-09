/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.UFDAO;
import Domain.UF;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterUF;
import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterUFImpl implements ManterUF{
    private final UFDAO ufdao;

    public ManterUFImpl(UFDAO ufdao) {
        
        this.ufdao = ufdao;
    }
    
    

    @Override
    public boolean cadastrarUF(UF id) throws ExcecaoPersistencia, ExcecaoNegocio {
       return ufdao.insert(id);
    }

    @Override
    public UF getUFById(Long id) throws ExcecaoPersistencia {
        return ufdao.consultarPorId(id);
    }

    @Override
    public List<UF> getAll() throws ExcecaoPersistencia {
        return ufdao.listAll();
    }
    
}

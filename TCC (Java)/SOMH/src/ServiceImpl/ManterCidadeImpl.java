/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImpl;

import DAO.CidadeDAO;
import Domain.Cidade;
import Domain.UF;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterCidade;
import java.util.List;

/**
 *
 * @author F43L
 */
public class ManterCidadeImpl implements ManterCidade {
    private final CidadeDAO cidadeDAO;

    public ManterCidadeImpl(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }
    
    

        @Override
        public boolean cadastrarCidade(Cidade cidade) throws ExcecaoPersistencia, ExcecaoNegocio {
            return cidadeDAO.insert(cidade);
        }

        @Override
        public Cidade deletarCidade(Long id) throws ExcecaoPersistencia {
            return cidadeDAO.delete(id);
        }

        @Override
        public Cidade getCidadeById(Long id) throws ExcecaoPersistencia {
            return cidadeDAO.getCidadeById(id);
        }

        @Override
        public List<Cidade> getAll() throws ExcecaoPersistencia {
           return cidadeDAO.listAll();
        }

        @Override
        public List<Cidade> getAllbyUF(UF uf) throws ExcecaoPersistencia {
            return cidadeDAO.listAllByUF(uf);
        }
    
}

package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.OS;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ManterOS {
    public Long cadastrarOS(OS os) throws ExcecaoPersistencia, ExcecaoNegocio;
    public boolean alterarOS(OS os) throws ExcecaoPersistencia, ExcecaoNegocio;
    public OS deletarOS(Long id) throws ExcecaoPersistencia;
    public OS getOSById(Long id) throws ExcecaoPersistencia;
    public List<OS> getAll() throws ExcecaoPersistencia;
}

package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.Acessorio;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ManterAcessorio {
    public Long cadastrarAcessorio(Acessorio acessorio) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Acessorio deletarAcessorio(Long id) throws ExcecaoPersistencia;
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia;
    public List<Acessorio> getAll() throws ExcecaoPersistencia;
}

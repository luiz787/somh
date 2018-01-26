package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.OSAcessorio;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ManterOSAcessorio {
    public boolean cadastrarOSAcessorio(OSAcessorio osAcessorio) throws ExcecaoPersistencia, ExcecaoNegocio;
    public OSAcessorio deletarOSAcessorio(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public void deletarAllOSAcessorio(Long idOS) throws ExcecaoPersistencia;
    public OSAcessorio getOSAcessorioById(Long idOS, Long idAcessorio) throws ExcecaoPersistencia;
    public List<OSAcessorio> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSAcessorio> getAll() throws ExcecaoPersistencia;
}

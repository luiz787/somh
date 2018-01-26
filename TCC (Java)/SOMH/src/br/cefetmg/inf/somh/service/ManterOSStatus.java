package br.cefetmg.inf.somh.service;

import br.cefetmg.inf.somh.domain.OSStatus;
import br.cefetmg.inf.somh.exception.ExcecaoNegocio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.util.List;

public interface ManterOSStatus {
    public boolean cadastrarOSStatus(OSStatus osStatus) throws ExcecaoPersistencia, ExcecaoNegocio;
    public void deletarAllOSStatus(Long id) throws ExcecaoPersistencia;
    public List<OSStatus> getAllByOS(Long id) throws ExcecaoPersistencia;
    public List<OSStatus> getAll() throws ExcecaoPersistencia;
}

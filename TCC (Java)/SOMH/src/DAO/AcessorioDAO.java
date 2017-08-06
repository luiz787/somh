package DAO;

import Domain.Acessorio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface AcessorioDAO {
    public Long insert(Acessorio acessorio) throws ExcecaoPersistencia;
    public Acessorio delete(Long id) throws ExcecaoPersistencia;
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia;
    public List<Acessorio> listAll() throws ExcecaoPersistencia;
}

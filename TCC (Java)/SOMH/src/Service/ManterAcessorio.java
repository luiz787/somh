package Service;

import Domain.Acessorio;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import java.util.List;

public interface ManterAcessorio {
    public Long cadastrarAcessorio(Acessorio acessorio) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Acessorio deletarAcessorio(Long id) throws ExcecaoPersistencia;
    public Acessorio getAcessorioById(Long id) throws ExcecaoPersistencia;
    public List<Acessorio> getAll() throws ExcecaoPersistencia;
}

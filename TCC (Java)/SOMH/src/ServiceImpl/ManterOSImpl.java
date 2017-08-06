package ServiceImpl;

import DAO.OSDAO;
import Domain.OS;
import Exception.ExcecaoNegocio;
import Exception.ExcecaoPersistencia;
import Service.ManterOS;
import java.util.List;

public class ManterOSImpl implements ManterOS{
    private final OSDAO osDAO;
    
    public ManterOSImpl(OSDAO osDAO) {
        this.osDAO = osDAO;
    }
    
    @Override
    public Long cadastrarOS(OS os) throws ExcecaoPersistencia, ExcecaoNegocio {
        Long result = osDAO.insert(os);
        return result;
    }

    @Override
    public boolean alterarOS(OS os) throws ExcecaoPersistencia, ExcecaoNegocio {
        boolean result = osDAO.update(os);
        return result;
    }

    @Override
    public OS deletarOS(Long cod_OS) throws ExcecaoPersistencia {
        OS result = osDAO.delete(cod_OS);
        return result;
    }

    @Override
    public OS getOSById(Long cod_OS) throws ExcecaoPersistencia {
        OS result = osDAO.getOSById(cod_OS);
        return result;
    }

    @Override
    public List<OS> getAll() throws ExcecaoPersistencia {
        List<OS> result = osDAO.listAll();
        return result;
    }
    
}

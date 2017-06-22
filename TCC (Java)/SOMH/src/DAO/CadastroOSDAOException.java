package DAO;

public class CadastroOSDAOException extends Exception{

    public CadastroOSDAOException(Exception e) {
        super("Erro no cadastro de OS: " + e.getMessage());
    }
    
}

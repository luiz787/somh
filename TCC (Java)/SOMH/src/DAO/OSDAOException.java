package DAO;

public class OSDAOException extends Exception{

    public OSDAOException(Exception e) {
        super("Erro no cadastro de OS: " + e.getMessage());
    }
    
}

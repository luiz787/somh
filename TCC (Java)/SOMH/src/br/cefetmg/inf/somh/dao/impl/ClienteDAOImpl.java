package br.cefetmg.inf.somh.dao.impl;

import br.cefetmg.inf.somh.bd.JDBCManterConexao;
import br.cefetmg.inf.somh.dao.CEPDAO;
import br.cefetmg.inf.somh.dao.ClienteDAO;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAOImpl implements ClienteDAO{
    
    private static ClienteDAOImpl clienteDAO = null;

    private ClienteDAOImpl() {
    }

    public static ClienteDAOImpl getInstance() {

        if (clienteDAO == null) {
            clienteDAO = new ClienteDAOImpl();
        }

        return clienteDAO;
    }

    @Override
    public Cliente getClienteById(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cliente WHERE cod_cpf_cnpj=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Cliente cliente = null;
            
            CEPDAO cepDAOImpl = CEPDAOImpl.getInstance();
            if (rs.next()) {
                cliente = new Cliente(
                    rs.getLong("cod_cpf_cnpj"),
                    cepDAOImpl.getCEPById(rs.getInt("nro_cep")),
                    rs.getString("nom_cliente"),
                    rs.getString("des_email"),
                    rs.getString("nro_tel_cel"),
                    rs.getString("des_endereco"),
                    rs.getString("nro_tel_fixo"),
                   
                    rs.getInt("nro_endereco"),
                    rs.getString("des_complemento")
                      
                    
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cliente;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    
    @Override
    public boolean insert(Cliente cliente) throws ExcecaoPersistencia {
        try {
            if (cliente == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO `cliente` ("
                    + "`cod_cpf_cnpj`,"
                    + " `nro_cep`,"
                    + " `cod_cidade`,"
                    + " `cod_UF`,"
                    + " `nom_cliente`,"
                    + " `des_email`"
                    + ", `nro_tel_cel`,"
                    + " `des_endereco`,"
                    + " `nro_tel_fixo`," //
                    
                    + " `nro_endereco`," //
                    + " `des_complemento`) " //
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cliente.getCodCPF_CNPJ());
            pstmt.setInt(2, cliente.getCep().getNroCEP());
            pstmt.setLong(3, cliente.getCep().getCidade().getId());
            pstmt.setString(4, cliente.getCep().getCidade().getUf().getId());
            pstmt.setString(5, cliente.getNome());
            pstmt.setString(6, cliente.getEmail());
            pstmt.setString(7, cliente.getNroTelefoneCelular());
            pstmt.setString(8, cliente.getEndereco());
            

            if(cliente.getNroTelefoneFixo()!= null) {
                pstmt.setString(9, cliente.getNroTelefoneFixo());
                
            } else {
                pstmt.setNull(9, java.sql.Types.NULL);
            }
            
            if(cliente.getNroEndereco()!=null) {
                pstmt.setInt(10, cliente.getNroEndereco());
                
            } else {
                pstmt.setNull(10, java.sql.Types.NULL);
            }
            if(cliente.getDescricaoComplemento()!=null) {
                pstmt.setString(11, cliente.getDescricaoComplemento());
                
            } else {
                pstmt.setNull(11, java.sql.Types.NULL);
            }
            System.out.println(sql); 
            
           System.out.println("Cliente infos: cpf: "+cliente.getCodCPF_CNPJ()+" cod_cep: "+cliente.getCep().getNroCEP()+" cod_cidade_id: "+cliente.getCep().getCidade().getId()+
                 " cod_UF: "+cliente.getCep().getCidade().getUf().getId()+" nome: "+cliente.getNome()+" email: "+cliente.getEmail()+
                    " nro_tel_cel: "+ cliente.getNroTelefoneCelular()+" des_endereço(rua): "+cliente.getEndereco()+" nro_endereço: "+cliente.getNroEndereco()
            +" des_complemento: "+cliente.getDescricaoComplemento());
            pstmt.executeUpdate();
           
            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public boolean update(Cliente cliente) throws ExcecaoPersistencia {
         try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE cliente "
                    + "SET "
                    +"cod_cpf_cnpj=?,"
                    + "nro_cep=?,"
                    + "cod_cidade=?,"
                    + "cod_UF=?,"
                    + "nom_cliente=?,"
                    + "des_email=?,"
                    + "nro_tel_cel=?,"
                    + "des_endereco=?,"
                    + "nro_tel_fixo=?," //
                    
                    + "nro_endereco=?," //
                    + "des_complemento=?"
                    //
                    
                    + " WHERE cod_cpf_cnpj=?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cliente.getCodCPF_CNPJ());
            pstmt.setInt(2, cliente.getCep().getNroCEP());
            pstmt.setLong(3, cliente.getCep().getCidade().getId());
            pstmt.setString(4, cliente.getCep().getCidade().getUf().getId());
            pstmt.setString(5, cliente.getNome());
            pstmt.setString(6, cliente.getEmail());
            pstmt.setString(7, cliente.getNroTelefoneCelular());
            pstmt.setString(8, cliente.getEndereco());
            

            if(cliente.getNroTelefoneFixo()!= null) {
                pstmt.setString(9, cliente.getNroTelefoneFixo());
            } else {
                pstmt.setNull(9, java.sql.Types.NULL);
            }
            
            if(cliente.getNroEndereco()!=null) {
                pstmt.setInt(10, cliente.getNroEndereco());
            } else {
                pstmt.setNull(10, java.sql.Types.NULL);
            }
            if(cliente.getDescricaoComplemento()!=null) {
                pstmt.setString(11, cliente.getDescricaoComplemento());
            } else {
                pstmt.setNull(11, java.sql.Types.NULL);
            }
            
            pstmt.setLong(12, cliente.getCodCPF_CNPJ());
            
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public Cliente delete(Long id) throws ExcecaoPersistencia {
         try {
            Cliente cliente = this.getClienteById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM cliente WHERE cod_cpf_cnpj=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return cliente;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Cliente> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM cliente ORDER BY nom_cliente;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Cliente> listAll = new ArrayList<>();
            
            CEPDAO cepDAOImpl = CEPDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    Cliente cliente = new Cliente(
                        rs.getLong("cod_cpf_cnpj"),
                        cepDAOImpl.getCEPById(rs.getInt("nro_cep")),
                        rs.getString("nom_cliente"),
                        rs.getString("des_email"),
                        rs.getString("nro_tel_cel"),
                        rs.getString("des_endereco"),
                        rs.getString("nro_tel_fixo"),
                        
                        rs.getInt("nro_endereco"),
                        rs.getString("des_complemento")
                    );
                    listAll.add(cliente);
                } while (rs.next());
            }
            
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
}

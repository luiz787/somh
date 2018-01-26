package br.cefetmg.inf.somh.dao.impl;

import br.cefetmg.inf.somh.bd.JDBCManterConexao;
import br.cefetmg.inf.somh.dao.AcessorioDAO;
import br.cefetmg.inf.somh.dao.ClienteDAO;
import br.cefetmg.inf.somh.dao.EquipamentoDAO;
import br.cefetmg.inf.somh.dao.OSDAO;
import br.cefetmg.inf.somh.domain.Cliente;
import br.cefetmg.inf.somh.domain.OS;
import br.cefetmg.inf.somh.domain.OSAcessorio;
import br.cefetmg.inf.somh.exception.ExcecaoPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OSDAOImpl implements OSDAO {
    private static OSDAOImpl osDAO = null;

    private OSDAOImpl() {
    }

    public static OSDAOImpl getInstance() {

        if (osDAO == null) {
            osDAO = new OSDAOImpl();
        }

        return osDAO;
    }
    
    @Override
    synchronized public Long insert(OS os) throws ExcecaoPersistencia {
        try {
            if (os == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO os ("
                    + "cod_cpf_cnpj, "
                    + "seq_equipto, "
                    + "txt_reclamacao, "
                    + "txt_observacao_acessorios, "
                    + "vlr_desconto, "
                    + "per_desconto, "
                    + "vlr_frete"
                    + ") VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, os.getCliente().getCodCPF_CNPJ());
            pstmt.setLong(2, os.getEquipamento().getId());
            pstmt.setString(3, os.getTxtReclamacao());
            if(os.getTxtObservacaoAcessorios()!=null) {
                pstmt.setString(4, os.getTxtObservacaoAcessorios());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if(os.getVlrDesconto()!=null) {
                pstmt.setDouble(5, os.getVlrDesconto());
            } else {
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            if(os.getPerDesconto()!=null) {
                pstmt.setDouble(6, os.getPerDesconto());
            } else {
                pstmt.setNull(6, java.sql.Types.NULL);
            }
            if(os.getVlrFrete()!=null) {
                pstmt.setDouble(7, os.getVlrFrete());
            } else {
                pstmt.setNull(7, java.sql.Types.NULL);
            }
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM os");
            
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                os.setId(id);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public boolean update(OS os) throws ExcecaoPersistencia {
        try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE os "
                    + "SET "
                    //+ "cod_cpf_cnpj=?, "
                    + "seq_equipto=?, "
                    + "txt_reclamacao=?, "
                    + "txt_observacao_acessorios=?, "
                    + "vlr_desconto=?, "
                    + "per_desconto=?, "
                    + "vlr_frete=?"
                    + " WHERE nro_OS=?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            //pstmt.setLong(1, os.getCliente().getCodCPF_CNPJ());
            pstmt.setLong(1, os.getEquipamento().getId());
            pstmt.setString(2, os.getTxtReclamacao());
            if(os.getTxtObservacaoAcessorios()!=null) {
                pstmt.setString(3, os.getTxtObservacaoAcessorios());
            } else {
                pstmt.setNull(3, java.sql.Types.NULL);
            }
            if(os.getVlrDesconto()!=null) {
                pstmt.setDouble(4, os.getVlrDesconto());
            } else {
                pstmt.setNull(4, java.sql.Types.NULL);
            }
            if(os.getPerDesconto()!=null) {
                pstmt.setDouble(5, os.getPerDesconto());
            } else {
                pstmt.setNull(5, java.sql.Types.NULL);
            }
            if(os.getVlrFrete()!=null) {
                pstmt.setDouble(6, os.getVlrFrete());
            } else {
                pstmt.setNull(6, java.sql.Types.NULL);
            }
            pstmt.setLong(7, os.getId());
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
    synchronized public OS delete(Long id) throws ExcecaoPersistencia {
        try {
            OS os = this.getOSById(id);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM os WHERE nro_OS=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return os;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public OS getOSById(Long id) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM os WHERE nro_OS=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            OS os = null;
            ClienteDAO clienteDAOImpl = ClienteDAOImpl.getInstance();
            EquipamentoDAO equipamentoDAOImpl = EquipamentoDAOImpl.getInstance();
            if (rs.next()) {
                os = new OS(
                    rs.getLong("nro_OS"),
                    clienteDAOImpl.getClienteById(rs.getLong("cod_cpf_cnpj")),
                    equipamentoDAOImpl.getEquipamentoById(rs.getLong("seq_equipto")),
                    rs.getString("txt_reclamacao"),
                    rs.getString("txt_observacao_acessorios"),
                    rs.getDouble("vlr_desconto"),
                    rs.getDouble("per_desconto"),
                    rs.getDouble("vlr_frete")
                );
            }

            rs.close();
            pstmt.close();
            connection.close();

            return os;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OSAcessorioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<OS> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM os ORDER BY nro_os;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<OS> listAll = new ArrayList<>();
            ClienteDAO clienteDAOImpl = ClienteDAOImpl.getInstance();
            EquipamentoDAO equipamentoDAOImpl = EquipamentoDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    OS os = new OS(
                        rs.getLong("nro_OS"),
                        clienteDAOImpl.getClienteById(rs.getLong("cod_cpf_cnpj")),
                        equipamentoDAOImpl.getEquipamentoById(rs.getLong("seq_equipto")),
                        rs.getString("txt_reclamacao"),
                        rs.getString("txt_observacao_acessorios"),
                        rs.getDouble("vlr_desconto"),
                        rs.getDouble("per_desconto"),
                        rs.getDouble("vlr_frete")
                    );
                    listAll.add(os);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Dao.PegawaiDao;
import domain.Pegawai;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author rangga
 */
public class JdbcService {
    
    private Connection connection;
    private PegawaiDao pegawaiDao;
    
    public void setDataSource(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            pegawaiDao = new PegawaiDao();
            pegawaiDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void save(Pegawai pegawai) {
        try {
            connection.setAutoCommit(false);
            pegawaiDao.save(pegawai);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            ex.printStackTrace();
        }
    }
    
   public Pegawai getGroup(int id) {
        return pegawaiDao.getById(id);
    }

    public List<Pegawai> getGroups() {
        return pegawaiDao.getAll();
    }
}

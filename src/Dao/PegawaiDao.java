/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import domain.Pegawai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rangga
 */
public class PegawaiDao {
    
    private Connection connection;
    private PreparedStatement getAllQuery;
    private PreparedStatement getQueryById;
    private PreparedStatement insertQuery;
    private PreparedStatement updateQuery;
    
    private final String getAll =
            "select * from pegawai";
    private final String getById =
            "select * from pegawai where id =?";
    private final String insert =
            "insert into pegawai (nama,jabatan)" +
            " values(?,?)";
    private final String update =
            "update pegawai set nama=?, jabatan=? " +
            " where id=?";
    
    public void setConnection(Connection connection){
        try {
            
            this.connection = connection;
            getAllQuery =  (PreparedStatement) this.connection.prepareStatement(getAll);
            getQueryById =  (PreparedStatement) this.connection.prepareStatement(getById);
            insertQuery =  (PreparedStatement) this.connection.prepareStatement(insert);
            updateQuery =  (PreparedStatement) this.connection.prepareStatement(update);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
    
    public void save(Pegawai pegawai){
        try {
            if(pegawai.getId() == null){
                insertQuery.setString(1, pegawai.getNama());
                insertQuery.setString(2, pegawai.getJabatan());
                insertQuery.executeUpdate();
//                ResultSet rs  = insertQuery.getGeneratedKeys();
//                Integer id = null;
//                while (rs.next()) {
//                    id = rs.getInt(1);
//                }
//                pegawai.setId(id);
//                System.out.println("Generated Key : " + pegawai.getId());
            }else{
                updateQuery.setString(1, pegawai.getNama());
                updateQuery.setString(2, pegawai.getJabatan());
                updateQuery.setInt(3, pegawai.getId());
                updateQuery.executeUpdate();
            }
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    
    public Pegawai getById(int id){
        try {
            getQueryById.setInt(1, id);
            ResultSet rs = getQueryById.executeQuery();

            // proses mapping dari relational ke object
            if (rs.next()) {
                Pegawai pegawai = new Pegawai();
                pegawai.setId(rs.getInt("id"));
                pegawai.setNama(rs.getString("nama"));
                pegawai.setJabatan(rs.getString("jabatan"));
                return pegawai;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
   
   public List<Pegawai> getAll() {
        try {
            List<Pegawai> pegawais = new ArrayList<Pegawai>();
            ResultSet rs = getAllQuery.executeQuery();
            while (rs.next()) {
                Pegawai pegawai = new Pegawai();
                pegawai.setId(rs.getInt("id"));
                pegawai.setNama(rs.getString("nama"));
                pegawai.setJabatan(rs.getString("jabatan"));
                pegawais.add(pegawai);
            }
            return pegawais;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    } 
}

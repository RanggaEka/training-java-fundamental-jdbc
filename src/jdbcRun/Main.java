/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcRun;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import domain.Pegawai;
import java.sql.SQLException;
import java.util.List;
import service.JdbcService;

/**
 *
 * @author rangga
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        dataSource.setDatabaseName("trainingJdbc");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        
        JdbcService service = new JdbcService();
        service.setDataSource(dataSource);

        List<Pegawai> recordCat = service.getGroups();
        for (Pegawai cat : recordCat) {
            System.out.println("Id   : " + cat.getId());
            System.out.println("Nama : " + cat.getNama());
            System.out.println("Jaba : " + cat.getJabatan());
        }

        Pegawai categoryById = service.getGroup(new Integer(4));
        System.out.println("Id   : " + categoryById.getId());
        System.out.println("Nama : " + categoryById.getNama());
        System.out.println("Jaba : " + categoryById.getJabatan());
        
        // Insert New Category
//        Pegawai pg = new Pegawai();
//        pg.setNama("RANGGA");
//        pg.setJabatan("DEPOK");
//        service.save(pg);
        
        try {
            dataSource.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

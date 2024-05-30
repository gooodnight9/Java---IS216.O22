/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import QLBVDATA.khothuoc;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;

/**
 *
 * @author trung
 */
public class khothuocDao {
    public List<khothuoc> getAllkhothuoc(){
        List<khothuoc> khothuocs = new ArrayList<>();
        Connection c = ConnectionOracle.getJDBCConeection();
        
        String sql = "SELECT * FROM KHOTHUOC";
        
        
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                khothuoc kt = new khothuoc();
                
                kt.setMathuoc(rs.getInt("MATHUOC"));
                kt.setTenthuoc(rs.getString("TENTHUOC"));
                kt.setDongia(rs.getDouble("DONGIA"));
                kt.setSltk(rs.getInt("SLTK"));
                kt.setGhichu(rs.getString("GHICHU"));
                
                khothuocs.add(kt);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(khothuocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return khothuocs;
    }
    
    public void insertkhothuoc(khothuoc kt) throws SQLException{
        Connection c = ConnectionOracle.getJDBCConeection();
        
        CallableStatement callableStatement = c.prepareCall("CALL INSERT_KHOTHUOC(?,?,?,?)");
        
        callableStatement.setString(1, kt.getTenthuoc());
        callableStatement.setInt(2, kt.getSltk());
        callableStatement.setDouble(3,kt.getDongia());
        callableStatement.setString(4, kt.getGhichu());
        
        callableStatement.executeUpdate();
    }
    
    public void updatekhothuoc(khothuoc kt) throws SQLException{
        Connection c = ConnectionOracle.getJDBCConeection();
        
        CallableStatement callableStatement = c.prepareCall("CALL UPDATE_KHOTHUOC(?,?,?,?)");
        
        callableStatement.setString(1, kt.getTenthuoc());
        callableStatement.setInt(2, kt.getSltk());
        callableStatement.setDouble(3,kt.getDongia());
        callableStatement.setString(4, kt.getGhichu());
        
        callableStatement.executeUpdate();
    }    
    
    public khothuoc kttontai (String ten){
        khothuocDao ktDao = new khothuocDao();
        
        for(khothuoc thuoc : ktDao.getAllkhothuoc()){
            if(thuoc.getTenthuoc().equals(ten)){
                return thuoc;
            }
        }
        
        return null;
    }
    
    public void deletethuoc(khothuoc kt){
        Connection c = ConnectionOracle.getJDBCConeection();
        
        String sql = "DELETE FROM KHOTHUOC WHERE MATHUOC = ?";
        
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            
            preparedStatement.setInt(1, kt.getMathuoc());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(khothuocDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

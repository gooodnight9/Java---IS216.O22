package Connection;

import QLBVDATA.benhnhan;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class benhnhanDao {
    public List<benhnhan> getAllBenhnhan(){
        
        List<benhnhan> benhnhans = new ArrayList<>();
        
        Connection c = ConnectionOracle.getJDBCConeection();
        String sql = "SELECT * FROM BENHNHAN";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                benhnhan bn = new benhnhan();
                
                bn.setMabn(rs.getInt("MABN"));
                bn.setCcccd(rs.getString("CCCCD"));
                bn.setTen(rs.getNString("TENBN"));
                bn.setNgaysinh(rs.getString("NGAYSINH"));
                
                bn.setGt(rs.getNString("GT"));
                bn.setSdt(rs.getString("SDT"));
                bn.setDiachi(rs.getNString("DIACHI"));
                bn.setGhichu(rs.getNString("GHICHU"));
                bn.setTinhtrang(rs.getNString("TINHTRANG"));
                
                benhnhans.add(bn);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(benhnhanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return benhnhans;
        
    }
    
    public void addbenhnhan (benhnhan bn) throws SQLException, ParseException{
            Connection c = ConnectionOracle.getJDBCConeection();
            
            CallableStatement callableStatement = c.prepareCall("{CALL INSERT_BENHNHAN(?,?,?,?,?,?,?,?)}");
            
            callableStatement.setString(1, bn.getCcccd());
            callableStatement.setString(2, bn.getTen());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(bn.getNgaysinh());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            callableStatement.setDate(3, sqlDate);
            callableStatement.setString(4, bn.getGt());
            callableStatement.setString(5, bn.getSdt());
            callableStatement.setString(6, bn.getDiachi());
            callableStatement.setString(7, bn.getGhichu());
            callableStatement.setString(8, bn.getTinhtrang());
            
        callableStatement.executeUpdate();
    }
    
    public void updatebenhnhan(benhnhan bn) throws SQLException, ParseException {
        try (Connection c = ConnectionOracle.getJDBCConeection(); CallableStatement callableStatement = c.prepareCall("{CALL UPDATE_BENHNHAN(?,?,?,?,?,?,?,?)}")) {
            
            callableStatement.setString(1, bn.getCcccd());
            callableStatement.setString(2, bn.getTen());
            
            // Chuyển đổi ngày sinh từ chuỗi định dạng dd/MM/yyyy sang java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(bn.getNgaysinh());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            callableStatement.setDate(3, sqlDate);
            
            callableStatement.setString(4, bn.getGt());
            callableStatement.setString(5, bn.getSdt());
            callableStatement.setString(6, bn.getDiachi());
            callableStatement.setString(7, bn.getGhichu());
            callableStatement.setString(8, bn.getTinhtrang());
            
            callableStatement.executeUpdate();
        }
    }
    
    

}

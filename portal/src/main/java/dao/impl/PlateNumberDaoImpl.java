package dao.impl;

import com.alibaba.fastjson.JSON;
import dao.PlateNumberDao;
import org.apache.commons.collections.map.HashedMap;

import javax.jws.Oneway;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 */
public class PlateNumberDaoImpl implements PlateNumberDao{

    public Map<String,Object> queryByPlateNumber(String plateNumber) {
        Map<String,Object> res = new HashedMap();
        try{
            Connection conn = LogCheckDaoImpl.SQLServer.getConnection();
            String sql = "select f.* ,u.user_name from plate_number_info f,user u WHERE u.id = f.user_id AND f.plate_number = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,plateNumber);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ResultSetMetaData data = rs.getMetaData();
                for (int i = 1; i<=data.getColumnCount();i++) {
                    String columnName = data.getColumnName(i);
                    res.put(columnName,rs.getString(columnName));
                }
            }
            pst.close();
            LogCheckDaoImpl.SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

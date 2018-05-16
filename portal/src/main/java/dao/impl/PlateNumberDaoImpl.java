package dao.impl;

import dao.BaseQueryDao;
import dao.PlateNumberDao;
import org.apache.commons.collections.map.HashedMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 */
public class PlateNumberDaoImpl extends BaseQueryDao implements PlateNumberDao{

    public Map<String,Object> queryByPlateNumber(String plateNumber) {
        Map<String,Object> res = new HashedMap();
        try{
            Connection conn = LogCheckDaoImpl.SQLServer.getConnection();
            String sql = "select f.* ,u.user_name from plate_number_info f,user u WHERE u.id = f.user_id AND f.plate_number = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,plateNumber);
            ResultSet rs = pst.executeQuery();
            res = this.formatSqlResult(rs);
            pst.close();
            LogCheckDaoImpl.SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

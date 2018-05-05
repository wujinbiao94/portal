package dao;

import dao.impl.LogCheckDaoImpl;
import org.apache.commons.collections.map.HashedMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 * 查询sql基础父类
 */
public class BaseQueryDao {

    /**
     * 查询sql
     * @param params 参数
     * @param sql 查询sql
     * @return 返回一个list
     */
    public Map<String,Object> queryBy(LinkedHashMap<String, Object> params, String sql){
        Map<String,Object> res = new HashedMap();
        try{
            Connection conn = LogCheckDaoImpl.SQLServer.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            int j = 1;
            for (String key:params.keySet()) {
                pst.setString(j++,params.get(key).toString());
            }
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

package dao;

import dao.impl.LogCheckDaoImpl;
import org.apache.commons.collections.map.HashedMap;
import utils.jdbc.SqlServicePool;

import java.sql.*;
import java.util.*;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 * 查询sql基础父类
 */
public class BaseQueryDao {

    public static SqlServicePool SQLServer = new SqlServicePool();
    /**
     * 查询sql
     * @param params 参数
     * @param sql 查询sql
     * @return 返回一个list
     */
    public Map<String,Object> queryBy(LinkedHashMap<String, Object> params, String sql){
        Map<String,Object> res = new HashedMap();
        Connection conn = null;
        try{
            conn = SQLServer.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            int j = 1;
            for (String key:params.keySet()) {
                pst.setString(j++,params.get(key).toString());
            }
            ResultSet rs = pst.executeQuery();
            res = formatSqlResult(rs);
            pst.close();
            SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                SQLServer.close(conn);
            }
        }
        return res;
    }

    public List<Map<String,Object>> queryList(LinkedHashMap<String, Object> params, String sql){
        List<Map<String,Object>> res = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        try{
            conn = SQLServer.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            int j = 1;
            for (String key:params.keySet()) {
                pst.setString(j++,params.get(key).toString());
            }
            ResultSet rs = pst.executeQuery();
             res = formatSqlResultToList(rs);
            pst.close();
            SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                SQLServer.close(conn);
            }
        }
        return res;
    }
    /**
     * 转换查询到的数据结果
     * @param rs
     * @return
     * @throws Exception
     */
    public Map<String, Object> formatSqlResult(ResultSet rs) throws Exception {
        Map<String,Object> res = new HashMap();
        if (rs.next()) {
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i<=data.getColumnCount();i++) {
                String columnName = data.getColumnName(i);
                res.put(columnName,rs.getString(columnName));
            }
        }
        return res;
    }

    /**
     * 返回查询结果为list<Map>
     * @param rs
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> formatSqlResultToList(ResultSet rs) throws  Exception{
        List<Map<String,Object>> res = new ArrayList<Map<String, Object>>();

        while (rs.next()) {
            Map<String,Object> map = new HashMap();
            ResultSetMetaData data = rs.getMetaData();
            for (int i = 1; i<=data.getColumnCount();i++) {
                String columnName = data.getColumnName(i);
                map.put(columnName,rs.getString(columnName));
            }
            res.add(map);
        }
        return res;
    }
    /**
     * 插入执行
     * @param sql
     * @param params
     * @return
     */
    public static int insert(String sql,LinkedHashMap<String, Object> params) {
        Connection conn = SQLServer.getConnection();
        int i = 0;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            int j = 1;
            for (String key:params.keySet()) {
                pstmt.setString(j++,params.get(key).toString());
            }
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conn != null){
                SQLServer.close(conn);
            }
        }
        return i;
    }
}

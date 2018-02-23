package dao.impl;

import dao.LogCheckDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.jdbc.SqlServicePool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class LogCheckDaoImpl implements LogCheckDao{
    private static final Logger logger = LoggerFactory.getLogger(LogCheckDaoImpl.class);
    static SqlServicePool SQLServer = new SqlServicePool();
    static int passwordIndex = 3;

    public String queryPasswordByUserName(String userName) {
        logger.info("查询密码，用户名为："+userName);
        SQLServer.init();
        long start = System.currentTimeMillis();
        String password = query(userName);
        long end = System.currentTimeMillis();
        logger.info("查询耗时：" + (end-start));
        logger.info("查询用户：{}的密码为：{}",userName,password);
        return password;
    }

    public String query(String userName) {
        String result = "";
        try{
            Connection conn = SQLServer.getConnection();
            String sql = "select * from user WHERE user_name = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = rs.getString(passwordIndex);
            }
            pst.close();
            SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}

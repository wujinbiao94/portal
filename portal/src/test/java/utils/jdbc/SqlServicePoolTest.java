package utils.jdbc;

import dao.LogCheckDao;
import dao.impl.LogCheckDaoImpl;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONObject;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class SqlServicePoolTest {
    static SqlServicePool SQLServer = new SqlServicePool();
    public static void main(String args[]) {
        SQLServer.init();
        long start = System.currentTimeMillis();
        LogCheckDaoImpl logCheckDao = new LogCheckDaoImpl();
        String rs = logCheckDao.queryPasswordByUserName("wujinbiao");
        //test();

        long end = System.currentTimeMillis();
        System.out.println("查詢結果"+rs);
    }
    private static void test() {
        try{
            Connection conn = SQLServer.getConnection();
            String sql = "select * from user";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            int i=1;
            while (rs.next()) {
                System.out.println("查询结果：" +rs.getObject(2));
                i++;
            }
                pst.close();
            SQLServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
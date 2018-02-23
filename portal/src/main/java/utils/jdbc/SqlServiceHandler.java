package utils.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class SqlServiceHandler {
    private static final Logger logger = LoggerFactory.getLogger(SqlServiceHandler.class);
    private Connection conn = null;

    public Connection buildConnection() {
        logger.info("开始读取mysql连接信息！");
        try {
           // URL url = new URL("F:\\webservice\\untitled\\portal\\src\\main\\resources\\");
            InputStream inStream = SqlServiceHandler.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(inStream);
            String  USERNAME = prop.getProperty("jdbc.username");
            String PASSWORD = prop.getProperty("jdbc.password");
            String DRIVER = prop.getProperty("jdbc.driver");
            String DBURL = prop.getProperty("jdbc.url");
            logger.info("数据库url="+DBURL+" " +USERNAME+" " +PASSWORD);
            Class.forName(DRIVER);
            this.conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }catch(Exception e) {
            logger.error("数据库连接失败！异常=",e);
            e.printStackTrace();
        }
        return conn;
    }
}

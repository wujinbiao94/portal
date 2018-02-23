package utils.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by 你敬爱的彪哥 on 2018/2/23.
 */
public class SqlServicePool {
    private static final Logger logger = LoggerFactory.getLogger(SqlServicePool.class);
    //最小连接数
    private static final int minCount = 1;
    //最大连接数
    private static final int maxCount = 10;
    //连接池
    private static final LinkedList<Connection> pools = new LinkedList<Connection>();
    SqlServiceHandler handler = new SqlServiceHandler();
    /**
     * @author jinbiaowu
     * @date 2018年02月22日 下午2:49:38
     * Description: 重写init
     */
    public void init() {
        logger.info("数据库初始化开始！");
        Connection conn = null;
        try{
            for(int i=0; i<minCount; i++) {
                conn = handler.buildConnection();
                pools.add(conn);
            }
        }catch(Exception e) {
            logger.error("数据库初始化异常！");
            e.printStackTrace();
        }
    }

    /**
     * @date 2018年02月22日 下午2:49:38
     * Description: 重写getConnection
     */
    public synchronized Connection getConnection() {
        logger.info("获取数据库连接！");
        Connection conn = null;
        if(pools.size() == 0) {
            conn = handler.buildConnection();
        } else {
            conn = pools.remove(0);
        }
        logger.info("获取数据库连接完成！");
        return conn;
    }

    /**
     * @date 2018年02月22日 下午2:49:38
     * Description: 重写close
     */
    public synchronized void close(Connection conn) {
        logger.info("关闭数据库连接！");
        if(pools.size() < maxCount) {
            pools.add(conn);
        }
        System.out.println(pools);
    }
}

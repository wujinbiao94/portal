package dao;

import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/5.
 */
public interface QueryCarDao {

    Map<String, Object> queryCarInfoById(String carID);
}

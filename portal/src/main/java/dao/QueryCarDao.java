package dao;

import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/5.
 */
public interface QueryCarDao {

    /**
     * 车牌号查询
     * @param carID 车牌号
     * @return
     */
    Map<String, Object> queryCarInfoById(String carID);

    /**
     * 驾驶证编号
     * @param driverLisence
     * @return
     */
    Map<String,Object> queryByDriverLisence(String driverLisence);
}

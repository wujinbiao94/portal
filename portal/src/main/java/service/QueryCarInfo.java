package service;

import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/5.
 * 进行相关信息查询
 */
public interface QueryCarInfo {

    /**
     * 根据车牌号查询行管信息
     * @param carID 车牌号
     * @return
     */
     Map<String, Object> queryCarInfoById(String carID);
}

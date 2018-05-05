package service.impl;

import dao.QueryCarDao;
import service.QueryCarInfo;

import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/5.
 */
public class QueryCarInfoServiceImpl implements QueryCarInfo{

    private QueryCarDao queryCarDao;
    /**
     * 根据车牌号查询行管信息
     *
     * @param carID 车牌号
     * @return
     */
    public Map<String, Object> queryCarInfoById(String carID) {
        return queryCarDao.queryCarInfoById(carID);
    }
}

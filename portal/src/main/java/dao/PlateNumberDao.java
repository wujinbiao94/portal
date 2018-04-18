package dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/4/17.
 * 车牌查询dao
 */
public interface PlateNumberDao {

    /**
     * 查询车牌信息
     * @param plateNumber 车牌号
     * @return
     */
    Map<String,Object> queryByPlateNumber(String plateNumber);
}

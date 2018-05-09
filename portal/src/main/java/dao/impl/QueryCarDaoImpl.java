package dao.impl;

import dao.BaseQueryDao;
import dao.QueryCarDao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/5.
 */
public class QueryCarDaoImpl extends BaseQueryDao implements QueryCarDao {


    /**
     * 根据车牌号查询行管信息
     *
     * @param carID 车牌号
     * @return
     */
    public Map<String, Object> queryCarInfoById(String carID) {
        String sql = "SELECT p.plate_number, r.region_name,d.ID_num,d.driver_name,d.driver_license_id,c.car_brand,c.description FROM driver d, region_info r, car_info c, plate_number_info p WHERE c.driver_id = d.id AND r.id = d.region_id AND p.driver_id =d.id and p.plate_number = ? ";
        LinkedHashMap<String,Object> param = new LinkedHashMap();
        param.put("plateNum", carID);
        return queryBy(param,sql);
    }

    /**
     * 驾驶证编号
     * @param driverLisence
     * @return
     */
    public Map<String, Object> queryByDriverLisence(String driverLisence) {
        String sql = "SELECT d.driver_name,d.age,d.phone,d.sex,d.ID_num,r.region_name,GROUP_CONCAT(CONCAT(p.peccancy_place,p.peccancy_type),',') peccancyInfo FROM driver d, region_info r,peccancy_info p WHERE r.id = d.region_id AND p.driver_id = d.id AND p.is_deal = 0 and d.ID_num = ?";
        LinkedHashMap<String,Object> param = new LinkedHashMap();
        param.put("driverLisence", driverLisence);
        return queryBy(param,sql);
    }
}

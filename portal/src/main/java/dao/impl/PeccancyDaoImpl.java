package dao.impl;

import dao.BaseQueryDao;
import dao.PeccancyDao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 你敬爱的彪哥 on 2018/5/6.
 * 插入违章信息
 */
public class PeccancyDaoImpl extends BaseQueryDao implements PeccancyDao {

    /**
     * 插入违章信息
     * @param data 插入信息
     */
    public int insertPeccancyInfo(Map<String, Object> data){
        String sql = "INSERT INTO peccancy_info (peccancy_place,peccancy_date,driver_id,create_time,modify_time,is_deal,peccancy_type,deal_persion,car_plate_num) VALUES (?,?,(SELECT id FROM driver WHERE ID_num = ?),NOW(),NOW(),'0',?,?,?);";
        LinkedHashMap<String,Object> param = new LinkedHashMap();
        param.put("peccancy_place", data.get("peccancy_place"));
        param.put("peccancy_date", data.get("peccancy_date"));
        param.put("ID_num", data.get("ID_num"));
        param.put("peccancy_type",data.get("peccancy_type"));
        param.put("deal_persion",data.get("dealPersion"));
        param.put("carPlateNum",data.get("carPlateNum"));
       return insert(sql,param);
    }
}